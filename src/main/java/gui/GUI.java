package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import newHire.create.permissions.AllPermissions;
import newHire.create.permissions.CreatedPermission;
import newHire.create.permissions.NewPermission;
import newHire.create.permissions.RequestPermissions;
import newHire.create.permissions.newHireGrpc;
import newHire.create.permissions.permissionRequest;
import newHire.create.permissions.permissionResponse;
import newHire.create.permissions.newHireGrpc.newHireBlockingStub;
import newHire.create.permissions.newHireGrpc.newHireStub;

import create.security.card.CardDeleted;
import create.security.card.CardsReturned;
import create.security.card.RequestCards;
import create.security.card.SpecifyCard;
import create.security.card.card;
import create.security.card.cardCreated;
import create.security.card.newHire1Grpc;
import create.security.card.newHire1Grpc.newHire1BlockingStub;
import create.security.card.newHire1Grpc.newHire1Stub;

public class GUI {

	private static newHire1BlockingStub blockingStub2;
	private static newHire1Stub asyncStub2;
	private static newHireBlockingStub blockingStub;
	private static newHireStub asyncStub;

	private static ServiceInfo serviceinfo;
	private JFrame frame;
	private JTextField EnterEmailCreateTxt;
	private JTextField EnterEmailDeleteTxt;
	private JTextField addSecurityPermissionTxt;
	private JTextField cardDeleteTxt;
	private JTextField CreatePermissionTxt;
	private JTextField SetPermissionTxt;

