package newHire.create.permissions;

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
    comments = "Source: create_permissions.proto")
public final class newHireGrpc {

  private newHireGrpc() {}

  public static final String SERVICE_NAME = "newHire.newHire";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<newHire.create.permissions.permissionRequest,
      newHire.create.permissions.permissionResponse> getPermissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Permissions",
      requestType = newHire.create.permissions.permissionRequest.class,
      responseType = newHire.create.permissions.permissionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<newHire.create.permissions.permissionRequest,
      newHire.create.permissions.permissionResponse> getPermissionsMethod() {
    io.grpc.MethodDescriptor<newHire.create.permissions.permissionRequest, newHire.create.permissions.permissionResponse> getPermissionsMethod;
    if ((getPermissionsMethod = newHireGrpc.getPermissionsMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getPermissionsMethod = newHireGrpc.getPermissionsMethod) == null) {
          newHireGrpc.getPermissionsMethod = getPermissionsMethod = 
              io.grpc.MethodDescriptor.<newHire.create.permissions.permissionRequest, newHire.create.permissions.permissionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "Permissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.permissionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.permissionResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("Permissions"))
                  .build();
          }
        }
     }
     return getPermissionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.permissions.RequestPermissions,
      newHire.create.permissions.AllPermissions> getSeePermissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "seePermissions",
      requestType = newHire.create.permissions.RequestPermissions.class,
      responseType = newHire.create.permissions.AllPermissions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<newHire.create.permissions.RequestPermissions,
      newHire.create.permissions.AllPermissions> getSeePermissionsMethod() {
    io.grpc.MethodDescriptor<newHire.create.permissions.RequestPermissions, newHire.create.permissions.AllPermissions> getSeePermissionsMethod;
    if ((getSeePermissionsMethod = newHireGrpc.getSeePermissionsMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSeePermissionsMethod = newHireGrpc.getSeePermissionsMethod) == null) {
          newHireGrpc.getSeePermissionsMethod = getSeePermissionsMethod = 
              io.grpc.MethodDescriptor.<newHire.create.permissions.RequestPermissions, newHire.create.permissions.AllPermissions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "seePermissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.RequestPermissions.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.AllPermissions.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("seePermissions"))
                  .build();
          }
        }
     }
     return getSeePermissionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<newHire.create.permissions.NewPermission,
      newHire.create.permissions.CreatedPermission> getSetPermissionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setPermissions",
      requestType = newHire.create.permissions.NewPermission.class,
      responseType = newHire.create.permissions.CreatedPermission.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<newHire.create.permissions.NewPermission,
      newHire.create.permissions.CreatedPermission> getSetPermissionsMethod() {
    io.grpc.MethodDescriptor<newHire.create.permissions.NewPermission, newHire.create.permissions.CreatedPermission> getSetPermissionsMethod;
    if ((getSetPermissionsMethod = newHireGrpc.getSetPermissionsMethod) == null) {
      synchronized (newHireGrpc.class) {
        if ((getSetPermissionsMethod = newHireGrpc.getSetPermissionsMethod) == null) {
          newHireGrpc.getSetPermissionsMethod = getSetPermissionsMethod = 
              io.grpc.MethodDescriptor.<newHire.create.permissions.NewPermission, newHire.create.permissions.CreatedPermission>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "newHire.newHire", "setPermissions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.NewPermission.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  newHire.create.permissions.CreatedPermission.getDefaultInstance()))
                  .setSchemaDescriptor(new newHireMethodDescriptorSupplier("setPermissions"))
                  .build();
          }
        }
     }
     return getSetPermissionsMethod;
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
     *setting permissions 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<newHire.create.permissions.permissionRequest> permissions(
        io.grpc.stub.StreamObserver<newHire.create.permissions.permissionResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getPermissionsMethod(), responseObserver);
    }

    /**
     * <pre>
     *seeing all available permissions
     * </pre>
     */
    public void seePermissions(newHire.create.permissions.RequestPermissions request,
        io.grpc.stub.StreamObserver<newHire.create.permissions.AllPermissions> responseObserver) {
      asyncUnimplementedUnaryCall(getSeePermissionsMethod(), responseObserver);
    }

    /**
     * <pre>
     *creating a new permission
     * </pre>
     */
    public void setPermissions(newHire.create.permissions.NewPermission request,
        io.grpc.stub.StreamObserver<newHire.create.permissions.CreatedPermission> responseObserver) {
      asyncUnimplementedUnaryCall(getSetPermissionsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getPermissionsMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                newHire.create.permissions.permissionRequest,
                newHire.create.permissions.permissionResponse>(
                  this, METHODID_PERMISSIONS)))
          .addMethod(
            getSeePermissionsMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                newHire.create.permissions.RequestPermissions,
                newHire.create.permissions.AllPermissions>(
                  this, METHODID_SEE_PERMISSIONS)))
          .addMethod(
            getSetPermissionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                newHire.create.permissions.NewPermission,
                newHire.create.permissions.CreatedPermission>(
                  this, METHODID_SET_PERMISSIONS)))
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
     *setting permissions 
     * </pre>
     */
    public io.grpc.stub.StreamObserver<newHire.create.permissions.permissionRequest> permissions(
        io.grpc.stub.StreamObserver<newHire.create.permissions.permissionResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getPermissionsMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *seeing all available permissions
     * </pre>
     */
    public void seePermissions(newHire.create.permissions.RequestPermissions request,
        io.grpc.stub.StreamObserver<newHire.create.permissions.AllPermissions> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSeePermissionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *creating a new permission
     * </pre>
     */
    public void setPermissions(newHire.create.permissions.NewPermission request,
        io.grpc.stub.StreamObserver<newHire.create.permissions.CreatedPermission> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetPermissionsMethod(), getCallOptions()), request, responseObserver);
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
     *seeing all available permissions
     * </pre>
     */
    public java.util.Iterator<newHire.create.permissions.AllPermissions> seePermissions(
        newHire.create.permissions.RequestPermissions request) {
      return blockingServerStreamingCall(
          getChannel(), getSeePermissionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *creating a new permission
     * </pre>
     */
    public newHire.create.permissions.CreatedPermission setPermissions(newHire.create.permissions.NewPermission request) {
      return blockingUnaryCall(
          getChannel(), getSetPermissionsMethod(), getCallOptions(), request);
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
     *creating a new permission
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<newHire.create.permissions.CreatedPermission> setPermissions(
        newHire.create.permissions.NewPermission request) {
      return futureUnaryCall(
          getChannel().newCall(getSetPermissionsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEE_PERMISSIONS = 0;
  private static final int METHODID_SET_PERMISSIONS = 1;
  private static final int METHODID_PERMISSIONS = 2;

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
        case METHODID_SEE_PERMISSIONS:
          serviceImpl.seePermissions((newHire.create.permissions.RequestPermissions) request,
              (io.grpc.stub.StreamObserver<newHire.create.permissions.AllPermissions>) responseObserver);
          break;
        case METHODID_SET_PERMISSIONS:
          serviceImpl.setPermissions((newHire.create.permissions.NewPermission) request,
              (io.grpc.stub.StreamObserver<newHire.create.permissions.CreatedPermission>) responseObserver);
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
        case METHODID_PERMISSIONS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.permissions(
              (io.grpc.stub.StreamObserver<newHire.create.permissions.permissionResponse>) responseObserver);
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
      return newHire.create.permissions.newHire2ServiceImpl.getDescriptor();
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
              .addMethod(getPermissionsMethod())
              .addMethod(getSeePermissionsMethod())
              .addMethod(getSetPermissionsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
