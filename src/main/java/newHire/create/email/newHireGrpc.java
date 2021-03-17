package newHire.create.email;

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
    comments = "Source: create_email.proto")
public final class newHireGrpc {

  private newHireGrpc() {}

  public static final String SERVICE_NAME = "newHire.newHire";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<newHire.create.email.EmailToCreate,
      newHire.create.email.EmailCreated> getCreateEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createEmail",
      requestType = newHire.create.email.EmailToCreate.class,
      responseType = newHire.create.email.EmailCreated.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<newHire.create.email.EmailToCreate,
      newHire.create.email.EmailCreated> getCreateEmailMethod() {
    io.grpc.MethodDescriptor<newHire.create.email.EmailToCreate, newHire.create.email.EmailCreated> getCreateEmailMethod;
    if ((getCreateEmailMethod = newHireGrpc.getCreateEmailMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getCreateEmailMethod = newHireGrpc.getCreateEmailMethod) == null) {
          newHireGrpc.getCreateEmailMethod = getCreateEmailMethod = 
              io.grpc.MethodDescriptor.<newHire.create.email.EmailToCreate, newHire.create.email.EmailCreated>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "createEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.EmailToCreate.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.EmailCreated.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("createEmail"))
                  .build();
          }
        }
     }
     return getCreateEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.email.EmailToDelete,
      newHire.create.email.EmailDeleted> getDeleteEmailMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteEmail",
      requestType = newHire.create.email.EmailToDelete.class,
      responseType = newHire.create.email.EmailDeleted.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<newHire.create.email.EmailToDelete,
      newHire.create.email.EmailDeleted> getDeleteEmailMethod() {
    io.grpc.MethodDescriptor<newHire.create.email.EmailToDelete, newHire.create.email.EmailDeleted> getDeleteEmailMethod;
    if ((getDeleteEmailMethod = newHireGrpc.getDeleteEmailMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getDeleteEmailMethod = newHireGrpc.getDeleteEmailMethod) == null) {
          newHireGrpc.getDeleteEmailMethod = getDeleteEmailMethod = 
              io.grpc.MethodDescriptor.<newHire.create.email.EmailToDelete, newHire.create.email.EmailDeleted>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "deleteEmail"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.EmailToDelete.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.EmailDeleted.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("deleteEmail"))
                  .build();
          }
        }
     }
     return getDeleteEmailMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.email.Emails,
      newHire.create.email.AllEmails> getSeeEmailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "seeEmails",
      requestType = newHire.create.email.Emails.class,
      responseType = newHire.create.email.AllEmails.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<newHire.create.email.Emails,
      newHire.create.email.AllEmails> getSeeEmailsMethod() {
    io.grpc.MethodDescriptor<newHire.create.email.Emails, newHire.create.email.AllEmails> getSeeEmailsMethod;
    if ((getSeeEmailsMethod = newHireGrpc.getSeeEmailsMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSeeEmailsMethod = newHireGrpc.getSeeEmailsMethod) == null) {
          newHireGrpc.getSeeEmailsMethod = getSeeEmailsMethod = 
              io.grpc.MethodDescriptor.<newHire.create.email.Emails, newHire.create.email.AllEmails>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "seeEmails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.Emails.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.email.AllEmails.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("seeEmails"))
                  .build();
          }
        }
     }
     return getSeeEmailsMethod;
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
     */
    public void createEmail(newHire.create.email.EmailToCreate request,
        io.grpc.stub.StreamObserver<newHire.create.email.EmailCreated> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateEmailMethod(), responseObserver);
    }

    /**
     */
    public void deleteEmail(newHire.create.email.EmailToDelete request,
        io.grpc.stub.StreamObserver<newHire.create.email.EmailDeleted> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteEmailMethod(), responseObserver);
    }

    /**
     */
    public void seeEmails(newHire.create.email.Emails request,
        io.grpc.stub.StreamObserver<newHire.create.email.AllEmails> responseObserver) {
      asyncUnimplementedUnaryCall(getSeeEmailsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateEmailMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                newHire.create.email.EmailToCreate,
                newHire.create.email.EmailCreated>(
                  this, METHODID_CREATE_EMAIL)))
          .addMethod(
            getDeleteEmailMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                newHire.create.email.EmailToDelete,
                newHire.create.email.EmailDeleted>(
                  this, METHODID_DELETE_EMAIL)))
          .addMethod(
            getSeeEmailsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                newHire.create.email.Emails,
                newHire.create.email.AllEmails>(
                  this, METHODID_SEE_EMAILS)))
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
     */
    public void createEmail(newHire.create.email.EmailToCreate request,
        io.grpc.stub.StreamObserver<newHire.create.email.EmailCreated> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteEmail(newHire.create.email.EmailToDelete request,
        io.grpc.stub.StreamObserver<newHire.create.email.EmailDeleted> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteEmailMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void seeEmails(newHire.create.email.Emails request,
        io.grpc.stub.StreamObserver<newHire.create.email.AllEmails> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSeeEmailsMethod(), getCallOptions()), request, responseObserver);
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
     */
    public newHire.create.email.EmailCreated createEmail(newHire.create.email.EmailToCreate request) {
      return blockingUnaryCall(
          getChannel(), getCreateEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public newHire.create.email.EmailDeleted deleteEmail(newHire.create.email.EmailToDelete request) {
      return blockingUnaryCall(
          getChannel(), getDeleteEmailMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<newHire.create.email.AllEmails> seeEmails(
        newHire.create.email.Emails request) {
      return blockingServerStreamingCall(
          getChannel(), getSeeEmailsMethod(), getCallOptions(), request);
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
     */
    public com.google.common.util.concurrent.ListenableFuture<newHire.create.email.EmailCreated> createEmail(
        newHire.create.email.EmailToCreate request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateEmailMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<newHire.create.email.EmailDeleted> deleteEmail(
        newHire.create.email.EmailToDelete request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteEmailMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_EMAIL = 0;
  private static final int METHODID_DELETE_EMAIL = 1;
  private static final int METHODID_SEE_EMAILS = 2;

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
        case METHODID_CREATE_EMAIL:
          serviceImpl.createEmail((newHire.create.email.EmailToCreate) request,
              (io.grpc.stub.StreamObserver<newHire.create.email.EmailCreated>) responseObserver);
          break;
        case METHODID_DELETE_EMAIL:
          serviceImpl.deleteEmail((newHire.create.email.EmailToDelete) request,
              (io.grpc.stub.StreamObserver<newHire.create.email.EmailDeleted>) responseObserver);
          break;
        case METHODID_SEE_EMAILS:
          serviceImpl.seeEmails((newHire.create.email.Emails) request,
              (io.grpc.stub.StreamObserver<newHire.create.email.AllEmails>) responseObserver);
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
      return newHire.create.email.newHire2ServiceImpl.getDescriptor();
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
              .addMethod(getCreateEmailMethod())
              .addMethod(getDeleteEmailMethod())
              .addMethod(getSeeEmailsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
