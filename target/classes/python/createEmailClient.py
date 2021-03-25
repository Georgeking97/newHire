from __future__ import print_function
import logging

import grpc

import create_email_pb2
import create_email_pb2_grpc
from pip._internal.network.utils import response_chunks

def create():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = create_email_pb2_grpc.newHirepythonStub(channel)
        response = stub.createEmail(create_email_pb2.EmailToCreate(text='george'))
    
    print("client received: " + response.value)
    
def delete():
    with grpc.insecure_channel('localhost:50051') as channel:
        stub = create_email_pb2_grpc.newHirepythonStub(channel)
        response = stub.deleteEmail(create_email_pb2.EmailToDelete(text='george@gmail.com'))
    
    print("client received: " + response.value)
    
def see():
    with grpc.insecure_channel('localhost:50051') as channel:
        
        stub = create_email_pb2_grpc.newHirepythonStub(channel)
        response = stub.seeEmails(create_email_pb2.Emails(text='request'))
        for resp in response:
            print("email %s" %resp)
    
if __name__ == '__main__':
    logging.basicConfig()
    create()
    see()
    delete()
    see()