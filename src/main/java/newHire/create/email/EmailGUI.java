package newHire.create.email;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import newHire.create.email.newHireGrpc.newHireBlockingStub;
import newHire.create.email.newHireGrpc.newHireStub;
import javax.swing.SwingConstants;

public class EmailGUI {

	private static newHireBlockingStub blockingStub;
	private static newHireStub asyncStub;

	private static ServiceInfo serviceinfo;

	private JFrame frame;
	private JTextField createEmail;
	private JTextField deleteEmail;
	private JTextArea textResponse;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EmailGUI window = new EmailGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public EmailGUI() {
		String email_service_type = "_email._tcp.local.";
		// discovering services
		discoverService(email_service_type);
		String host = serviceinfo.getHostAddresses()[0];
		int port = serviceinfo.getPort();
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		blockingStub = newHireGrpc.newBlockingStub(channel);
		asyncStub = newHireGrpc.newStub(channel);

		initializer();
	}

	private void discoverService(String email_service_type) {
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(email_service_type, new MyServiceListener());
			Thread.sleep(10000);
			jmdns.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	// class that contains the service listener methods
	private static class MyServiceListener implements ServiceListener {

		public void serviceAdded(ServiceEvent event) {
			System.out.println("Service added: " + event.getInfo());
		}

		public void serviceRemoved(ServiceEvent event) {
			System.out.println("Service removed: " + event.getInfo());
		}

		public void serviceResolved(ServiceEvent event) {
			System.out.println("Service resolved: " + event.getInfo());
			serviceinfo = event.getInfo();
			System.out.println("Host: " + serviceinfo.getHostAddresses()[0]);
			System.out.println("Port: " + serviceinfo.getPort());
			System.out.println("Type: " + serviceinfo.getType());
			System.out.println("Name:  " + serviceinfo.getName());
			System.out.println("Server: " + serviceinfo.getServer().replace(".local", ""));
			System.out.println("Description/Properties: " + serviceinfo.getNiceTextString());
		}
	}

	private void initializer() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 393);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textResponse = new JTextArea(3, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		BoxLayout bl = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);

		frame.getContentPane().setLayout(bl);
		// panel for creating email's
		JPanel panel_service_1 = new JPanel();
		panel_service_1.setAlignmentX(0.6f);
		frame.getContentPane().add(panel_service_1);
		panel_service_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		// panel for deleting email's
		JPanel panel_service_2 = new JPanel();
		panel_service_2.setAlignmentX(0.6f);
		frame.getContentPane().add(panel_service_2);
		panel_service_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		// panel for seeing all email's
		JPanel panel_service_3 = new JPanel();
		panel_service_3.setAlignmentX(0.6f);
		frame.getContentPane().add(panel_service_3);
		panel_service_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton seeButton = new JButton("See email's");
		panel_service_3.add(seeButton);

		seeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = "hey";
				Emails request = Emails.newBuilder().setText(input).build();

				Iterator<AllEmails> response = blockingStub.seeEmails(request);
				while (response.hasNext()) {
					AllEmails temp = response.next();
					textResponse.append("reply: " + temp.getValue());
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(textResponse);
		panel_service_3.add(scrollPane);

		JLabel createEmailName = new JLabel("Enter Name:");
		panel_service_1.add(createEmailName);

		createEmail = new JTextField();
		panel_service_1.add(createEmail);
		createEmail.setColumns(10);

		JButton createButton = new JButton("Create Email");
		panel_service_1.add(createButton);

		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = createEmail.getText();
				MessageRequest request = MessageRequest.newBuilder().setText(name).build();
				MessageReply response = blockingStub.sendMessage(request);
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Enter Name:");
		panel_service_2.add(lblNewLabel_2);

		deleteEmail = new JTextField();
		panel_service_2.add(deleteEmail);
		deleteEmail.setColumns(10);

		JButton deleteButton = new JButton("Delete Email");
		panel_service_2.add(deleteButton);

	}
}
