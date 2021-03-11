package newHire.find.newhires;

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
    comments = "Source: see_new_hires.proto")
public final class newHireGrpc {

  private newHireGrpc() {}

  public static final String SERVICE_NAME = "newHire.newHire";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<newHire.find.newhires.MessageRequest,
      newHire.find.newhires.MessageReply> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendMessage",
      requestType = newHire.find.newhires.MessageRequest.class,
      responseType = newHire.find.newhires.MessageReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<newHire.find.newhires.MessageRequest,
      newHire.find.newhires.MessageReply> getSendMessageMethod() {
    io.grpc.MethodDescriptor<newHire.find.newhires.MessageRequest, newHire.find.newhires.MessageReply> getSendMessageMethod;
    if ((getSendMessageMethod = newHireGrpc.getSendMessageMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSendMessageMethod = newHireGrpc.getSendMessageMethod) == null) {
          newHireGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<newHire.find.newhires.MessageRequest, newHire.find.newhires.MessageReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "sendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.find.newhires.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.find.newhires.MessageReply.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("sendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
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
     *see all current new hires
     * </pre>
     */
    public void sendMessage(newHire.find.newhires.MessageRequest request,
        io.grpc.stub.StreamObserver<newHire.find.newhires.MessageReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                newHire.find.newhires.MessageRequest,
                newHire.find.newhires.MessageReply>(
                  this, METHODID_SEND_MESSAGE)))
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
     *see all current new hires
     * </pre>
     */
    public void sendMessage(newHire.find.newhires.MessageRequest request,
        io.grpc.stub.StreamObserver<newHire.find.newhires.MessageReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
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
     *see all current new hires
     * </pre>
     */
    public java.util.Iterator<newHire.find.newhires.MessageReply> sendMessage(
        newHire.find.newhires.MessageRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
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
  }

  private static final int METHODID_SEND_MESSAGE = 0;

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
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((newHire.find.newhires.MessageRequest) request,
              (io.grpc.stub.StreamObserver<newHire.find.newhires.MessageReply>) responseObserver);
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
      return newHire.find.newhires.newHire2ServiceImpl.getDescriptor();
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
              .build();
        }
      }
    }
    return result;
  }
}
