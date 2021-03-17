package newHire.create.email;

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
		CreateEmailServer emailservice = new CreateEmailServer();
		Properties prop = emailservice.getProperties();
		emailservice.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));
		try {
			Server server = ServerBuilder.forPort(port).addService(emailservice).build().start();
			System.out.println("Server started, waiting for RPC calls...");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Properties getProperties() {
		Properties prop = null;
		try (InputStream input = new FileInputStream("src/main/resources/properties/email.properties")) {
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

	public void createEmail(EmailToCreate request, StreamObserver<EmailCreated> responseObserver) {
		String email = request.getText() + "@gmail.com";
		System.out.println("Email created: " + email);
		emails.add(email);
		EmailCreated reply = EmailCreated.newBuilder().setValue(email).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	public void deleteEmail(EmailToDelete request, StreamObserver<EmailDeleted> responseObserver) {
		for (int i = 0; i < emails.size(); i++) {
			if (emails.get(i).contains(request.getText())) {
				emails.remove(i);
				EmailDeleted reply = EmailDeleted.newBuilder().setValue("Email deleted: " + emails.get(i)).build();
				responseObserver.onNext(reply);
				break;
			} else {
				String noEmail = "No email was found";
				EmailDeleted reply = EmailDeleted.newBuilder().setValue(noEmail).build();
				responseObserver.onNext(reply);
			}
		}
		responseObserver.onCompleted();
	}

	public void seeEmails(Emails request, StreamObserver<AllEmails> responseObserver) {
		if (emails.size() > 0) {
			for (int i = 0; i < emails.size(); i++) {
				String email = emails.get(i);
				AllEmails reply = AllEmails.newBuilder().setValue(email).build();
				System.out.println("email: " + email);
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