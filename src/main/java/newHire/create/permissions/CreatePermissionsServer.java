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
	
	@Override
	public StreamObserver<permissionRequest> permissions(StreamObserver<permissionResponse> responseObserver) {
		return new StreamObserver<permissionRequest>() {
			@Override
			public void onNext(permissionRequest value) {
				System.out.println("started setting permissions method");
				System.out.println("The value is: "+value.getText().toString());
				if (permissions.size() > 0) {
					for (int i=0; i<permissions.size();i++) {
						System.out.println("The values in the for loop are: "+permissions.get(i));
						if (permissions.get(i).equals(value.getText().toString())) {
							permissionResponse reply = permissionResponse.newBuilder().setValue("Your permission has been set: " + value.getText()).build();
							responseObserver.onNext(reply);
							break;
						} else {
							permissionResponse reply = permissionResponse.newBuilder().setValue("This isn't a valid permission to set: "+value.getText()).build();
							responseObserver.onNext(reply);
							break;
						}
					}
				} else {
					permissionResponse reply = permissionResponse.newBuilder().setValue("There is no permissions available for setting right now").build();
					responseObserver.onNext(reply);
				}
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("the stream is done son");
			}
		};
	}
	
	@Override
	public void setPermissions(NewPermission request, StreamObserver<CreatedPermission> responseObserver) {
		String permission = request.getText();
		permissions.add(permission);
		String response = "Your permission has been created: ";
		CreatedPermission reply = CreatedPermission.newBuilder().setValue(response + permission).build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}
	
	@Override
	public void seePermissions(RequestPermissions request, StreamObserver<AllPermissions> responseObserver) {
		System.out.println("see permissions started");
		System.out.println("The request we got was: "+ request.getText());
		System.out.println("The size of permissions is: "+permissions.size());
		if(permissions.size() > 0) {
			for (int i = 0; i < permissions.size(); i++) {
				String permission = permissions.get(i);
				AllPermissions reply = AllPermissions.newBuilder().setValue(permission).build();
				System.out.println("this is the permission value that is being sent back to the client: " + reply.getValue().toString());
				responseObserver.onNext(reply);
			}
		} else {
			AllPermissions reply = AllPermissions.newBuilder().setValue("No permissions to see").build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
		System.out.println("see permissions finished");
	}
}
