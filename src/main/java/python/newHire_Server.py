from concurrent import futures
from jproperties import Properties
from zeroconf import IPVersion, ServiceInfo, Zeroconf

import logging
import grpc
import create_email_pb2
import create_email_pb2_grpc
import socket
import queue
import jpysocket

class newHirepython(create_email_pb2_grpc.newHirepythonServicer):
    
    emails = []
    # grpc method that allows the creation of emails based on the request the user passes in
    def createEmail(self, request, context):
        self.emails
        self.emails.append('%s@gmail.com' % request.text)
        return create_email_pb2.EmailCreated(value='Email created: %s@gmail.com' % request.text)
    # grpc method that allows the deletion of emails based on the request the user passes in
    # if no email matches the request or there are no emails to delete that is sent back instead
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
    # grpc method that allows the user to see all emails currently on the server
    def seeEmails(self, request, context):
        self.emails
        if (len(self.emails)) > 0:
            for p in self.emails:
                yield create_email_pb2.AllEmails(value=p)
                print('Email: %s' % p)
        else:
            yield create_email_pb2.AllEmails(value='no email to see')
#creates the server and sets it on the port 50051        
def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    create_email_pb2_grpc.add_newHirepythonServicer_to_server(newHirepython(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print('Server started...')
    server.wait_for_termination()
#registers the service and allows it to be discoverable
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
    print('registered...')
    
#gets access to the properties file that provides information in the logs when starting the server    
def getProp():
    configs = Properties()
    with open('email.properties', 'rb') as config_file:
        configs.load(config_file)
    print('Service properties')
    print(configs.get("service_type"))
    print(configs.get("service_name"))
    print(configs.get("service_description"))
    print(configs.get("service_port"))
#main method that calls all the required methods to complete its functionality
#serve must be called last as it doesn't finish until termination 
if __name__ == "__main__":
    logging.basicConfig()
    getProp()
    register()
    serve()


    
    
    