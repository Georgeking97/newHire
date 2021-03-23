package newHire.create.permissions;

import java.util.Random;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import newHire.create.permissions.newHireGrpc.newHireBlockingStub;
import newHire.create.permissions.newHireGrpc.newHireStub;

public class permissionClient {
	private static newHireBlockingStub blockingStub;
	private static newHireStub asyncStub;

	public static void main(String[] args) {
		ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052).usePlaintext().build();
		blockingStub = newHireGrpc.newBlockingStub(channel);
		asyncStub = newHireGrpc.newStub(channel);
		permissions();
		setPermissions();
		seePermissions();
	}

	private static void seePermissions() {
		String requestMessage = "request";
		RequestPermissions request = RequestPermissions.newBuilder().setText(requestMessage).build();
		StreamObserver<AllPermissions> responseObserver = new StreamObserver<AllPermissions>() {

			@Override
			public void onNext(AllPermissions value) {
				System.out.println("Available permission: " + value.getValue());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("Stream is completed, all permissions show");
			}
		};
		asyncStub.seePermissions(request, responseObserver);
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void setPermissions() {
		String requestMessage = "permission";
		NewPermission request = NewPermission.newBuilder().setText(requestMessage).build();
		try {
			CreatedPermission response = blockingStub.setPermissions(request);
			System.out.println("The server responded back with: " + response.getValue());
		} catch (StatusRuntimeException e) {
			e.printStackTrace();
		}
	}

	private static void permissions() {
		String requestMessage = "request";
		StreamObserver<permissionResponse> responseObserver = new StreamObserver<permissionResponse>() {

			@Override
			public void onNext(permissionResponse value) {
				System.out.println("The response is: " + value.getValue());
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onCompleted() {
				System.out.println("The server has recieved all your requests");
			}
		};

		StreamObserver<permissionRequest> requestObserver = asyncStub.permissions(responseObserver);
		try {
			requestObserver.onNext(permissionRequest.newBuilder().setText(requestMessage).build());
			requestObserver.onCompleted();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
