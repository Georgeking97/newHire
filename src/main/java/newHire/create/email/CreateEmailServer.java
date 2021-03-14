package newHire.create.email;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

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
		// getting access for the properties file for registration method
		Properties prop = emailservice.getProperties();
		// calling the register service method to register the server
		emailservice.registerService(prop);

		// default port for connecting to
		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {
			// setting the port to connect to, building & starting the server
			Server server = ServerBuilder.forPort(port).addService(emailservice).build().start();
			System.out.println("Server started, waiting for rpc...");

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
		try (InputStream input = new FileInputStream("src/main/resources/email.properties")) {
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

	// Simple RPC - creating the email
	@Override
	public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
		// telling the user I got their message
		System.out.println("Recieving message: " + request.getText());
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
		// for each email in the array check if the passed in name matches and if so
		// delete the matching email
		String email = "deleted";
		System.out.print(emails.size());
		for (int i = 0; i < emails.size(); i++) {
			System.out.print(emails.get(i));
			if (emails.get(i).contains(request.getText())) {
				emails.remove(i);
				EmailDeleted reply = EmailDeleted.newBuilder().setValue(email).build();
				responseObserver.onNext(reply);
				break;
			} else {
				String noEmail = "No email was found";
				EmailDeleted reply = EmailDeleted.newBuilder().setValue(noEmail).build();
				responseObserver.onNext(reply);
			}
		}
		// responding to the client
		responseObserver.onCompleted();
	}

	// see all email's
	public void seeEmails(Emails request, StreamObserver<AllEmails> responseObserver) {
		// for each email in the array respond to the client with the email
		System.out.println("array size: "+emails.size());
		if (emails.size() > 0) {
			for (int i = 0; i < emails.size(); i++) {
				String email = emails.get(i);
				AllEmails reply = AllEmails.newBuilder().setValue(email).build();
				System.out.println("email: "+email);
				responseObserver.onNext(reply);
			}
		} else {
			String response = "There was no emails";
			AllEmails reply = AllEmails.newBuilder().setValue(response).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}
}
