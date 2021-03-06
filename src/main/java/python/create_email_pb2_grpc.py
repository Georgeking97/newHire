# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

import create_email_pb2 as create__email__pb2


class newHirepythonStub(object):
    """Missing associated documentation comment in .proto file."""

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.createEmail = channel.unary_unary(
                '/newHirepython.newHirepython/createEmail',
                request_serializer=create__email__pb2.EmailToCreate.SerializeToString,
                response_deserializer=create__email__pb2.EmailCreated.FromString,
                )
        self.deleteEmail = channel.unary_unary(
                '/newHirepython.newHirepython/deleteEmail',
                request_serializer=create__email__pb2.EmailToDelete.SerializeToString,
                response_deserializer=create__email__pb2.EmailDeleted.FromString,
                )
        self.seeEmails = channel.unary_stream(
                '/newHirepython.newHirepython/seeEmails',
                request_serializer=create__email__pb2.Emails.SerializeToString,
                response_deserializer=create__email__pb2.AllEmails.FromString,
                )


class newHirepythonServicer(object):
    """Missing associated documentation comment in .proto file."""

    def createEmail(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def deleteEmail(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')

    def seeEmails(self, request, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_newHirepythonServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'createEmail': grpc.unary_unary_rpc_method_handler(
                    servicer.createEmail,
                    request_deserializer=create__email__pb2.EmailToCreate.FromString,
                    response_serializer=create__email__pb2.EmailCreated.SerializeToString,
            ),
            'deleteEmail': grpc.unary_unary_rpc_method_handler(
                    servicer.deleteEmail,
                    request_deserializer=create__email__pb2.EmailToDelete.FromString,
                    response_serializer=create__email__pb2.EmailDeleted.SerializeToString,
            ),
            'seeEmails': grpc.unary_stream_rpc_method_handler(
                    servicer.seeEmails,
                    request_deserializer=create__email__pb2.Emails.FromString,
                    response_serializer=create__email__pb2.AllEmails.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'newHirepython.newHirepython', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class newHirepython(object):
    """Missing associated documentation comment in .proto file."""

    @staticmethod
    def createEmail(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/newHirepython.newHirepython/createEmail',
            create__email__pb2.EmailToCreate.SerializeToString,
            create__email__pb2.EmailCreated.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def deleteEmail(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_unary(request, target, '/newHirepython.newHirepython/deleteEmail',
            create__email__pb2.EmailToDelete.SerializeToString,
            create__email__pb2.EmailDeleted.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)

    @staticmethod
    def seeEmails(request,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.unary_stream(request, target, '/newHirepython.newHirepython/seeEmails',
            create__email__pb2.Emails.SerializeToString,
            create__email__pb2.AllEmails.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
