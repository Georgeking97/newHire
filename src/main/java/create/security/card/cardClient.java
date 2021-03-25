package create.security.card;

import java.util.Iterator;

import create.security.card.newHire1Grpc.newHire1BlockingStub;
import create.security.card.newHire1Grpc.newHire1Stub;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

public class cardClient {
	private static newHire1BlockingStub blockingStub;
	private static newHire1Stub asyncStub;

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50053).usePlaintext().build();
		blockingStub = newHire1Grpc.newBlockingStub(channel);
		asyncStub = newHire1Grpc.newStub(channel);

		create();
		see();
		delete();
		see();
	}

	private static void see() {
		String requestMessage = "request";
		RequestCards request = RequestCards.newBuilder().setText(requestMessage).build();
		StreamObserver<CardsReturned> responseObserver = new StreamObserver<CardsReturned>() {

			@Override
			public void onNext(CardsReturned value) {	
				System.out.println("Requested recieved, streaming cards");
				System.out.println(value.getValue());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream is completed, all cards show");
			}	
		};
		
		asyncStub.seeCards(request, responseObserver);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void delete() {
		String requestMessage = "George";
		SpecifyCard request = SpecifyCard.newBuilder().setText(requestMessage).build();
		try {
			CardDeleted response = blockingStub.deleteCard(request);
			System.out.println("card deleted: " + response.getValue());
		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	}

	private static void create() {
		String requestMessage1 = "George";
		String requestMessage2 = "George2";
		String requestMessage3 = "George3";
		StreamObserver<cardCreated> responseObserver = new StreamObserver<cardCreated>() {
			@Override
			public void onNext(cardCreated value) {
				System.out.println("Recieved message: " + value.getValue());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("All messages recieved, the card has been created");
			}
		};
		StreamObserver<card> requestObserver = asyncStub.createCard(responseObserver);
		try {
			requestObserver.onNext(card.newBuilder().setText(requestMessage1).build());
			Thread.sleep(500);
			requestObserver.onNext(card.newBuilder().setText(requestMessage2).build());
			Thread.sleep(500);
			requestObserver.onNext(card.newBuilder().setText(requestMessage3).build());
			Thread.sleep(500);
			requestObserver.onCompleted();
			Thread.sleep(10000);
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
