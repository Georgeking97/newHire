package newHire.create.email;

import java.util.Iterator;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import newHire.create.email.newHireGrpc.newHireBlockingStub;
import newHire.create.email.newHireGrpc.newHireStub;

public class EmailClient {

	private static newHireBlockingStub blockingStub;
	private static newHireStub asyncStub;

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
		blockingStub = newHireGrpc.newBlockingStub(channel);
		asyncStub = newHireGrpc.newStub(channel);

		sendMessage();
		deleteEmail();
		seeEmails();

	}

	public static void sendMessage() {
		String name = "George";
		MessageRequest request = MessageRequest.newBuilder().setText(name).build();
		MessageReply reply = blockingStub.sendMessage(request);

		String compare = "George@gmail.com";
		if (compare.equals(reply.getValue())) {
			System.out.println("Create email method passed its test");
		} else {
			System.out.println("Create email method failed its test");
		}
	}

	public static void deleteEmail() {
		String name = "GeorgeKing";
		EmailToDelete request = EmailToDelete.newBuilder().setText(name).build();
		EmailDeleted reply = blockingStub.deleteEmail(request);
		String compare = "No email was found";
		System.out.println(reply.getValue());
		if (compare.equals(reply.getValue())) {
			System.out.println("Delete email method passed its test");
		} else {
			System.out.println("Delete email method failed its test");
		}
	}

	public static void seeEmails() {
		String compare = "George@gmail.com";
		Emails request = Emails.newBuilder().setText("test").build();
		try {
			Iterator<AllEmails> responces = blockingStub.seeEmails(request);
			while (responces.hasNext()) {
				AllEmails temp = responces.next();
				if (compare.equals(temp.getValue())) {
					System.out.println("See emails method passed its test");
				} else {
					System.out.println("See emails method failed its test");
				}
			}
		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	}
}
