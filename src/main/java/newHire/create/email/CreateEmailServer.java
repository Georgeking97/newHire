package newHire.create.email;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.email.newHireGrpc.newHireImplBase;

public class CreateEmailServer extends newHireImplBase {

	ArrayList<String> emails = new ArrayList<>();

	public static void main(String[] args) {
		// object of server class
		CreateEmailServer emailservice = new CreateEmailServer();
		// default port for connecting to
		int port = 50051;

		try {
			// setting the port to connect to, building & starting the server
			Server server = ServerBuilder.forPort(port).addService(emailservice).build().start();
			System.out.println("Server started, waiting for rpc...");

			// get an instance of a JmDNS
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting service information to be passed into service info below
			String service_type = "_http._tcp.local.";
			String service_name = "emails";
			String service_description = "Service for creating an email for a new hire";
			int service_port = 9080;
			// create a service - with ServiceInfo
			ServiceInfo serviceinfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
			// registering the service I just made
			jmdns.registerService(serviceinfo);
			// feedback for the user
			System.out.printf("Registering service with type %s and name %s on port %d", service_type, service_name,
					service_port);
			// good idea to wait a bit
			Thread.sleep(20000);

			server.awaitTermination();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Simple RPC - creating the email
	@Override
	public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
		// telling the user I got their message
		System.out.println("Recieving message " + request.getText());
		String email = request.getText() + "@gmail.com";
		emails.add(email);
		// creating the response to send back to the user
		MessageReply reply = MessageReply.newBuilder().setValue(email).build();
		// sending back the response to the user
		responseObserver.onNext(reply);
		// the action is completed and all actions are executed
		responseObserver.onCompleted();
	}

	// deleting the email
	public void deleteEmail(EmailToDelete request, StreamObserver<EmailDeleted> responseObserver) {
		//for each email in the array check if the passed in name matches and if so delete the matching email
		for (int i = 0; i < emails.size(); i++) {
			if (emails.get(i).contains(request.getText())) {
				emails.remove(i);
				break;
			}
		}
		//responding to the client 
		String response = "Email deleted...";
		EmailDeleted reply = EmailDeleted.newBuilder().setValue(response).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	// see all email's
	public void seeEmails(Emails request, StreamObserver<AllEmails> responseObserver) {
		//for each email in the array respond to the client with the email
		for (int i = 0; i < emails.size(); i++) {
			String email = emails.get(i);
			AllEmails reply = AllEmails.newBuilder().setValue(email).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();

	}
}
