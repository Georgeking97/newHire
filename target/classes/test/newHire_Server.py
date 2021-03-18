from concurrent import futures
from jproperties import Properties
from zeroconf import IPVersion, ServiceInfo, Zeroconf

import logging
import grpc
import create_email_pb2
import create_email_pb2_grpc
import socket
import queue
import socket
import jpysocket

class newHirepython(create_email_pb2_grpc.newHirepythonServicer):
    
    emails = []

    def createEmail(self, request, context):
        self.emails
        self.emails.append('%s@gmail.com' % request.text)
        return create_email_pb2.EmailCreated(value='Email created: %s@gmail.com' % request.text)
    
    def deleteEmail(self, request, context):
        self.emails
        if (len(self.emails)) > 0:
            for y in self.emails:
                if request.text == y:
                    self.emails.remove(y)
                    return create_email_pb2.EmailDeleted(value='Email deleted: %s' % request.text)
                else:
                    return create_email_pb2.EmailDeleted(value='no email found')
        else:
            return create_email_pb2.EmailDeleted(value='No emails to delete')
        
    def seeEmails(self, request, context):
        self.emails
        if (len(self.emails)) > 0:
            for p in self.emails:
                return create_email_pb2.EmailDeleted(p)
        else:
            print('No emails to print')
    
def serve():
    host='localhost' 
    port=50051    
    s=socket.socket() 
    s.bind((host,port)) 
    s.listen(5) 
    print("Socket Is Listening....")
    connection,address=s.accept() 
    print("Connected To ",address)
    msgsend=jpysocket.jpyencode("Thank You For Connecting.") 
    connection.send(msgsend) 
    msgrecv=connection.recv(1024) 
    msgrecv=jpysocket.jpydecode(msgrecv) 
    print("From Client: ",msgrecv)
    s.close() 
    print("Connection Closed.")
    
def register():
    global zeroconf
    desc = {'path': 'email.properties'}
    
    info = ServiceInfo(
        "_http._tcp.local.",
        "Email._http._tcp.local.",
        addresses=[socket.inet_aton("192.168.0.241")],
        port=50051,
        properties=desc,
        server="email.local.",
    )
    zeroconf = Zeroconf()
    zeroconf.register_service(info)
    print('registered!')
    
    
def getProp():
    configs = Properties()
    with open('email.properties', 'rb') as config_file:
        configs.load(config_file)
    print('Service properties')
    print(configs.get("service_type"))
    print(configs.get("service_name"))
    print(configs.get("service_description"))
    print(configs.get("service_port"))
    
if __name__ == "__main__":
    logging.basicConfig()
    getProp()
    register()
    serve()

    