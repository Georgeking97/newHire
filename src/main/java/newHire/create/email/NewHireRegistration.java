package newHire.create.email;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

// registration class for the service, allows it to be discoverable from the client

public class NewHireRegistration {

	public static void main(String[] args) {
		try {
			// get an instance of a JmDNS
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			// setting service information to be passed into service info below
			String service_type = "_http._tcp.local.";
			String service_name = "Creating email";
			String service_description = "Service for creating an email for a new hire";
			int service_port = 9080;
			// create a service - with ServiceInfo
			ServiceInfo serviceinfo = ServiceInfo.create(service_type, service_name, service_port, service_description);
			// registering the service I just made
			jmdns.registerService(serviceinfo);
			// feedback for the user
			System.out.printf("Registering service with type %s and name %s on port %d", service_type, service_name, service_port);
			// good idea to wait a bit
			Thread.sleep(20000);

			System.out.println("Ready to unregister services...");

			// unregistering all services
			jmdns.unregisterService(serviceinfo);
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
