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
		SecurityCardServer classObj = new SecurityCardServer();
		Properties prop = classObj.getProperties();
		classObj.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));
		try {
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, awaiting RPC calls...");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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

	private void registerService(Properties prop) {
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			String service_description_properties = prop.getProperty("service_description");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));
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

	public StreamObserver<card> createCard(StreamObserver<cardCreated> responseObserver) {
		return new StreamObserver<card>() {
			ArrayList<String> list = new ArrayList<>();

			@Override
			public void onNext(card value) {
				System.out.println("Recieving permissions for your security badge: " + value.getText());
				list.add(value.getText());
			}

			@Override
			public void onCompleted() {
				StringBuilder sb = new StringBuilder();
				String result = "";
				System.out.println("All permissions recieved \n");
				for (String p : list) {
					result = sb.append(" " + p + " ").toString();
				}
				cards.add(result.toString());
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

	public void seeCards(RequestCards request, StreamObserver<CardsReturned> responseObserver) {
		for (int i = 0; i < cards.size(); i++) {
			String card = cards.get(i);
			System.out.println("this is the card value that is being sent back to the client: "+card);
			CardsReturned reply = CardsReturned.newBuilder().setValue(card).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}

	public void deleteCard(SpecifyCard request, StreamObserver<CardDeleted> responseObserver) {
		for (int i = 0; i < cards.size(); i++) {
			if (cards.get(i).contains(request.getText())) {
				cards.remove(i);
				break;
			}
		}
		String response = "Card deleted...";
		CardDeleted reply = CardDeleted.newBuilder().setValue(response).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

}
