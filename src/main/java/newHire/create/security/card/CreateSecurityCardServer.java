package newHire.create.security.card;

import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.security.card.newHireGrpc.newHireImplBase;

public class CreateSecurityCardServer extends newHireImplBase {
	ArrayList<String> cards = new ArrayList<>();

	public static void main(String[] args) {
		// creating an object of the class to add the service to the server
		CreateSecurityCardServer classObj = new CreateSecurityCardServer();
		// default port
		int port = 50053;

		try {
			// setting the port, adding the service and starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			// Providing feedback letting the user know the server started successfully
			System.out.println("Server started, awaiting RPC...");
			// get an instance of a JmDNS
			
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting service information to be passed into service info below
			String service_type = "_http._tcp.local.";
			String service_name = "Creating security card";
			String service_description = "Service for creating a security card for a new hire";
			int service_port = 9080;
			// create a service - with ServiceInfo
			ServiceInfo serviceinfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
			// registering the service I just made
			jmdns.registerService(serviceinfo);
			// feedback for the user
			System.out.printf("Registering service with type %s and name %s on port %d", service_type, service_name, service_port);
			// good idea to wait a bit
			Thread.sleep(20000);

			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// client side streaming RPC
	public StreamObserver<MessageRequest> sendMessage(StreamObserver<MessageReply> responseObserver) {
		return new StreamObserver<MessageRequest>() {

			// an arrayList of strings to hold each message as they're sent over
			ArrayList<String> list = new ArrayList();

			@Override
			public void onNext(MessageRequest value) {
				// letting the user know I've received the message
				System.out.println("Recieving permissions for your security badge: " + value.getText());
				// adding the message to my array of messages
				list.add(value.getText());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
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
				cards.add(result);
				System.out.println("Your security badge has been created with said permissions: " + result);
				// creating the reply to send back
				MessageReply reply = MessageReply.newBuilder().setValue(result).build();
				// sending the reply back
				responseObserver.onNext(reply);
				responseObserver.onCompleted();
			}
		};
	}
}
