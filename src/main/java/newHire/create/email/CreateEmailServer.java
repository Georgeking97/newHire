package newHire.create.email;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.email.newHireGrpc.newHireImplBase;

public class CreateEmailServer extends newHireImplBase {

	public static void main(String[] args) {
		// object of server class
		CreateEmailServer emailservice = new CreateEmailServer();
		// default port for connecting to
		int port = 50051;

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

	// Receiving and responding to the request, unary RPC
	@Override
	public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
		// telling the user I got their message
		System.out.println("Recieving message " + request.getText());
		// creating the response to send back to the user
		MessageReply reply = MessageReply.newBuilder().setValue("Hi out there " + request.getText()).build();
		// sending back the response to the user
		responseObserver.onNext(reply);
		// the action is completed and all actions are executed
		responseObserver.onCompleted();
	}
}