	ArrayList<String> cards = new ArrayList<>();
	ArrayList<String> permissions = new ArrayList<>();

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
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, 50053).usePlaintext().build();
		ManagedChannel channe2 = ManagedChannelBuilder.forAddress(host, 50052).usePlaintext().build();
		// ManagedChannel channe3 = ManagedChannelBuilder.forAddress(host,
		// 50053).usePlaintext().build();

		blockingStub2 = newHire1Grpc.newBlockingStub(channel);
		asyncStub2 = newHire1Grpc.newStub(channel);

		blockingStub = newHireGrpc.newBlockingStub(channe2);
		asyncStub = newHireGrpc.newStub(channe2);

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

		JTextArea textResponse = new JTextArea(3, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		JTextArea textResponse2 = new JTextArea(3, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		JTextArea textResponse3 = new JTextArea(3, 20);
		textResponse.setLineWrap(true);
		textResponse.setWrapStyleWord(true);

		JLabel addSecurityPermissionLbl = new JLabel("Enter Security permission");
		p4.add(addSecurityPermissionLbl);

		addSecurityPermissionTxt = new JTextField();
		p4.add(addSecurityPermissionTxt);
		addSecurityPermissionTxt.setColumns(10);

		JButton addSecurityPermissionBtn = new JButton("Add permission to card");
		addSecurityPermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.add(addSecurityPermissionTxt.getText());
				addSecurityPermissionTxt.setText("");
			}
		});
		p4.add(addSecurityPermissionBtn);

		JButton createSecurityCardBtn = new JButton("Create card");
		createSecurityCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("The size of the array when being sent to the create card method: " + cards.size());
				for (int i = 0; i < cards.size(); i++) {
					System.out.println("The card value is: " + cards.get(i));
				}
				StreamObserver<cardCreated> responseObserver = new StreamObserver<cardCreated>() {
					@Override
					public void onNext(cardCreated value) {
						JOptionPane.showMessageDialog(frame, value.getValue());
					}
					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}
					@Override
					public void onCompleted() {
						System.out.println("request complete");
						cards.clear();
					}
				};
				StreamObserver<card> requestObserver = asyncStub2.createCard(responseObserver);
				try {
					if (cards.size() > 0) {
						for (int i = 0; i < cards.size(); i++) {
							requestObserver.onNext(card.newBuilder().setText(cards.get(i).toString()).build());
							Thread.sleep(1000);
						}
						requestObserver.onCompleted();
					} else {
						System.out.println("No permissions to create a card have been set");
					}
					Thread.sleep(500);
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		p4.add(createSecurityCardBtn);
		panel2.add(p5);

		JLabel cardDeleteLbl = new JLabel("Enter Card to Delete");
		p5.add(cardDeleteLbl);

		cardDeleteTxt = new JTextField();
		p5.add(cardDeleteTxt);
		cardDeleteTxt.setColumns(10);

		JButton cardDeleteBtn = new JButton("Delete Card");
		cardDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String card = cardDeleteTxt.getText();
				SpecifyCard request = SpecifyCard.newBuilder().setText(card).build();
				CardDeleted response = blockingStub2.deleteCard(request);
				JOptionPane.showMessageDialog(frame, response.getValue());
				cardDeleteTxt.setText("");
			}
		});
		p5.add(cardDeleteBtn);
		panel2.add(p6);

		JButton SeeCardsBtn = new JButton("See Cards");
		p6.add(SeeCardsBtn);
		panel3.add(p7);

		JButton SeePermissionsBtn = new JButton("See Permissions");
		SeePermissionsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResponse.setText(null);
				RequestPermissions request = RequestPermissions.newBuilder().setText("request").build();
				Iterator<AllPermissions> response = blockingStub.seePermissions(request);
				while (response.hasNext()) {
					AllPermissions tmp = response.next();
					textResponse.append("Permission: " + tmp.getValue() + "\n");
				}
			}
		});

		p7.add(SeePermissionsBtn);
		panel3.add(p8);

		JLabel CreatePermissionLbl = new JLabel("Enter Permission");
		p8.add(CreatePermissionLbl);

		CreatePermissionTxt = new JTextField();
		p8.add(CreatePermissionTxt);
		CreatePermissionTxt.setColumns(10);

		JButton CreatePermissionBtn = new JButton("Create Permission");
		CreatePermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String requestMessage = CreatePermissionTxt.getText();
				NewPermission request = NewPermission.newBuilder().setText(requestMessage).build();
				CreatedPermission response = blockingStub.setPermissions(request);
				JOptionPane.showMessageDialog(frame, response.getValue());
				CreatePermissionTxt.setText("");
			}
		});
		p8.add(CreatePermissionBtn);
		panel3.add(p9);

		JLabel SetPermissionLbl = new JLabel("Enter Permission");
		p9.add(SetPermissionLbl);

		SetPermissionTxt = new JTextField();
		p9.add(SetPermissionTxt);
		SetPermissionTxt.setColumns(10);

		JButton SetPermissionBtn2 = new JButton("Request Permission");
		SetPermissionBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StreamObserver<permissionResponse> responseObserver = new StreamObserver<permissionResponse>() {
					@Override
					public void onNext(permissionResponse value) {
						JOptionPane.showMessageDialog(frame, value.getValue());
						System.out.println("Response is: " + value.getValue());
					}
					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}
					@Override
					public void onCompleted() {

					}
				};
				StreamObserver<permissionRequest> requestObserver = asyncStub.permissions(responseObserver);
				try {
					requestObserver.onNext(permissionRequest.newBuilder().setText(SetPermissionTxt.getText()).build());
					Thread.sleep(1000);
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		p9.add(SetPermissionBtn2);

		JLabel EnterEmailCreateLabel = new JLabel("Enter Email");
		p1.add(EnterEmailCreateLabel);

		EnterEmailCreateTxt = new JTextField();
		p1.add(EnterEmailCreateTxt);
		EnterEmailCreateTxt.setColumns(10);

		JButton EnterEmailCreateBtn = new JButton("Create Email");

		p1.add(EnterEmailCreateBtn);

		JLabel EnterEmailDeleteLabel = new JLabel("Enter Email");
		p2.add(EnterEmailDeleteLabel);

		EnterEmailDeleteTxt = new JTextField();
		p2.add(EnterEmailDeleteTxt);
		EnterEmailDeleteTxt.setColumns(10);

		JButton EnterEmailDeleteBtn = new JButton("Delete Email");
		p2.add(EnterEmailDeleteBtn);

		JButton SeeEmailBtn = new JButton("See Email's");
		SeeEmailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		p3.add(SeeEmailBtn);

		JScrollPane SeePermissionsScroll = new JScrollPane(textResponse);
		p7.add(SeePermissionsScroll);

		JScrollPane SeeEmailScroll = new JScrollPane(textResponse2);
		p3.add(SeeEmailScroll);

		JScrollPane seeCardsScroll = new JScrollPane(textResponse3);
		p6.add(seeCardsScroll);

		SeeCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResponse3.setText("");
				String requestMessage = "request";
				RequestCards request = RequestCards.newBuilder().setText(requestMessage).build();
				Iterator<CardsReturned> response = blockingStub2.seeCards(request);
				while (response.hasNext()) {
					CardsReturned temp = response.next();
					textResponse3.append(temp.getValue().toString());
				}
			}
		});

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}
}
