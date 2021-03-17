package newHire.create.permissions;

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
import newHire.create.permissions.newHireGrpc.newHireImplBase;

public class CreatePermissionsServer extends newHireImplBase {
	ArrayList<String> permissions = new ArrayList<>();

	public static void main(String[] args) {
		CreatePermissionsServer classObj = new CreatePermissionsServer();
		Properties prop = classObj.getProperties();
		int port = Integer.valueOf(prop.getProperty("service_port"));
		classObj.registerService(prop);
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
		try (InputStream input = new FileInputStream("src/main/resources/properties/permissions.properties")) {
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

	public StreamObserver<MessageRequest> sendMessage(StreamObserver<MessageReply> responseObserver) {
		return new StreamObserver<MessageRequest>() {
			String message;

			@Override
			public void onNext(MessageRequest value) {
				System.out.println("Messaged recieved: " + value.getText());
				MessageReply reply = MessageReply.newBuilder().setValue(message.toString()).build();
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

	public void setPermissions(NewPermission request, StreamObserver<CreatedPermission> responseObserver) {
		String permission = request.getText();
		permissions.add(permission);
		String response = "Your permission has been created ";
		CreatedPermission reply = CreatedPermission.newBuilder().setValue(response + permission).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	public void seePermissions(RequestPermissions request, StreamObserver<AllPermissions> responseObserver) {
		for (int i = 0; i < permissions.size(); i++) {
			String permission = permissions.get(i);
			AllPermissions reply = AllPermissions.newBuilder().setValue(permission).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}

}
