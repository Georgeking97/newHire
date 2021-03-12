package newHire.create.permissions;

import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.permissions.newHireGrpc.newHireImplBase;

public class CreatePermissionsServer extends newHireImplBase {
	ArrayList<String> permissions = new ArrayList<>();

	public static void main(String[] args) {
		CreatePermissionsServer classObj = new CreatePermissionsServer();
		// creating the obj to register
		Properties prop = classObj.getProperties();
		// setting the port for hosting the server
		int port = Integer.valueOf(prop.getProperty("service_port"));
		// registering the obj created
		classObj.registerService(prop);

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
		try (InputStream input = new FileInputStream("src/main/resources/permissions.properties")) {
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

	// Setting permissions bi-drectional RPC
	public StreamObserver<MessageRequest> sendMessage(StreamObserver<MessageReply> responseObserver) {
		return new StreamObserver<MessageRequest>() {

			String message;

			@Override
			public void onNext(MessageRequest value) {
				// letting the user know we recieved the message and what that message was
				System.out.println("Messaged recieved: " + value.getText());
				// creating the reply to send back to the user
				MessageReply reply = MessageReply.newBuilder().setValue(message.toString()).build();
				// sending the reply back to the user
				responseObserver.onNext(reply);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Service completed...");
				responseObserver.onCompleted();
			}

		};
	}

	// creating permissions simple rpc
	public void setPermissions(NewPermission request, StreamObserver<CreatedPermission> responseObserver) {
		// getting the value the user sent from the client to create the permission
		String permission = request.getText();
		// adding the new permission to the array of permissions
		permissions.add(permission);
		// creating the message for the reply
		String response = "Your permission has been created ";
		// creating the reply for the client
		CreatedPermission reply = CreatedPermission.newBuilder().setValue(response + permission).build();
		// sending the reply to the client
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	// seeing all available permissions server side rpc streaming
	public void seePermissions(RequestPermissions request, StreamObserver<AllPermissions> responseObserver) {
		// for each permission in the array create a reply and send it back to the
		// client
		for (int i = 0; i < permissions.size(); i++) {
			String permission = permissions.get(i);
			AllPermissions reply = AllPermissions.newBuilder().setValue(permission).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}
}
