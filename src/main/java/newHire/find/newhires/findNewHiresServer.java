package newHire.find.newhires;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

import java.io.IOException;
import java.util.ArrayList;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.find.newhires.newHireGrpc.newHireImplBase;

public class findNewHiresServer extends newHireImplBase {

	public static void main(String[] args) {
		findNewHiresServer classObj = new findNewHiresServer();
		int port = 50051;

		try {
			// setting the port to connect to, building & starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, waiting for rpc...");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

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
