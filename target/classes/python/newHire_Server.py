from concurrent import futures
import logging

import grpc

import create_email_pb2
import create_email_pb2_grpc


class newHire(create_email_pb2_grpc.newHireServicer):

    def createEmail(self, request, context):
        return create_email_pb2.EmailCreated(value='Hello, %s' % request.text)
    
def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    create_email_pb2_grpc.add_newHireServicer_to_server(newHire(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    server.wait_for_termination()
    
    
if __name__ == "__main__":
    logging.basicConfig()
    serve()
    