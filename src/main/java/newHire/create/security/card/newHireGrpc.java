package newHire.create.security.card;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: create_security_card.proto")
public final class newHireGrpc {

  private newHireGrpc() {}

  public static final String SERVICE_NAME = "newHire.newHire";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<newHire.create.security.card.MessageRequest,
      newHire.create.security.card.MessageReply> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = newHire.create.security.card.MessageRequest.class,
      responseType = newHire.create.security.card.MessageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<newHire.create.security.card.MessageRequest,
      newHire.create.security.card.MessageReply> getSendMessageMethod() {
    io.grpc.MethodDescriptor<newHire.create.security.card.MessageRequest, newHire.create.security.card.MessageReply> getSendMessageMethod;
    if ((getSendMessageMethod = newHireGrpc.getSendMessageMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSendMessageMethod = newHireGrpc.getSendMessageMethod) == null) {
          newHireGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<newHire.create.security.card.MessageRequest, newHire.create.security.card.MessageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.MessageReply.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.security.card.RequestCards,
      newHire.create.security.card.CardsReturned> getSeeCardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "seeCards",
      requestType = newHire.create.security.card.RequestCards.class,
      responseType = newHire.create.security.card.CardsReturned.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<newHire.create.security.card.RequestCards,
      newHire.create.security.card.CardsReturned> getSeeCardsMethod() {
    io.grpc.MethodDescriptor<newHire.create.security.card.RequestCards, newHire.create.security.card.CardsReturned> getSeeCardsMethod;
    if ((getSeeCardsMethod = newHireGrpc.getSeeCardsMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSeeCardsMethod = newHireGrpc.getSeeCardsMethod) == null) {
          newHireGrpc.getSeeCardsMethod = getSeeCardsMethod = 
              io.grpc.MethodDescriptor.<newHire.create.security.card.RequestCards, newHire.create.security.card.CardsReturned>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "seeCards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.RequestCards.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.CardsReturned.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("seeCards"))
                  .build();
          }
        }
     }
     return getSeeCardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.security.card.SpecifyCard,
      newHire.create.security.card.CardDeleted> getDeleteCardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteCard",
      requestType = newHire.create.security.card.SpecifyCard.class,
      responseType = newHire.create.security.card.CardDeleted.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<newHire.create.security.card.SpecifyCard,
      newHire.create.security.card.CardDeleted> getDeleteCardMethod() {
    io.grpc.MethodDescriptor<newHire.create.security.card.SpecifyCard, newHire.create.security.card.CardDeleted> getDeleteCardMethod;
    if ((getDeleteCardMethod = newHireGrpc.getDeleteCardMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getDeleteCardMethod = newHireGrpc.getDeleteCardMethod) == null) {
          newHireGrpc.getDeleteCardMethod = getDeleteCardMethod = 
              io.grpc.MethodDescriptor.<newHire.create.security.card.SpecifyCard, newHire.create.security.card.CardDeleted>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "deleteCard"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.SpecifyCard.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.security.card.CardDeleted.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("deleteCard"))
                  .build();
          }
        }
     }
     return getDeleteCardMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static newHireStub newStub(io.grpc.Channel channel) {
    return new newHireStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static newHireBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new newHireBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static newHireFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new newHireFutureStub(channel);
  }

  /**
   */
  public static abstract class newHireImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *creating the security card 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<newHire.create.security.card.MessageRequest> sendMessage(
        io.grpc.stub.StreamObserver<newHire.create.security.card.MessageReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getSendMessageMethod(), responseObserver);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public void seeCards(newHire.create.security.card.RequestCards request,
        io.grpc.stub.StreamObserver<newHire.create.security.card.CardsReturned> responseObserver) {
      asyncUnimplementedUnaryCall(getSeeCardsMethod(), responseObserver);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public void deleteCard(newHire.create.security.card.SpecifyCard request,
        io.grpc.stub.StreamObserver<newHire.create.security.card.CardDeleted> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteCardMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                newHire.create.security.card.MessageRequest,
                newHire.create.security.card.MessageReply>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getSeeCardsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                newHire.create.security.card.RequestCards,
                newHire.create.security.card.CardsReturned>(
                  this, METHODID_SEE_CARDS)))
          .addMethod(
            getDeleteCardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                newHire.create.security.card.SpecifyCard,
                newHire.create.security.card.CardDeleted>(
                  this, METHODID_DELETE_CARD)))
          .build();
    }
  }

  /**
   */
  public static final class newHireStub extends io.grpc.stub.AbstractStub<newHireStub> {
    private newHireStub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHireStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHireStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHireStub(channel, callOptions);
    }

    /**
     * <pre>
     *creating the security card 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<newHire.create.security.card.MessageRequest> sendMessage(
        io.grpc.stub.StreamObserver<newHire.create.security.card.MessageReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public void seeCards(newHire.create.security.card.RequestCards request,
        io.grpc.stub.StreamObserver<newHire.create.security.card.CardsReturned> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSeeCardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public void deleteCard(newHire.create.security.card.SpecifyCard request,
        io.grpc.stub.StreamObserver<newHire.create.security.card.CardDeleted> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteCardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class newHireBlockingStub extends io.grpc.stub.AbstractStub<newHireBlockingStub> {
    private newHireBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHireBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHireBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHireBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public newHire.create.security.card.CardsReturned seeCards(newHire.create.security.card.RequestCards request) {
      return blockingUnaryCall(
          getChannel(), getSeeCardsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public newHire.create.security.card.CardDeleted deleteCard(newHire.create.security.card.SpecifyCard request) {
      return blockingUnaryCall(
          getChannel(), getDeleteCardMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class newHireFutureStub extends io.grpc.stub.AbstractStub<newHireFutureStub> {
    private newHireFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHireFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHireFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHireFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<newHire.create.security.card.CardsReturned> seeCards(
        newHire.create.security.card.RequestCards request) {
      return futureUnaryCall(
          getChannel().newCall(getSeeCardsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<newHire.create.security.card.CardDeleted> deleteCard(
        newHire.create.security.card.SpecifyCard request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteCardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEE_CARDS = 0;
  private static final int METHODID_DELETE_CARD = 1;
  private static final int METHODID_SEND_MESSAGE = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final newHireImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(newHireImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEE_CARDS:
          serviceImpl.seeCards((newHire.create.security.card.RequestCards) request,
              (io.grpc.stub.StreamObserver<newHire.create.security.card.CardsReturned>) responseObserver);
          break;
        case METHODID_DELETE_CARD:
          serviceImpl.deleteCard((newHire.create.security.card.SpecifyCard) request,
              (io.grpc.stub.StreamObserver<newHire.create.security.card.CardDeleted>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sendMessage(
              (io.grpc.stub.StreamObserver<newHire.create.security.card.MessageReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class newHireBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    newHireBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return newHire.create.security.card.newHire2ServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("newHire");
    }
  }

  private static final class newHireFileDescriptorSupplier
      extends newHireBaseDescriptorSupplier {
    newHireFileDescriptorSupplier() {}
  }

  private static final class newHireMethodDescriptorSupplier
      extends newHireBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    newHireMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (newHireGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new newHireFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .addMethod(getSeeCardsMethod())
              .addMethod(getDeleteCardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
