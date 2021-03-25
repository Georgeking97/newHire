package newHire.create.permissions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.permissions.newHireGrpc.newHireImplBase;

public class CreatePermissionsServer extends newHireImplBase {
    ArrayList<String> permissions = new ArrayList<>();

    public static void main(String[] args) {
    	//creating an object of the class
        CreatePermissionsServer classObj = new CreatePermissionsServer();
        //creating the property object 
        Properties prop = classObj.getProperties();
        //setting the port for the server to register on and listen on
        int port = Integer.parseInt(prop.getProperty("service_port"));
        //registering the service
        classObj.registerService(prop);
        try {
        	//creating the server and starting it
            Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
            System.out.println("Server started, awaiting RPC calls...");
            server.awaitTermination();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    //property method for getting information from the properties file stored in resources
    private Properties getProperties() {
        Properties prop = null;
        try (InputStream input = new FileInputStream("src/main/resources/properties/permissions.properties")) {
            prop = new Properties();
            prop.load(input);
            System.out.println("Service properties...");
            System.out.println("\t service_description: " + prop.getProperty("service_description"));
            System.out.println("\t service_type: " + prop.getProperty("service_type"));
            System.out.println("\t service_name: " + prop.getProperty("service_name"));
            System.out.println("\t service_port: " + prop.getProperty("service_port"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }
    //registering the service so it can be discoverable by the GUI
    private void registerService(Properties prop) {
        try {
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
            String service_type = prop.getProperty("service_type");
            String service_name = prop.getProperty("service_name");
            String service_description_properties = prop.getProperty("service_description");
            int service_port = Integer.parseInt(prop.getProperty("service_port"));
            //creating the service and registering it using JmDNS
            ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
                    service_description_properties);
            jmdns.registerService(serviceInfo);
            System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
            Thread.sleep(1000);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // bi-directional streaming allowing a user to set permissions and get an instant response. The user
    // is only allowed set permissions which already exist. if they try to set a permission that doesn't exist
    // or there is no permissions at all to set they are informed via a simple response message
    @Override
    public StreamObserver<permissionRequest> permissions(StreamObserver<permissionResponse> responseObserver) {
        System.out.println("Permissions started");
        return new StreamObserver<permissionRequest>() {
            @Override
            public void onNext(permissionRequest value) {
                if (permissions.size() > 0) {
                    String lastIndexOfPermissionsArray = permissions.get(permissions.size() - 1);
                    for (String permission : permissions) {
                        if (permission.equals(value.getText())) {
                            permissionResponse reply = permissionResponse.newBuilder()
                                    .setValue("Your permission has been set: " + value.getText()).build();
                            responseObserver.onNext(reply);
                            break;
                        } else if (permission.equals(lastIndexOfPermissionsArray)) {
                            permissionResponse reply = permissionResponse.newBuilder()
                                    .setValue("This isn't a valid permission to set: " + value.getText()).build();
                            responseObserver.onNext(reply);
                        }
                    }
                } else {
                    permissionResponse reply = permissionResponse.newBuilder()
                            .setValue("There is no permissions available for setting right now").build();
                    responseObserver.onNext(reply);
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {

            }
        };
    }
    // a simple rpc call, allows a user to create permissions so that they can be set
    @Override
    public void setPermissions(NewPermission request, StreamObserver<CreatedPermission> responseObserver) {
    	permissions.add(request.getText());
		CreatedPermission reply = CreatedPermission.newBuilder().setValue("Your permission has been added: "+request.getText()).build();
	    responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
    // a simple server side streaming rpc call, allows a user to see all available permissions. If there is no
    // permissions to see a simple response message is sent instead
    @Override
    public void seePermissions(RequestPermissions request, StreamObserver<AllPermissions> responseObserver) {
        if (permissions.size() > 0) {
            for (String permission : permissions) {
                AllPermissions reply = AllPermissions.newBuilder().setValue(permission).build();
                responseObserver.onNext(reply);
            }
        } else {
            AllPermissions reply = AllPermissions.newBuilder().setValue("No permissions to see").build();
            responseObserver.onNext(reply);
        }
        responseObserver.onCompleted();
    }
}
