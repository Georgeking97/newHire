package create.security.card;

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
public final class newHire1Grpc {

  private newHire1Grpc() {}

  public static final String SERVICE_NAME = "newHire1.newHire1";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<create.security.card.card,
      create.security.card.cardCreated> getCreateCardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createCard",
      requestType = create.security.card.card.class,
      responseType = create.security.card.cardCreated.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<create.security.card.card,
      create.security.card.cardCreated> getCreateCardMethod() {
    io.grpc.MethodDescriptor<create.security.card.card, create.security.card.cardCreated> getCreateCardMethod;
    if ((getCreateCardMethod = newHire1Grpc.getCreateCardMethod) == null) {
      synchronized (newHire1Grpc.class) {
        if ((getCreateCardMethod = newHire1Grpc.getCreateCardMethod) == null) {
          newHire1Grpc.getCreateCardMethod = getCreateCardMethod = 
              io.grpc.MethodDescriptor.<create.security.card.card, create.security.card.cardCreated>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire1.newHire1", "createCard"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.card.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.cardCreated.getDefaultInstance()))
                  .setSchemaDescriptor(new newHire1MethodDescriptorSupplier("createCard"))
                  .build();
          }
        }
     }
     return getCreateCardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<create.security.card.RequestCards,
      create.security.card.CardsReturned> getSeeCardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "seeCards",
      requestType = create.security.card.RequestCards.class,
      responseType = create.security.card.CardsReturned.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<create.security.card.RequestCards,
      create.security.card.CardsReturned> getSeeCardsMethod() {
    io.grpc.MethodDescriptor<create.security.card.RequestCards, create.security.card.CardsReturned> getSeeCardsMethod;
    if ((getSeeCardsMethod = newHire1Grpc.getSeeCardsMethod) == null) {
      synchronized (newHire1Grpc.class) {
        if ((getSeeCardsMethod = newHire1Grpc.getSeeCardsMethod) == null) {
          newHire1Grpc.getSeeCardsMethod = getSeeCardsMethod = 
              io.grpc.MethodDescriptor.<create.security.card.RequestCards, create.security.card.CardsReturned>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire1.newHire1", "seeCards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.RequestCards.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.CardsReturned.getDefaultInstance()))
                  .setSchemaDescriptor(new newHire1MethodDescriptorSupplier("seeCards"))
                  .build();
          }
        }
     }
     return getSeeCardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<create.security.card.SpecifyCard,
      create.security.card.CardDeleted> getDeleteCardMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteCard",
      requestType = create.security.card.SpecifyCard.class,
      responseType = create.security.card.CardDeleted.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<create.security.card.SpecifyCard,
      create.security.card.CardDeleted> getDeleteCardMethod() {
    io.grpc.MethodDescriptor<create.security.card.SpecifyCard, create.security.card.CardDeleted> getDeleteCardMethod;
    if ((getDeleteCardMethod = newHire1Grpc.getDeleteCardMethod) == null) {
      synchronized (newHire1Grpc.class) {
        if ((getDeleteCardMethod = newHire1Grpc.getDeleteCardMethod) == null) {
          newHire1Grpc.getDeleteCardMethod = getDeleteCardMethod = 
              io.grpc.MethodDescriptor.<create.security.card.SpecifyCard, create.security.card.CardDeleted>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire1.newHire1", "deleteCard"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.SpecifyCard.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  create.security.card.CardDeleted.getDefaultInstance()))
                  .setSchemaDescriptor(new newHire1MethodDescriptorSupplier("deleteCard"))
                  .build();
          }
        }
     }
     return getDeleteCardMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static newHire1Stub newStub(io.grpc.Channel channel) {
    return new newHire1Stub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static newHire1BlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new newHire1BlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static newHire1FutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new newHire1FutureStub(channel);
  }

  /**
   */
  public static abstract class newHire1ImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *creating the security card 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<create.security.card.card> createCard(
        io.grpc.stub.StreamObserver<create.security.card.cardCreated> responseObserver) {
      return asyncUnimplementedStreamingCall(getCreateCardMethod(), responseObserver);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public void seeCards(create.security.card.RequestCards request,
        io.grpc.stub.StreamObserver<create.security.card.CardsReturned> responseObserver) {
      asyncUnimplementedUnaryCall(getSeeCardsMethod(), responseObserver);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public void deleteCard(create.security.card.SpecifyCard request,
        io.grpc.stub.StreamObserver<create.security.card.CardDeleted> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteCardMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateCardMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                create.security.card.card,
                create.security.card.cardCreated>(
                  this, METHODID_CREATE_CARD)))
          .addMethod(
            getSeeCardsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                create.security.card.RequestCards,
                create.security.card.CardsReturned>(
                  this, METHODID_SEE_CARDS)))
          .addMethod(
            getDeleteCardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                create.security.card.SpecifyCard,
                create.security.card.CardDeleted>(
                  this, METHODID_DELETE_CARD)))
          .build();
    }
  }

  /**
   */
  public static final class newHire1Stub extends io.grpc.stub.AbstractStub<newHire1Stub> {
    private newHire1Stub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHire1Stub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHire1Stub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHire1Stub(channel, callOptions);
    }

    /**
     * <pre>
     *creating the security card 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<create.security.card.card> createCard(
        io.grpc.stub.StreamObserver<create.security.card.cardCreated> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getCreateCardMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public void seeCards(create.security.card.RequestCards request,
        io.grpc.stub.StreamObserver<create.security.card.CardsReturned> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSeeCardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public void deleteCard(create.security.card.SpecifyCard request,
        io.grpc.stub.StreamObserver<create.security.card.CardDeleted> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteCardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class newHire1BlockingStub extends io.grpc.stub.AbstractStub<newHire1BlockingStub> {
    private newHire1BlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHire1BlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHire1BlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHire1BlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *seeing all security cards
     * </pre>
     */
    public java.util.Iterator<create.security.card.CardsReturned> seeCards(
        create.security.card.RequestCards request) {
      return blockingServerStreamingCall(
          getChannel(), getSeeCardsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public create.security.card.CardDeleted deleteCard(create.security.card.SpecifyCard request) {
      return blockingUnaryCall(
          getChannel(), getDeleteCardMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class newHire1FutureStub extends io.grpc.stub.AbstractStub<newHire1FutureStub> {
    private newHire1FutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private newHire1FutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected newHire1FutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new newHire1FutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *delete security card
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<create.security.card.CardDeleted> deleteCard(
        create.security.card.SpecifyCard request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteCardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEE_CARDS = 0;
  private static final int METHODID_DELETE_CARD = 1;
  private static final int METHODID_CREATE_CARD = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final newHire1ImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(newHire1ImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEE_CARDS:
          serviceImpl.seeCards((create.security.card.RequestCards) request,
              (io.grpc.stub.StreamObserver<create.security.card.CardsReturned>) responseObserver);
          break;
        case METHODID_DELETE_CARD:
          serviceImpl.deleteCard((create.security.card.SpecifyCard) request,
              (io.grpc.stub.StreamObserver<create.security.card.CardDeleted>) responseObserver);
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
        case METHODID_CREATE_CARD:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.createCard(
              (io.grpc.stub.StreamObserver<create.security.card.cardCreated>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class newHire1BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    newHire1BaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return create.security.card.newHire2ServiceImpl.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("newHire1");
    }
  }

  private static final class newHire1FileDescriptorSupplier
      extends newHire1BaseDescriptorSupplier {
    newHire1FileDescriptorSupplier() {}
  }

  private static final class newHire1MethodDescriptorSupplier
      extends newHire1BaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    newHire1MethodDescriptorSupplier(String methodName) {
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
      synchronized (newHire1Grpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new newHire1FileDescriptorSupplier())
              .addMethod(getCreateCardMethod())
              .addMethod(getSeeCardsMethod())
              .addMethod(getDeleteCardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
