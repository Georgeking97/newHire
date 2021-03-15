package create.security.card;

import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

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
		// creating an object of the class to add the service to the server
		SecurityCardServer classObj = new SecurityCardServer();
		Properties prop = classObj.getProperties();
		classObj.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));
		try {
			// setting the port, adding the service and starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			// Providing feedback letting the user know the server started successfully
			System.out.println("Server started, awaiting RPC...");

			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Properties getProperties() {
		Properties prop = null;
		// providing path to the properties file
		try (InputStream input = new FileInputStream("src/main/resources/security_card.properties")) {
			prop = new Properties();
			// loading the properties file
			prop.load(input);
			// get the property values and printing them out
			System.out.println("Math Service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	private void registerService(Properties prop) {
		try {
			// creating an instance of JmDNS
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting the values required to register my service
			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			String service_description_properties = prop.getProperty("service_description");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			// creating the service for registration
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			// registering the service
			jmdns.registerService(serviceInfo);
			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
			// good practice to wait some time
			Thread.sleep(1000);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// client side streaming RPC
	public StreamObserver<card> createCard(StreamObserver<cardCreated> responseObserver) {
		return new StreamObserver<card>() {

			// an arrayList of strings to hold each message as they're sent over
			ArrayList<String> list = new ArrayList();

			@Override
			public void onNext(card value) {
				// letting the user know I've received the message
				System.out.println("Recieving permissions for your security badge: " + value.getText());
				// adding the message to my array of messages
				list.add(value.getText());
			}

			@Override
			public void onCompleted() {
				// string builder to connect the strings into a single response
				StringBuilder sb = new StringBuilder();
				String result = "";
				// keeping the user informed
				System.out.println("All permissions recieved \n");
				// for each object in the arrayList (for each message the client sends us)
				for (String p : list) {
					// get the message sent and append it to the response (temp)
					result = sb.append(" " + p + " ").toString();
				}
				// adding each card to the global array for system access
				cards.add(result.toString());
				System.out.println("Your security badge has been created with said permissions: " + result);
				// creating the reply to send back
				cardCreated reply = cardCreated.newBuilder().setValue(result).build();
				// sending the reply back
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
		};
	}

	public void seeCards(RequestCards request, StreamObserver<CardsReturned> responseObserver) {
		for (int i = 0; i < cards.size(); i++) {
			String card = cards.get(i);
			CardsReturned reply = CardsReturned.newBuilder().setValue(card).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}

	public void deleteCard(SpecifyCard request, StreamObserver<CardDeleted> responseObserver) {
		// for each card in the array check if the passed in name matches and if so
		// delete the matching email
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).contains(request.getText())) {
				cards.remove(i);
				break;
			}
		}
		// responding to the client
		String response = "Card deleted...";
		CardDeleted reply = CardDeleted.newBuilder().setValue(response).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
}
