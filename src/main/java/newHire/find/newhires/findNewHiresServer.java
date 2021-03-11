package newHire.find.newhires;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.find.newhires.newHireGrpc.newHireImplBase;

public class findNewHiresServer extends newHireImplBase {

	public static void main(String[] args) {
		findNewHiresServer classObj = new findNewHiresServer();
		int port = 50054;

		try {
			// setting the port to connect to, building & starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, waiting for rpc...");
			
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting service information to be passed into service info below
			String service_type = "_http._tcp.local.";
			String service_name = "Finding new hires";
			String service_description = "Service for finding new hires";
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
	
	//server side streaming rpc
	public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
		System.out.println("Your message has been recieved: " + request.getText());
		// creating an array and populating it with dummy data currently to test
		// functionality
		ArrayList<String> list = new ArrayList();
		list.add("John");
		list.add("Adam");
		list.add("Jess");
		// for loop for every name in the array create a reply and respond to the client
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			MessageReply reply = MessageReply.newBuilder().setValue(name).build();
			responseObserver.onNext(reply);
		}
		
		responseObserver.onCompleted();
	}

}
