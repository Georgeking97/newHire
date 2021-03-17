from __future__ import print_function
import logging

import grpc

import create_email_pb2
import create_email_pb2_grpc

def run():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = create_email_pb2_grpc.newHireStub(channel)
        response = stub.createEmail(create_email_pb2.EmailToCreate(text='george'))
    
    print("Greeter client received: " + response.value)
    

if __name__ == '__main__':
    logging.basicConfig()
    run()