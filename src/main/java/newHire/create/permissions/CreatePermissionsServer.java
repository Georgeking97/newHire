package newHire.create.permissions;

import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

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
		int port = 50052;

		try {
			// setting the port, adding the service and starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			// Providing feedback letting the user know the server started successfully
			System.out.println("Server started, awaiting RPC...");

			// get an instance of a JmDNS
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting service information to be passed into service info below
			String service_type = "_http._tcp.local.";
			String service_name = "Creating permissions";
			String service_description = "Service for creating permissions for a new hire";
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

	// Setting permissions bi-drectional RPC
	public StreamObserver<MessageRequest> sendMessage(StreamObserver<MessageReply> responseObserver) {
		return new StreamObserver<MessageRequest>() {

			String message;

			@Override
			public void onNext(MessageRequest value) {
				// letting the user know we recieved the message and what that message was
				System.out.println("Messaged recieved: " + value.getText());
				// making sure the message is in a string format for response
				message = value.toString();
				// creating the reply to send back to the user
				MessageReply reply = MessageReply.newBuilder().setValue(message).build();
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
