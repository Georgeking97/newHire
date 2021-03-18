package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import newHire.create.email.*;
import newHire.create.email.newHireGrpc.newHireBlockingStub;

import create.security.card.newHire1Grpc;
import create.security.card.newHire1Grpc.newHire1BlockingStub;
import create.security.card.newHire1Grpc.newHire1Stub;

public class GUI {
	private static newHireBlockingStub blockingStub;
	private static newHire1BlockingStub blockingStub2;
	private static newHire1Stub asyncStub2;
	private static ServiceInfo serviceinfo;
	private JFrame frame;
	private JTextField EnterEmailCreateTxt;
	private JTextField EnterEmailDeleteTxt;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		String email_service_type = "_http._tcp.local.";
		discoverService(email_service_type);
		String host = serviceinfo.getHostAddresses()[0];
		int port = serviceinfo.getPort();
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

		blockingStub = newHireGrpc.newBlockingStub(channel);
		blockingStub2 = newHire1Grpc.newBlockingStub(channel);
		asyncStub2 = newHire1Grpc.newStub(channel);

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
		}
	}

	private void initializer() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
	}

	private void createUI(JFrame frame2) {
		JTabbedPane tabbedPane = new JTabbedPane();
		JPanel panel1 = new JPanel(false);
		JPanel panel2 = new JPanel(false);
		JPanel panel3 = new JPanel(false);
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();
		JPanel p3 = new JPanel();
		JPanel p4 = new JPanel();
		JPanel p5 = new JPanel();
		JPanel p6 = new JPanel();
		JPanel p7 = new JPanel();
		JPanel p8 = new JPanel();
		JPanel p9 = new JPanel();

		BoxLayout boxlayout1 = new BoxLayout(panel1, BoxLayout.Y_AXIS);
		BoxLayout boxlayout2 = new BoxLayout(panel2, BoxLayout.Y_AXIS);
		BoxLayout boxlayout3 = new BoxLayout(panel3, BoxLayout.Y_AXIS);

		p1.setAlignmentX(0.6f);
		p1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p2.setAlignmentX(0.6f);
		p2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p3.setAlignmentX(0.6f);
		p3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p4.setAlignmentX(0.6f);
		p4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p5.setAlignmentX(0.6f);
		p5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p6.setAlignmentX(0.6f);
		p6.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p7.setAlignmentX(0.6f);
		p7.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p8.setAlignmentX(0.6f);
		p8.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		p9.setAlignmentX(0.6f);
		p9.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel1.setLayout(boxlayout1);
		tabbedPane.addTab("Tab 1", null, panel1, "Tab 1 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		panel2.setLayout(boxlayout2);
		tabbedPane.addTab("Tab 2", null, panel2, "Tab 2 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);
		panel3.setLayout(boxlayout3);
		tabbedPane.addTab("Tab 3", null, panel3, "Tab 3 tooltip");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_2);

		panel1.add(p1);
		panel1.add(p2);
		panel1.add(p3);
		panel2.add(p4);
		panel2.add(p5);
		panel2.add(p6);
		panel3.add(p7);
		panel3.add(p8);
		panel3.add(p9);

		JTextArea textResponse = new JTextArea(3, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		JLabel EnterEmailCreateLabel = new JLabel("Enter Email");
		p1.add(EnterEmailCreateLabel);

		EnterEmailCreateTxt = new JTextField();
		p1.add(EnterEmailCreateTxt);
		EnterEmailCreateTxt.setColumns(10);

		JButton EnterEmailCreateBtn = new JButton("Create Email");
		EnterEmailCreateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = EnterEmailCreateTxt.getText();
				EmailToCreate request = EmailToCreate.newBuilder().setText(name).build();
				EmailCreated response = blockingStub.createEmail(request);
				EnterEmailCreateTxt.setText("");
			}
		});
		p1.add(EnterEmailCreateBtn);

		JLabel EnterEmailDeleteLabel = new JLabel("Enter Email");
		p2.add(EnterEmailDeleteLabel);

		EnterEmailDeleteTxt = new JTextField();
		p2.add(EnterEmailDeleteTxt);
		EnterEmailDeleteTxt.setColumns(10);

		JButton EnterEmailDeleteBtn = new JButton("Delete Email");
		EnterEmailDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = EnterEmailDeleteTxt.getText();
				EmailToDelete request = EmailToDelete.newBuilder().setText(name).build();
				EmailDeleted response = blockingStub.deleteEmail(request);
				EnterEmailDeleteTxt.setText("");
			}
		});
		p2.add(EnterEmailDeleteBtn);

		JButton SeeEmailBtn = new JButton("See Email's");
		SeeEmailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResponse.setText(null);
				String input = "hey";
				Emails request = Emails.newBuilder().setText(input).build();
				Iterator<AllEmails> response = blockingStub.seeEmails(request);
				while (response.hasNext()) {
					AllEmails temp = response.next();
					textResponse.append("reply: " + temp.getValue());
				}
			}
		});
		p3.add(SeeEmailBtn);

		JScrollPane SeeEmailScroll = new JScrollPane(textResponse);
		p3.add(SeeEmailScroll);

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}
}
