package highflip.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 *&#47;/////////////////////////////////////////////////////////////////////////////
 * SERVICE
 * //////////////////////////////////////////////////////////////////////////////
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.49.0)",
    comments = "Source: highflip.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HighFlipGrpc {

  private HighFlipGrpc() {}

  public static final String SERVICE_NAME = "highflip.v1.HighFlip";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigSetRequest,
      highflip.v1.Highflip.Void> getSetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setConfig",
      requestType = highflip.v1.Highflip.ConfigSetRequest.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigSetRequest,
      highflip.v1.Highflip.Void> getSetConfigMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigSetRequest, highflip.v1.Highflip.Void> getSetConfigMethod;
    if ((getSetConfigMethod = HighFlipGrpc.getSetConfigMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getSetConfigMethod = HighFlipGrpc.getSetConfigMethod) == null) {
          HighFlipGrpc.getSetConfigMethod = getSetConfigMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.ConfigSetRequest, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigSetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("setConfig"))
              .build();
        }
      }
    }
    return getSetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId,
      highflip.v1.Highflip.ConfigGetResponse> getGetConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getConfig",
      requestType = highflip.v1.Highflip.ConfigId.class,
      responseType = highflip.v1.Highflip.ConfigGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId,
      highflip.v1.Highflip.ConfigGetResponse> getGetConfigMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId, highflip.v1.Highflip.ConfigGetResponse> getGetConfigMethod;
    if ((getGetConfigMethod = HighFlipGrpc.getGetConfigMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetConfigMethod = HighFlipGrpc.getGetConfigMethod) == null) {
          HighFlipGrpc.getGetConfigMethod = getGetConfigMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.ConfigId, highflip.v1.Highflip.ConfigGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getConfig"))
              .build();
        }
      }
    }
    return getGetConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigListRequest,
      highflip.v1.Highflip.ConfigListResponse> getListConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listConfig",
      requestType = highflip.v1.Highflip.ConfigListRequest.class,
      responseType = highflip.v1.Highflip.ConfigListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigListRequest,
      highflip.v1.Highflip.ConfigListResponse> getListConfigMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigListRequest, highflip.v1.Highflip.ConfigListResponse> getListConfigMethod;
    if ((getListConfigMethod = HighFlipGrpc.getListConfigMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListConfigMethod = HighFlipGrpc.getListConfigMethod) == null) {
          HighFlipGrpc.getListConfigMethod = getListConfigMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.ConfigListRequest, highflip.v1.Highflip.ConfigListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listConfig"))
              .build();
        }
      }
    }
    return getListConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId,
      highflip.v1.Highflip.Void> getDeleteConfigMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteConfig",
      requestType = highflip.v1.Highflip.ConfigId.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId,
      highflip.v1.Highflip.Void> getDeleteConfigMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.ConfigId, highflip.v1.Highflip.Void> getDeleteConfigMethod;
    if ((getDeleteConfigMethod = HighFlipGrpc.getDeleteConfigMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getDeleteConfigMethod = HighFlipGrpc.getDeleteConfigMethod) == null) {
          HighFlipGrpc.getDeleteConfigMethod = getDeleteConfigMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.ConfigId, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteConfig"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.ConfigId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("deleteConfig"))
              .build();
        }
      }
    }
    return getDeleteConfigMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.Void,
      highflip.v1.Highflip.PlatformGetResponse> getGetPlatformMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPlatform",
      requestType = highflip.v1.Highflip.Void.class,
      responseType = highflip.v1.Highflip.PlatformGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.Void,
      highflip.v1.Highflip.PlatformGetResponse> getGetPlatformMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.Void, highflip.v1.Highflip.PlatformGetResponse> getGetPlatformMethod;
    if ((getGetPlatformMethod = HighFlipGrpc.getGetPlatformMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetPlatformMethod = HighFlipGrpc.getGetPlatformMethod) == null) {
          HighFlipGrpc.getGetPlatformMethod = getGetPlatformMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.Void, highflip.v1.Highflip.PlatformGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPlatform"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PlatformGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getPlatform"))
              .build();
        }
      }
    }
    return getGetPlatformMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformListRequest,
      highflip.v1.Highflip.PlatformListResponse> getListPlatformMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listPlatform",
      requestType = highflip.v1.Highflip.PlatformListRequest.class,
      responseType = highflip.v1.Highflip.PlatformListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformListRequest,
      highflip.v1.Highflip.PlatformListResponse> getListPlatformMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformListRequest, highflip.v1.Highflip.PlatformListResponse> getListPlatformMethod;
    if ((getListPlatformMethod = HighFlipGrpc.getListPlatformMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListPlatformMethod = HighFlipGrpc.getListPlatformMethod) == null) {
          HighFlipGrpc.getListPlatformMethod = getListPlatformMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PlatformListRequest, highflip.v1.Highflip.PlatformListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listPlatform"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PlatformListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PlatformListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listPlatform"))
              .build();
        }
      }
    }
    return getListPlatformMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformMatchRequest,
      highflip.v1.Highflip.PlatformMatchResponse> getMatchPlatformMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "matchPlatform",
      requestType = highflip.v1.Highflip.PlatformMatchRequest.class,
      responseType = highflip.v1.Highflip.PlatformMatchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformMatchRequest,
      highflip.v1.Highflip.PlatformMatchResponse> getMatchPlatformMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PlatformMatchRequest, highflip.v1.Highflip.PlatformMatchResponse> getMatchPlatformMethod;
    if ((getMatchPlatformMethod = HighFlipGrpc.getMatchPlatformMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getMatchPlatformMethod = HighFlipGrpc.getMatchPlatformMethod) == null) {
          HighFlipGrpc.getMatchPlatformMethod = getMatchPlatformMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PlatformMatchRequest, highflip.v1.Highflip.PlatformMatchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "matchPlatform"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PlatformMatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PlatformMatchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("matchPlatform"))
              .build();
        }
      }
    }
    return getMatchPlatformMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobCreateRequest,
      highflip.v1.Highflip.JobId> getCreateJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createJob",
      requestType = highflip.v1.Highflip.JobCreateRequest.class,
      responseType = highflip.v1.Highflip.JobId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobCreateRequest,
      highflip.v1.Highflip.JobId> getCreateJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobCreateRequest, highflip.v1.Highflip.JobId> getCreateJobMethod;
    if ((getCreateJobMethod = HighFlipGrpc.getCreateJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getCreateJobMethod = HighFlipGrpc.getCreateJobMethod) == null) {
          HighFlipGrpc.getCreateJobMethod = getCreateJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobCreateRequest, highflip.v1.Highflip.JobId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobCreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobId.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("createJob"))
              .build();
        }
      }
    }
    return getCreateJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.JobGetResponse> getGetJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getJob",
      requestType = highflip.v1.Highflip.JobId.class,
      responseType = highflip.v1.Highflip.JobGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.JobGetResponse> getGetJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId, highflip.v1.Highflip.JobGetResponse> getGetJobMethod;
    if ((getGetJobMethod = HighFlipGrpc.getGetJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetJobMethod = HighFlipGrpc.getGetJobMethod) == null) {
          HighFlipGrpc.getGetJobMethod = getGetJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobId, highflip.v1.Highflip.JobGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getJob"))
              .build();
        }
      }
    }
    return getGetJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.JobCheckResponse> getCheckJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkJob",
      requestType = highflip.v1.Highflip.JobId.class,
      responseType = highflip.v1.Highflip.JobCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.JobCheckResponse> getCheckJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId, highflip.v1.Highflip.JobCheckResponse> getCheckJobMethod;
    if ((getCheckJobMethod = HighFlipGrpc.getCheckJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getCheckJobMethod = HighFlipGrpc.getCheckJobMethod) == null) {
          HighFlipGrpc.getCheckJobMethod = getCheckJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobId, highflip.v1.Highflip.JobCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("checkJob"))
              .build();
        }
      }
    }
    return getCheckJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.Void> getDeleteJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteJob",
      requestType = highflip.v1.Highflip.JobId.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId,
      highflip.v1.Highflip.Void> getDeleteJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobId, highflip.v1.Highflip.Void> getDeleteJobMethod;
    if ((getDeleteJobMethod = HighFlipGrpc.getDeleteJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getDeleteJobMethod = HighFlipGrpc.getDeleteJobMethod) == null) {
          HighFlipGrpc.getDeleteJobMethod = getDeleteJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobId, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("deleteJob"))
              .build();
        }
      }
    }
    return getDeleteJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobListRequest,
      highflip.v1.Highflip.JobListResponse> getListJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listJob",
      requestType = highflip.v1.Highflip.JobListRequest.class,
      responseType = highflip.v1.Highflip.JobListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobListRequest,
      highflip.v1.Highflip.JobListResponse> getListJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobListRequest, highflip.v1.Highflip.JobListResponse> getListJobMethod;
    if ((getListJobMethod = HighFlipGrpc.getListJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListJobMethod = HighFlipGrpc.getListJobMethod) == null) {
          HighFlipGrpc.getListJobMethod = getListJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobListRequest, highflip.v1.Highflip.JobListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listJob"))
              .build();
        }
      }
    }
    return getListJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobControlRequest,
      highflip.v1.Highflip.Void> getControlJobMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "controlJob",
      requestType = highflip.v1.Highflip.JobControlRequest.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobControlRequest,
      highflip.v1.Highflip.Void> getControlJobMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobControlRequest, highflip.v1.Highflip.Void> getControlJobMethod;
    if ((getControlJobMethod = HighFlipGrpc.getControlJobMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getControlJobMethod = HighFlipGrpc.getControlJobMethod) == null) {
          HighFlipGrpc.getControlJobMethod = getControlJobMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobControlRequest, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "controlJob"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobControlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("controlJob"))
              .build();
        }
      }
    }
    return getControlJobMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.JobLogRequest,
      highflip.v1.Highflip.JobLogResponse> getGetJobLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getJobLog",
      requestType = highflip.v1.Highflip.JobLogRequest.class,
      responseType = highflip.v1.Highflip.JobLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.JobLogRequest,
      highflip.v1.Highflip.JobLogResponse> getGetJobLogMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.JobLogRequest, highflip.v1.Highflip.JobLogResponse> getGetJobLogMethod;
    if ((getGetJobLogMethod = HighFlipGrpc.getGetJobLogMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetJobLogMethod = HighFlipGrpc.getGetJobLogMethod) == null) {
          HighFlipGrpc.getGetJobLogMethod = getGetJobLogMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.JobLogRequest, highflip.v1.Highflip.JobLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getJobLog"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.JobLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getJobLog"))
              .build();
        }
      }
    }
    return getGetJobLogMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskListRequest,
      highflip.v1.Highflip.TaskListResponse> getListTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listTask",
      requestType = highflip.v1.Highflip.TaskListRequest.class,
      responseType = highflip.v1.Highflip.TaskListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskListRequest,
      highflip.v1.Highflip.TaskListResponse> getListTaskMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskListRequest, highflip.v1.Highflip.TaskListResponse> getListTaskMethod;
    if ((getListTaskMethod = HighFlipGrpc.getListTaskMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListTaskMethod = HighFlipGrpc.getListTaskMethod) == null) {
          HighFlipGrpc.getListTaskMethod = getListTaskMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.TaskListRequest, highflip.v1.Highflip.TaskListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listTask"))
              .build();
        }
      }
    }
    return getListTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId,
      highflip.v1.Highflip.TaskGetResponse> getGetTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTask",
      requestType = highflip.v1.Highflip.TaskId.class,
      responseType = highflip.v1.Highflip.TaskGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId,
      highflip.v1.Highflip.TaskGetResponse> getGetTaskMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId, highflip.v1.Highflip.TaskGetResponse> getGetTaskMethod;
    if ((getGetTaskMethod = HighFlipGrpc.getGetTaskMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetTaskMethod = HighFlipGrpc.getGetTaskMethod) == null) {
          HighFlipGrpc.getGetTaskMethod = getGetTaskMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.TaskId, highflip.v1.Highflip.TaskGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getTask"))
              .build();
        }
      }
    }
    return getGetTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId,
      highflip.v1.Highflip.TaskCheckResponse> getCheckTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkTask",
      requestType = highflip.v1.Highflip.TaskId.class,
      responseType = highflip.v1.Highflip.TaskCheckResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId,
      highflip.v1.Highflip.TaskCheckResponse> getCheckTaskMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskId, highflip.v1.Highflip.TaskCheckResponse> getCheckTaskMethod;
    if ((getCheckTaskMethod = HighFlipGrpc.getCheckTaskMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getCheckTaskMethod = HighFlipGrpc.getCheckTaskMethod) == null) {
          HighFlipGrpc.getCheckTaskMethod = getCheckTaskMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.TaskId, highflip.v1.Highflip.TaskCheckResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskCheckResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("checkTask"))
              .build();
        }
      }
    }
    return getCheckTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskControlRequest,
      highflip.v1.Highflip.Void> getControlTaskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "controlTask",
      requestType = highflip.v1.Highflip.TaskControlRequest.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskControlRequest,
      highflip.v1.Highflip.Void> getControlTaskMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskControlRequest, highflip.v1.Highflip.Void> getControlTaskMethod;
    if ((getControlTaskMethod = HighFlipGrpc.getControlTaskMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getControlTaskMethod = HighFlipGrpc.getControlTaskMethod) == null) {
          HighFlipGrpc.getControlTaskMethod = getControlTaskMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.TaskControlRequest, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "controlTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskControlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("controlTask"))
              .build();
        }
      }
    }
    return getControlTaskMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskLogRequest,
      highflip.v1.Highflip.TaskLogResponse> getGetTaskLogMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getTaskLog",
      requestType = highflip.v1.Highflip.TaskLogRequest.class,
      responseType = highflip.v1.Highflip.TaskLogResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskLogRequest,
      highflip.v1.Highflip.TaskLogResponse> getGetTaskLogMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.TaskLogRequest, highflip.v1.Highflip.TaskLogResponse> getGetTaskLogMethod;
    if ((getGetTaskLogMethod = HighFlipGrpc.getGetTaskLogMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetTaskLogMethod = HighFlipGrpc.getGetTaskLogMethod) == null) {
          HighFlipGrpc.getGetTaskLogMethod = getGetTaskLogMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.TaskLogRequest, highflip.v1.Highflip.TaskLogResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getTaskLog"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskLogRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.TaskLogResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getTaskLog"))
              .build();
        }
      }
    }
    return getGetTaskLogMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.DataListRequest,
      highflip.v1.Highflip.DataListResponse> getListDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listData",
      requestType = highflip.v1.Highflip.DataListRequest.class,
      responseType = highflip.v1.Highflip.DataListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.DataListRequest,
      highflip.v1.Highflip.DataListResponse> getListDataMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.DataListRequest, highflip.v1.Highflip.DataListResponse> getListDataMethod;
    if ((getListDataMethod = HighFlipGrpc.getListDataMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListDataMethod = HighFlipGrpc.getListDataMethod) == null) {
          HighFlipGrpc.getListDataMethod = getListDataMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.DataListRequest, highflip.v1.Highflip.DataListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listData"))
              .build();
        }
      }
    }
    return getListDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId,
      highflip.v1.Highflip.DataGetResponse> getGetDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getData",
      requestType = highflip.v1.Highflip.DataId.class,
      responseType = highflip.v1.Highflip.DataGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId,
      highflip.v1.Highflip.DataGetResponse> getGetDataMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId, highflip.v1.Highflip.DataGetResponse> getGetDataMethod;
    if ((getGetDataMethod = HighFlipGrpc.getGetDataMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetDataMethod = HighFlipGrpc.getGetDataMethod) == null) {
          HighFlipGrpc.getGetDataMethod = getGetDataMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.DataId, highflip.v1.Highflip.DataGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getData"))
              .build();
        }
      }
    }
    return getGetDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPushRequest,
      highflip.v1.Highflip.DataId> getPushDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pushData",
      requestType = highflip.v1.Highflip.DataPushRequest.class,
      responseType = highflip.v1.Highflip.DataId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPushRequest,
      highflip.v1.Highflip.DataId> getPushDataMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPushRequest, highflip.v1.Highflip.DataId> getPushDataMethod;
    if ((getPushDataMethod = HighFlipGrpc.getPushDataMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getPushDataMethod = HighFlipGrpc.getPushDataMethod) == null) {
          HighFlipGrpc.getPushDataMethod = getPushDataMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.DataPushRequest, highflip.v1.Highflip.DataId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pushData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataPushRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataId.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("pushData"))
              .build();
        }
      }
    }
    return getPushDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPullRequest,
      highflip.v1.Highflip.DataPullResponse> getPullDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "pullData",
      requestType = highflip.v1.Highflip.DataPullRequest.class,
      responseType = highflip.v1.Highflip.DataPullResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPullRequest,
      highflip.v1.Highflip.DataPullResponse> getPullDataMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.DataPullRequest, highflip.v1.Highflip.DataPullResponse> getPullDataMethod;
    if ((getPullDataMethod = HighFlipGrpc.getPullDataMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getPullDataMethod = HighFlipGrpc.getPullDataMethod) == null) {
          HighFlipGrpc.getPullDataMethod = getPullDataMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.DataPullRequest, highflip.v1.Highflip.DataPullResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "pullData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataPullRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataPullResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("pullData"))
              .build();
        }
      }
    }
    return getPullDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId,
      highflip.v1.Highflip.Void> getDeleteDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteData",
      requestType = highflip.v1.Highflip.DataId.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId,
      highflip.v1.Highflip.Void> getDeleteDataMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.DataId, highflip.v1.Highflip.Void> getDeleteDataMethod;
    if ((getDeleteDataMethod = HighFlipGrpc.getDeleteDataMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getDeleteDataMethod = HighFlipGrpc.getDeleteDataMethod) == null) {
          HighFlipGrpc.getDeleteDataMethod = getDeleteDataMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.DataId, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.DataId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("deleteData"))
              .build();
        }
      }
    }
    return getDeleteDataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorListRequest,
      highflip.v1.Highflip.OperatorListResponse> getListOperatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listOperator",
      requestType = highflip.v1.Highflip.OperatorListRequest.class,
      responseType = highflip.v1.Highflip.OperatorListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorListRequest,
      highflip.v1.Highflip.OperatorListResponse> getListOperatorMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorListRequest, highflip.v1.Highflip.OperatorListResponse> getListOperatorMethod;
    if ((getListOperatorMethod = HighFlipGrpc.getListOperatorMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListOperatorMethod = HighFlipGrpc.getListOperatorMethod) == null) {
          HighFlipGrpc.getListOperatorMethod = getListOperatorMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.OperatorListRequest, highflip.v1.Highflip.OperatorListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listOperator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.OperatorListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.OperatorListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listOperator"))
              .build();
        }
      }
    }
    return getListOperatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorId,
      highflip.v1.Highflip.OperatorGetResponse> getGetOperatorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getOperator",
      requestType = highflip.v1.Highflip.OperatorId.class,
      responseType = highflip.v1.Highflip.OperatorGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorId,
      highflip.v1.Highflip.OperatorGetResponse> getGetOperatorMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.OperatorId, highflip.v1.Highflip.OperatorGetResponse> getGetOperatorMethod;
    if ((getGetOperatorMethod = HighFlipGrpc.getGetOperatorMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetOperatorMethod = HighFlipGrpc.getGetOperatorMethod) == null) {
          HighFlipGrpc.getGetOperatorMethod = getGetOperatorMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.OperatorId, highflip.v1.Highflip.OperatorGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getOperator"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.OperatorId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.OperatorGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getOperator"))
              .build();
        }
      }
    }
    return getGetOperatorMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerCreateRequest,
      highflip.v1.Highflip.PartnerId> getCreatePartnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createPartner",
      requestType = highflip.v1.Highflip.PartnerCreateRequest.class,
      responseType = highflip.v1.Highflip.PartnerId.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerCreateRequest,
      highflip.v1.Highflip.PartnerId> getCreatePartnerMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerCreateRequest, highflip.v1.Highflip.PartnerId> getCreatePartnerMethod;
    if ((getCreatePartnerMethod = HighFlipGrpc.getCreatePartnerMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getCreatePartnerMethod = HighFlipGrpc.getCreatePartnerMethod) == null) {
          HighFlipGrpc.getCreatePartnerMethod = getCreatePartnerMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PartnerCreateRequest, highflip.v1.Highflip.PartnerId>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createPartner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerCreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerId.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("createPartner"))
              .build();
        }
      }
    }
    return getCreatePartnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerId,
      highflip.v1.Highflip.PartnerGetResponse> getGetPartnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPartner",
      requestType = highflip.v1.Highflip.PartnerId.class,
      responseType = highflip.v1.Highflip.PartnerGetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerId,
      highflip.v1.Highflip.PartnerGetResponse> getGetPartnerMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerId, highflip.v1.Highflip.PartnerGetResponse> getGetPartnerMethod;
    if ((getGetPartnerMethod = HighFlipGrpc.getGetPartnerMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getGetPartnerMethod = HighFlipGrpc.getGetPartnerMethod) == null) {
          HighFlipGrpc.getGetPartnerMethod = getGetPartnerMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PartnerId, highflip.v1.Highflip.PartnerGetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPartner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerId.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerGetResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("getPartner"))
              .build();
        }
      }
    }
    return getGetPartnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerListRequest,
      highflip.v1.Highflip.PartnerListResponse> getListPartnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listPartner",
      requestType = highflip.v1.Highflip.PartnerListRequest.class,
      responseType = highflip.v1.Highflip.PartnerListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerListRequest,
      highflip.v1.Highflip.PartnerListResponse> getListPartnerMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerListRequest, highflip.v1.Highflip.PartnerListResponse> getListPartnerMethod;
    if ((getListPartnerMethod = HighFlipGrpc.getListPartnerMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getListPartnerMethod = HighFlipGrpc.getListPartnerMethod) == null) {
          HighFlipGrpc.getListPartnerMethod = getListPartnerMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PartnerListRequest, highflip.v1.Highflip.PartnerListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listPartner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerListRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("listPartner"))
              .build();
        }
      }
    }
    return getListPartnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerControlRequest,
      highflip.v1.Highflip.Void> getControlPartnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "controlPartner",
      requestType = highflip.v1.Highflip.PartnerControlRequest.class,
      responseType = highflip.v1.Highflip.Void.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerControlRequest,
      highflip.v1.Highflip.Void> getControlPartnerMethod() {
    io.grpc.MethodDescriptor<highflip.v1.Highflip.PartnerControlRequest, highflip.v1.Highflip.Void> getControlPartnerMethod;
    if ((getControlPartnerMethod = HighFlipGrpc.getControlPartnerMethod) == null) {
      synchronized (HighFlipGrpc.class) {
        if ((getControlPartnerMethod = HighFlipGrpc.getControlPartnerMethod) == null) {
          HighFlipGrpc.getControlPartnerMethod = getControlPartnerMethod =
              io.grpc.MethodDescriptor.<highflip.v1.Highflip.PartnerControlRequest, highflip.v1.Highflip.Void>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "controlPartner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.PartnerControlRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  highflip.v1.Highflip.Void.getDefaultInstance()))
              .setSchemaDescriptor(new HighFlipMethodDescriptorSupplier("controlPartner"))
              .build();
        }
      }
    }
    return getControlPartnerMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HighFlipStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HighFlipStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HighFlipStub>() {
        @java.lang.Override
        public HighFlipStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HighFlipStub(channel, callOptions);
        }
      };
    return HighFlipStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HighFlipBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HighFlipBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HighFlipBlockingStub>() {
        @java.lang.Override
        public HighFlipBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HighFlipBlockingStub(channel, callOptions);
        }
      };
    return HighFlipBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HighFlipFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HighFlipFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HighFlipFutureStub>() {
        @java.lang.Override
        public HighFlipFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HighFlipFutureStub(channel, callOptions);
        }
      };
    return HighFlipFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   *&#47;/////////////////////////////////////////////////////////////////////////////
   * SERVICE
   * //////////////////////////////////////////////////////////////////////////////
   * </pre>
   */
  public static abstract class HighFlipImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * CONFIG
     * </pre>
     */
    public void setConfig(highflip.v1.Highflip.ConfigSetRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSetConfigMethod(), responseObserver);
    }

    /**
     */
    public void getConfig(highflip.v1.Highflip.ConfigId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetConfigMethod(), responseObserver);
    }

    /**
     */
    public void listConfig(highflip.v1.Highflip.ConfigListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListConfigMethod(), responseObserver);
    }

    /**
     */
    public void deleteConfig(highflip.v1.Highflip.ConfigId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteConfigMethod(), responseObserver);
    }

    /**
     * <pre>
     * PLATFORM
     * </pre>
     */
    public void getPlatform(highflip.v1.Highflip.Void request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPlatformMethod(), responseObserver);
    }

    /**
     */
    public void listPlatform(highflip.v1.Highflip.PlatformListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPlatformMethod(), responseObserver);
    }

    /**
     */
    public void matchPlatform(highflip.v1.Highflip.PlatformMatchRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformMatchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getMatchPlatformMethod(), responseObserver);
    }

    /**
     * <pre>
     * JOB
     * </pre>
     */
    public void createJob(highflip.v1.Highflip.JobCreateRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateJobMethod(), responseObserver);
    }

    /**
     */
    public void getJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetJobMethod(), responseObserver);
    }

    /**
     */
    public void checkJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckJobMethod(), responseObserver);
    }

    /**
     */
    public void deleteJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteJobMethod(), responseObserver);
    }

    /**
     */
    public void listJob(highflip.v1.Highflip.JobListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListJobMethod(), responseObserver);
    }

    /**
     */
    public void controlJob(highflip.v1.Highflip.JobControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getControlJobMethod(), responseObserver);
    }

    /**
     */
    public void getJobLog(highflip.v1.Highflip.JobLogRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobLogResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetJobLogMethod(), responseObserver);
    }

    /**
     * <pre>
     * TASK
     * </pre>
     */
    public void listTask(highflip.v1.Highflip.TaskListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTaskMethod(), responseObserver);
    }

    /**
     */
    public void getTask(highflip.v1.Highflip.TaskId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTaskMethod(), responseObserver);
    }

    /**
     */
    public void checkTask(highflip.v1.Highflip.TaskId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskCheckResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckTaskMethod(), responseObserver);
    }

    /**
     */
    public void controlTask(highflip.v1.Highflip.TaskControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getControlTaskMethod(), responseObserver);
    }

    /**
     */
    public void getTaskLog(highflip.v1.Highflip.TaskLogRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskLogResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTaskLogMethod(), responseObserver);
    }

    /**
     * <pre>
     * DATA
     * </pre>
     */
    public void listData(highflip.v1.Highflip.DataListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListDataMethod(), responseObserver);
    }

    /**
     */
    public void getData(highflip.v1.Highflip.DataId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetDataMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataPushRequest> pushData(
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataId> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getPushDataMethod(), responseObserver);
    }

    /**
     */
    public void pullData(highflip.v1.Highflip.DataPullRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataPullResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPullDataMethod(), responseObserver);
    }

    /**
     */
    public void deleteData(highflip.v1.Highflip.DataId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteDataMethod(), responseObserver);
    }

    /**
     * <pre>
     * OPERATOR
     * </pre>
     */
    public void listOperator(highflip.v1.Highflip.OperatorListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListOperatorMethod(), responseObserver);
    }

    /**
     */
    public void getOperator(highflip.v1.Highflip.OperatorId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetOperatorMethod(), responseObserver);
    }

    /**
     * <pre>
     * PARTNER
     * </pre>
     */
    public void createPartner(highflip.v1.Highflip.PartnerCreateRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerId> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePartnerMethod(), responseObserver);
    }

    /**
     */
    public void getPartner(highflip.v1.Highflip.PartnerId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerGetResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPartnerMethod(), responseObserver);
    }

    /**
     */
    public void listPartner(highflip.v1.Highflip.PartnerListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPartnerMethod(), responseObserver);
    }

    /**
     */
    public void controlPartner(highflip.v1.Highflip.PartnerControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getControlPartnerMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSetConfigMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.ConfigSetRequest,
                highflip.v1.Highflip.Void>(
                  this, METHODID_SET_CONFIG)))
          .addMethod(
            getGetConfigMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.ConfigId,
                highflip.v1.Highflip.ConfigGetResponse>(
                  this, METHODID_GET_CONFIG)))
          .addMethod(
            getListConfigMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.ConfigListRequest,
                highflip.v1.Highflip.ConfigListResponse>(
                  this, METHODID_LIST_CONFIG)))
          .addMethod(
            getDeleteConfigMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.ConfigId,
                highflip.v1.Highflip.Void>(
                  this, METHODID_DELETE_CONFIG)))
          .addMethod(
            getGetPlatformMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.Void,
                highflip.v1.Highflip.PlatformGetResponse>(
                  this, METHODID_GET_PLATFORM)))
          .addMethod(
            getListPlatformMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.PlatformListRequest,
                highflip.v1.Highflip.PlatformListResponse>(
                  this, METHODID_LIST_PLATFORM)))
          .addMethod(
            getMatchPlatformMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.PlatformMatchRequest,
                highflip.v1.Highflip.PlatformMatchResponse>(
                  this, METHODID_MATCH_PLATFORM)))
          .addMethod(
            getCreateJobMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobCreateRequest,
                highflip.v1.Highflip.JobId>(
                  this, METHODID_CREATE_JOB)))
          .addMethod(
            getGetJobMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobId,
                highflip.v1.Highflip.JobGetResponse>(
                  this, METHODID_GET_JOB)))
          .addMethod(
            getCheckJobMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobId,
                highflip.v1.Highflip.JobCheckResponse>(
                  this, METHODID_CHECK_JOB)))
          .addMethod(
            getDeleteJobMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobId,
                highflip.v1.Highflip.Void>(
                  this, METHODID_DELETE_JOB)))
          .addMethod(
            getListJobMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobListRequest,
                highflip.v1.Highflip.JobListResponse>(
                  this, METHODID_LIST_JOB)))
          .addMethod(
            getControlJobMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobControlRequest,
                highflip.v1.Highflip.Void>(
                  this, METHODID_CONTROL_JOB)))
          .addMethod(
            getGetJobLogMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.JobLogRequest,
                highflip.v1.Highflip.JobLogResponse>(
                  this, METHODID_GET_JOB_LOG)))
          .addMethod(
            getListTaskMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.TaskListRequest,
                highflip.v1.Highflip.TaskListResponse>(
                  this, METHODID_LIST_TASK)))
          .addMethod(
            getGetTaskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.TaskId,
                highflip.v1.Highflip.TaskGetResponse>(
                  this, METHODID_GET_TASK)))
          .addMethod(
            getCheckTaskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.TaskId,
                highflip.v1.Highflip.TaskCheckResponse>(
                  this, METHODID_CHECK_TASK)))
          .addMethod(
            getControlTaskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.TaskControlRequest,
                highflip.v1.Highflip.Void>(
                  this, METHODID_CONTROL_TASK)))
          .addMethod(
            getGetTaskLogMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.TaskLogRequest,
                highflip.v1.Highflip.TaskLogResponse>(
                  this, METHODID_GET_TASK_LOG)))
          .addMethod(
            getListDataMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.DataListRequest,
                highflip.v1.Highflip.DataListResponse>(
                  this, METHODID_LIST_DATA)))
          .addMethod(
            getGetDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.DataId,
                highflip.v1.Highflip.DataGetResponse>(
                  this, METHODID_GET_DATA)))
          .addMethod(
            getPushDataMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.DataPushRequest,
                highflip.v1.Highflip.DataId>(
                  this, METHODID_PUSH_DATA)))
          .addMethod(
            getPullDataMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.DataPullRequest,
                highflip.v1.Highflip.DataPullResponse>(
                  this, METHODID_PULL_DATA)))
          .addMethod(
            getDeleteDataMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.DataId,
                highflip.v1.Highflip.Void>(
                  this, METHODID_DELETE_DATA)))
          .addMethod(
            getListOperatorMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.OperatorListRequest,
                highflip.v1.Highflip.OperatorListResponse>(
                  this, METHODID_LIST_OPERATOR)))
          .addMethod(
            getGetOperatorMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.OperatorId,
                highflip.v1.Highflip.OperatorGetResponse>(
                  this, METHODID_GET_OPERATOR)))
          .addMethod(
            getCreatePartnerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.PartnerCreateRequest,
                highflip.v1.Highflip.PartnerId>(
                  this, METHODID_CREATE_PARTNER)))
          .addMethod(
            getGetPartnerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.PartnerId,
                highflip.v1.Highflip.PartnerGetResponse>(
                  this, METHODID_GET_PARTNER)))
          .addMethod(
            getListPartnerMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                highflip.v1.Highflip.PartnerListRequest,
                highflip.v1.Highflip.PartnerListResponse>(
                  this, METHODID_LIST_PARTNER)))
          .addMethod(
            getControlPartnerMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                highflip.v1.Highflip.PartnerControlRequest,
                highflip.v1.Highflip.Void>(
                  this, METHODID_CONTROL_PARTNER)))
          .build();
    }
  }

  /**
   * <pre>
   *&#47;/////////////////////////////////////////////////////////////////////////////
   * SERVICE
   * //////////////////////////////////////////////////////////////////////////////
   * </pre>
   */
  public static final class HighFlipStub extends io.grpc.stub.AbstractAsyncStub<HighFlipStub> {
    private HighFlipStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HighFlipStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HighFlipStub(channel, callOptions);
    }

    /**
     * <pre>
     * CONFIG
     * </pre>
     */
    public void setConfig(highflip.v1.Highflip.ConfigSetRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getConfig(highflip.v1.Highflip.ConfigId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listConfig(highflip.v1.Highflip.ConfigListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteConfig(highflip.v1.Highflip.ConfigId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteConfigMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PLATFORM
     * </pre>
     */
    public void getPlatform(highflip.v1.Highflip.Void request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPlatformMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listPlatform(highflip.v1.Highflip.PlatformListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListPlatformMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void matchPlatform(highflip.v1.Highflip.PlatformMatchRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformMatchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getMatchPlatformMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * JOB
     * </pre>
     */
    public void createJob(highflip.v1.Highflip.JobCreateRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteJob(highflip.v1.Highflip.JobId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listJob(highflip.v1.Highflip.JobListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void controlJob(highflip.v1.Highflip.JobControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getControlJobMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getJobLog(highflip.v1.Highflip.JobLogRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobLogResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetJobLogMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * TASK
     * </pre>
     */
    public void listTask(highflip.v1.Highflip.TaskListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTask(highflip.v1.Highflip.TaskId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkTask(highflip.v1.Highflip.TaskId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskCheckResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void controlTask(highflip.v1.Highflip.TaskControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getControlTaskMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTaskLog(highflip.v1.Highflip.TaskLogRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskLogResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getGetTaskLogMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * DATA
     * </pre>
     */
    public void listData(highflip.v1.Highflip.DataListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getData(highflip.v1.Highflip.DataId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataPushRequest> pushData(
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataId> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getPushDataMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void pullData(highflip.v1.Highflip.DataPullRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataPullResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getPullDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteData(highflip.v1.Highflip.DataId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteDataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * OPERATOR
     * </pre>
     */
    public void listOperator(highflip.v1.Highflip.OperatorListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListOperatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOperator(highflip.v1.Highflip.OperatorId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetOperatorMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PARTNER
     * </pre>
     */
    public void createPartner(highflip.v1.Highflip.PartnerCreateRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerId> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePartnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPartner(highflip.v1.Highflip.PartnerId request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerGetResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPartnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listPartner(highflip.v1.Highflip.PartnerListRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getListPartnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void controlPartner(highflip.v1.Highflip.PartnerControlRequest request,
        io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getControlPartnerMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *&#47;/////////////////////////////////////////////////////////////////////////////
   * SERVICE
   * //////////////////////////////////////////////////////////////////////////////
   * </pre>
   */
  public static final class HighFlipBlockingStub extends io.grpc.stub.AbstractBlockingStub<HighFlipBlockingStub> {
    private HighFlipBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HighFlipBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HighFlipBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * CONFIG
     * </pre>
     */
    public highflip.v1.Highflip.Void setConfig(highflip.v1.Highflip.ConfigSetRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.ConfigGetResponse getConfig(highflip.v1.Highflip.ConfigId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.ConfigListResponse> listConfig(
        highflip.v1.Highflip.ConfigListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListConfigMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void deleteConfig(highflip.v1.Highflip.ConfigId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteConfigMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PLATFORM
     * </pre>
     */
    public highflip.v1.Highflip.PlatformGetResponse getPlatform(highflip.v1.Highflip.Void request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPlatformMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.PlatformListResponse listPlatform(highflip.v1.Highflip.PlatformListRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPlatformMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.PlatformMatchResponse> matchPlatform(
        highflip.v1.Highflip.PlatformMatchRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getMatchPlatformMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * JOB
     * </pre>
     */
    public highflip.v1.Highflip.JobId createJob(highflip.v1.Highflip.JobCreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.JobGetResponse getJob(highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.JobCheckResponse checkJob(highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void deleteJob(highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.JobListResponse> listJob(
        highflip.v1.Highflip.JobListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void controlJob(highflip.v1.Highflip.JobControlRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getControlJobMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.JobLogResponse> getJobLog(
        highflip.v1.Highflip.JobLogRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetJobLogMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * TASK
     * </pre>
     */
    public java.util.Iterator<highflip.v1.Highflip.TaskListResponse> listTask(
        highflip.v1.Highflip.TaskListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.TaskGetResponse getTask(highflip.v1.Highflip.TaskId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.TaskCheckResponse checkTask(highflip.v1.Highflip.TaskId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void controlTask(highflip.v1.Highflip.TaskControlRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getControlTaskMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.TaskLogResponse> getTaskLog(
        highflip.v1.Highflip.TaskLogRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getGetTaskLogMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * DATA
     * </pre>
     */
    public java.util.Iterator<highflip.v1.Highflip.DataListResponse> listData(
        highflip.v1.Highflip.DataListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.DataGetResponse getData(highflip.v1.Highflip.DataId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.DataPullResponse> pullData(
        highflip.v1.Highflip.DataPullRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getPullDataMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void deleteData(highflip.v1.Highflip.DataId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteDataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * OPERATOR
     * </pre>
     */
    public java.util.Iterator<highflip.v1.Highflip.OperatorListResponse> listOperator(
        highflip.v1.Highflip.OperatorListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListOperatorMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.OperatorGetResponse getOperator(highflip.v1.Highflip.OperatorId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetOperatorMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PARTNER
     * </pre>
     */
    public highflip.v1.Highflip.PartnerId createPartner(highflip.v1.Highflip.PartnerCreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePartnerMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.PartnerGetResponse getPartner(highflip.v1.Highflip.PartnerId request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPartnerMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<highflip.v1.Highflip.PartnerListResponse> listPartner(
        highflip.v1.Highflip.PartnerListRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getListPartnerMethod(), getCallOptions(), request);
    }

    /**
     */
    public highflip.v1.Highflip.Void controlPartner(highflip.v1.Highflip.PartnerControlRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getControlPartnerMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *&#47;/////////////////////////////////////////////////////////////////////////////
   * SERVICE
   * //////////////////////////////////////////////////////////////////////////////
   * </pre>
   */
  public static final class HighFlipFutureStub extends io.grpc.stub.AbstractFutureStub<HighFlipFutureStub> {
    private HighFlipFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HighFlipFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HighFlipFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * CONFIG
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> setConfig(
        highflip.v1.Highflip.ConfigSetRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSetConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.ConfigGetResponse> getConfig(
        highflip.v1.Highflip.ConfigId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetConfigMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> deleteConfig(
        highflip.v1.Highflip.ConfigId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteConfigMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PLATFORM
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.PlatformGetResponse> getPlatform(
        highflip.v1.Highflip.Void request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPlatformMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.PlatformListResponse> listPlatform(
        highflip.v1.Highflip.PlatformListRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListPlatformMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * JOB
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.JobId> createJob(
        highflip.v1.Highflip.JobCreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateJobMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.JobGetResponse> getJob(
        highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetJobMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.JobCheckResponse> checkJob(
        highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckJobMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> deleteJob(
        highflip.v1.Highflip.JobId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteJobMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> controlJob(
        highflip.v1.Highflip.JobControlRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getControlJobMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.TaskGetResponse> getTask(
        highflip.v1.Highflip.TaskId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.TaskCheckResponse> checkTask(
        highflip.v1.Highflip.TaskId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> controlTask(
        highflip.v1.Highflip.TaskControlRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getControlTaskMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.DataGetResponse> getData(
        highflip.v1.Highflip.DataId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> deleteData(
        highflip.v1.Highflip.DataId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteDataMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.OperatorGetResponse> getOperator(
        highflip.v1.Highflip.OperatorId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetOperatorMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PARTNER
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.PartnerId> createPartner(
        highflip.v1.Highflip.PartnerCreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePartnerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.PartnerGetResponse> getPartner(
        highflip.v1.Highflip.PartnerId request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPartnerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<highflip.v1.Highflip.Void> controlPartner(
        highflip.v1.Highflip.PartnerControlRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getControlPartnerMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SET_CONFIG = 0;
  private static final int METHODID_GET_CONFIG = 1;
  private static final int METHODID_LIST_CONFIG = 2;
  private static final int METHODID_DELETE_CONFIG = 3;
  private static final int METHODID_GET_PLATFORM = 4;
  private static final int METHODID_LIST_PLATFORM = 5;
  private static final int METHODID_MATCH_PLATFORM = 6;
  private static final int METHODID_CREATE_JOB = 7;
  private static final int METHODID_GET_JOB = 8;
  private static final int METHODID_CHECK_JOB = 9;
  private static final int METHODID_DELETE_JOB = 10;
  private static final int METHODID_LIST_JOB = 11;
  private static final int METHODID_CONTROL_JOB = 12;
  private static final int METHODID_GET_JOB_LOG = 13;
  private static final int METHODID_LIST_TASK = 14;
  private static final int METHODID_GET_TASK = 15;
  private static final int METHODID_CHECK_TASK = 16;
  private static final int METHODID_CONTROL_TASK = 17;
  private static final int METHODID_GET_TASK_LOG = 18;
  private static final int METHODID_LIST_DATA = 19;
  private static final int METHODID_GET_DATA = 20;
  private static final int METHODID_PULL_DATA = 21;
  private static final int METHODID_DELETE_DATA = 22;
  private static final int METHODID_LIST_OPERATOR = 23;
  private static final int METHODID_GET_OPERATOR = 24;
  private static final int METHODID_CREATE_PARTNER = 25;
  private static final int METHODID_GET_PARTNER = 26;
  private static final int METHODID_LIST_PARTNER = 27;
  private static final int METHODID_CONTROL_PARTNER = 28;
  private static final int METHODID_PUSH_DATA = 29;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HighFlipImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HighFlipImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SET_CONFIG:
          serviceImpl.setConfig((highflip.v1.Highflip.ConfigSetRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_GET_CONFIG:
          serviceImpl.getConfig((highflip.v1.Highflip.ConfigId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigGetResponse>) responseObserver);
          break;
        case METHODID_LIST_CONFIG:
          serviceImpl.listConfig((highflip.v1.Highflip.ConfigListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.ConfigListResponse>) responseObserver);
          break;
        case METHODID_DELETE_CONFIG:
          serviceImpl.deleteConfig((highflip.v1.Highflip.ConfigId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_GET_PLATFORM:
          serviceImpl.getPlatform((highflip.v1.Highflip.Void) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformGetResponse>) responseObserver);
          break;
        case METHODID_LIST_PLATFORM:
          serviceImpl.listPlatform((highflip.v1.Highflip.PlatformListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformListResponse>) responseObserver);
          break;
        case METHODID_MATCH_PLATFORM:
          serviceImpl.matchPlatform((highflip.v1.Highflip.PlatformMatchRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PlatformMatchResponse>) responseObserver);
          break;
        case METHODID_CREATE_JOB:
          serviceImpl.createJob((highflip.v1.Highflip.JobCreateRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobId>) responseObserver);
          break;
        case METHODID_GET_JOB:
          serviceImpl.getJob((highflip.v1.Highflip.JobId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobGetResponse>) responseObserver);
          break;
        case METHODID_CHECK_JOB:
          serviceImpl.checkJob((highflip.v1.Highflip.JobId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobCheckResponse>) responseObserver);
          break;
        case METHODID_DELETE_JOB:
          serviceImpl.deleteJob((highflip.v1.Highflip.JobId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_LIST_JOB:
          serviceImpl.listJob((highflip.v1.Highflip.JobListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobListResponse>) responseObserver);
          break;
        case METHODID_CONTROL_JOB:
          serviceImpl.controlJob((highflip.v1.Highflip.JobControlRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_GET_JOB_LOG:
          serviceImpl.getJobLog((highflip.v1.Highflip.JobLogRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.JobLogResponse>) responseObserver);
          break;
        case METHODID_LIST_TASK:
          serviceImpl.listTask((highflip.v1.Highflip.TaskListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskListResponse>) responseObserver);
          break;
        case METHODID_GET_TASK:
          serviceImpl.getTask((highflip.v1.Highflip.TaskId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskGetResponse>) responseObserver);
          break;
        case METHODID_CHECK_TASK:
          serviceImpl.checkTask((highflip.v1.Highflip.TaskId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskCheckResponse>) responseObserver);
          break;
        case METHODID_CONTROL_TASK:
          serviceImpl.controlTask((highflip.v1.Highflip.TaskControlRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_GET_TASK_LOG:
          serviceImpl.getTaskLog((highflip.v1.Highflip.TaskLogRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.TaskLogResponse>) responseObserver);
          break;
        case METHODID_LIST_DATA:
          serviceImpl.listData((highflip.v1.Highflip.DataListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataListResponse>) responseObserver);
          break;
        case METHODID_GET_DATA:
          serviceImpl.getData((highflip.v1.Highflip.DataId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataGetResponse>) responseObserver);
          break;
        case METHODID_PULL_DATA:
          serviceImpl.pullData((highflip.v1.Highflip.DataPullRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataPullResponse>) responseObserver);
          break;
        case METHODID_DELETE_DATA:
          serviceImpl.deleteData((highflip.v1.Highflip.DataId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
          break;
        case METHODID_LIST_OPERATOR:
          serviceImpl.listOperator((highflip.v1.Highflip.OperatorListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorListResponse>) responseObserver);
          break;
        case METHODID_GET_OPERATOR:
          serviceImpl.getOperator((highflip.v1.Highflip.OperatorId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.OperatorGetResponse>) responseObserver);
          break;
        case METHODID_CREATE_PARTNER:
          serviceImpl.createPartner((highflip.v1.Highflip.PartnerCreateRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerId>) responseObserver);
          break;
        case METHODID_GET_PARTNER:
          serviceImpl.getPartner((highflip.v1.Highflip.PartnerId) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerGetResponse>) responseObserver);
          break;
        case METHODID_LIST_PARTNER:
          serviceImpl.listPartner((highflip.v1.Highflip.PartnerListRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.PartnerListResponse>) responseObserver);
          break;
        case METHODID_CONTROL_PARTNER:
          serviceImpl.controlPartner((highflip.v1.Highflip.PartnerControlRequest) request,
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.Void>) responseObserver);
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
        case METHODID_PUSH_DATA:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.pushData(
              (io.grpc.stub.StreamObserver<highflip.v1.Highflip.DataId>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HighFlipBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HighFlipBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return highflip.v1.Highflip.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HighFlip");
    }
  }

  private static final class HighFlipFileDescriptorSupplier
      extends HighFlipBaseDescriptorSupplier {
    HighFlipFileDescriptorSupplier() {}
  }

  private static final class HighFlipMethodDescriptorSupplier
      extends HighFlipBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HighFlipMethodDescriptorSupplier(String methodName) {
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
      synchronized (HighFlipGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HighFlipFileDescriptorSupplier())
              .addMethod(getSetConfigMethod())
              .addMethod(getGetConfigMethod())
              .addMethod(getListConfigMethod())
              .addMethod(getDeleteConfigMethod())
              .addMethod(getGetPlatformMethod())
              .addMethod(getListPlatformMethod())
              .addMethod(getMatchPlatformMethod())
              .addMethod(getCreateJobMethod())
              .addMethod(getGetJobMethod())
              .addMethod(getCheckJobMethod())
              .addMethod(getDeleteJobMethod())
              .addMethod(getListJobMethod())
              .addMethod(getControlJobMethod())
              .addMethod(getGetJobLogMethod())
              .addMethod(getListTaskMethod())
              .addMethod(getGetTaskMethod())
              .addMethod(getCheckTaskMethod())
              .addMethod(getControlTaskMethod())
              .addMethod(getGetTaskLogMethod())
              .addMethod(getListDataMethod())
              .addMethod(getGetDataMethod())
              .addMethod(getPushDataMethod())
              .addMethod(getPullDataMethod())
              .addMethod(getDeleteDataMethod())
              .addMethod(getListOperatorMethod())
              .addMethod(getGetOperatorMethod())
              .addMethod(getCreatePartnerMethod())
              .addMethod(getGetPartnerMethod())
              .addMethod(getListPartnerMethod())
              .addMethod(getControlPartnerMethod())
              .build();
        }
      }
    }
    return result;
  }
}
