package newHire.find.newhires;

import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

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
import newHire.find.newhires.newHireGrpc.newHireImplBase;

public class findNewHiresServer extends newHireImplBase {

	public static void main(String[] args) {
		findNewHiresServer classObj = new findNewHiresServer();
		Properties prop = classObj.getProperties();
		classObj.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));

		try {
			// setting the port to connect to, building & starting the server
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, waiting for rpc...");

			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Properties getProperties() {
		Properties prop = null;
		// providing path to the properties file
		try (InputStream input = new FileInputStream("src/main/resources/new_hires.properties")) {
			prop = new Properties();
			// loading the properties file
			prop.load(input);
			// get the property values and printing them out
			System.out.println("Math Service properies ...");
			System.out.println("\t service_type: " + prop.getProperty("service_type"));
			System.out.println("\t service_name: " + prop.getProperty("service_name"));
			System.out.println("\t service_description: " + prop.getProperty("service_description"));
			System.out.println("\t service_port: " + prop.getProperty("service_port"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	private void registerService(Properties prop) {
		try {
			// creating an instance of JmDNS
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting the values required to register my service
			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			String service_description_properties = prop.getProperty("service_description");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));

			// creating the service for registration
			ServiceInfo serviceInfo = ServiceInfo.create(service_type, service_name, service_port,
					service_description_properties);
			// registering the service
			jmdns.registerService(serviceInfo);
			System.out.printf("registrering service with type %s and name %s \n", service_type, service_name);
			// good practice to wait some time
			Thread.sleep(1000);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// server side streaming rpc
	public void sendMessage(MessageRequest request, StreamObserver<MessageReply> responseObserver) {
		System.out.println("Your message has been recieved: " + request.getText());
		// creating an array and populating it with dummy data currently to test
		// functionality
		ArrayList<String> list = new ArrayList();
		list.add("John");
		list.add("Adam");
		list.add("Jess");
		// for loop for every name in the array create a reply and respond to the client
		for (int i = 0; i < list.size(); i++) {
			String name = list.get(i);
			MessageReply reply = MessageReply.newBuilder().setValue(name).build();
			responseObserver.onNext(reply);
		}

		responseObserver.onCompleted();
	}
}
