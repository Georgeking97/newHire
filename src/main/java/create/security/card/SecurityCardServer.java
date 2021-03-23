package create.security.card;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import create.security.card.newHire1Grpc.newHire1ImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class SecurityCardServer extends newHire1ImplBase {
	ArrayList<String> cards = new ArrayList<>();

	public static void main(String[] args) {
		//creating an instance of the class
		SecurityCardServer classObj = new SecurityCardServer();
		//creating the prop object to allow me to register my service
		Properties prop = classObj.getProperties();
		//calling the register service method and passing in my property object 
		classObj.registerService(prop);
		//setting the port for the server to run on and listen on for requests
		int port = Integer.valueOf(prop.getProperty("service_port"));
		try {
			//creating the server 
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, awaiting RPC calls...");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//method used to get the required property information
	private Properties getProperties() {
		Properties prop = null;
		try (InputStream input = new FileInputStream("src/main/resources/properties/security_card.properties")) {
			prop = new Properties();
			prop.load(input);
			System.out.println("Service properties...");
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}
	//method used to register the service and have it be discoverable 
	private void registerService(Properties prop) {
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			String service_description_properties = prop.getProperty("service_description");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));
			//registering the service via JmDNS
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			jmdns.registerService(serviceInfo);
			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
			Thread.sleep(1000);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//client side streaming RPC to create a security card
	@Override
	public StreamObserver<card> createCard(StreamObserver<cardCreated> responseObserver) {
		return new StreamObserver<card>() {
			final ArrayList<String> list = new ArrayList<>();
			//for each request we recieve from the client in a single stream we add it to an array
			@Override
			public void onNext(card value) {
				System.out.println("Recieving permissions for your security badge: " + value.getText());
				list.add(value.getText());
			}
			//once the user indicates to use the stream is finished we use a string buffer to 
			//connect all the values together to create a single value to return to the user
			@Override
			public void onCompleted() {
				StringBuilder sb = new StringBuilder();
				String result = "";
				System.out.println("All permissions recieved \n");
				for (String p : list) {
					result = sb.append("" + p + " ").toString();
				}
				cards.add(result);
				System.out.println("Your security badge has been created with said permissions: " + result);
				cardCreated reply = cardCreated.newBuilder().setValue(result).build();
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		};
	}
	//server side rpc call to show the user all current security cards on the system
	@Override
	public void seeCards(RequestCards request, StreamObserver<CardsReturned> responseObserver) {
		System.out.println("See cards request started");
		//using a for loop we send each security card on the system back to the user
		//if there is no security cards on the server a simple response is sent back
		//letting the user know this
		if(cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				String card = cards.get(i);
				System.out.println("this is the card value that is being sent back to the client: " + card);
				CardsReturned reply = CardsReturned.newBuilder().setValue("The cards are: "+card).build();
				responseObserver.onNext(reply);
			}
		} else {
			CardsReturned reply = CardsReturned.newBuilder().setValue("No cards to see").build();
			responseObserver.onNext(reply);
		}
		System.out.println("See cards request finished");
		responseObserver.onCompleted();
	}
	//a simple rpc call for allowing a user to delete security cards off the server
	//it uses a for loop to check the request against every security card in the system
	//and on the first match it deletes that card if there is a match. if no card is found
	//a simple response is sent back to the user letting them know this. as well as if there
	//is no cards on the system
	@Override
	public void deleteCard(SpecifyCard request, StreamObserver<CardDeleted> responseObserver) {
		System.out.println("delete cards request started");
		System.out.println("The request sent to the server was: "+request.getText());
		if(cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				if (cards.get(i).contains(request.getText())) {
					CardDeleted reply = CardDeleted.newBuilder().setValue("Card deleted: "+cards.get(i)).build();
					responseObserver.onNext(reply);
					cards.remove(i);
					break;
				} else {
					CardDeleted reply = CardDeleted.newBuilder().setValue("No cards found with the following information: "+request.getText()).build();
					responseObserver.onNext(reply);
				}
			}
		} else {
			CardDeleted reply = CardDeleted.newBuilder().setValue("No cards to delete").build();
			responseObserver.onNext(reply);
		}
		System.out.println("delete cards request finished");
		responseObserver.onCompleted();
	}

}
