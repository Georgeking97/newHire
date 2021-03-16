package create.security.card;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import create.security.card.newHire1Grpc.newHire1ImplBase;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class SecurityCardServer extends newHire1ImplBase {
	public static void main(String[] args) {
		SecurityCardServer classObj = new SecurityCardServer();
		Properties prop = classObj.getProperties();
		classObj.registerService(prop);
		int port = Integer.valueOf(prop.getProperty("service_port"));
		try {
			Server server = ServerBuilder.forPort(port).addService(classObj).build().start();
			System.out.println("Server started, awaiting RPC calls...");
			server.awaitTermination();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private Properties getProperties() {
		Properties prop = null;
		try (InputStream input = new FileInputStream("src/main/resources/properties/security_card.properties")) {
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

	private void registerService(Properties prop) {
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			String service_type = prop.getProperty("service_type");
			String service_name = prop.getProperty("service_name");
			String service_description_properties = prop.getProperty("service_description");
			int service_port = Integer.valueOf(prop.getProperty("service_port"));
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
}
