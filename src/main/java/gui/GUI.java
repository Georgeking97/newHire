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
import java.util.ArrayList;
import java.util.Iterator;
import java.time.*;

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
		String service_type = "_http._tcp.local.";
		// discovering all the servers broadcasting on the predefined service type
		discoverService(service_type);
		String host = serviceinfo.getHostAddresses()[0];
		// due to having three services three channels need to be created to access all
		// three services, currently unable to access python
		ManagedChannel channel = ManagedChannelBuilder.forAddress(host, 50053).usePlaintext().build();
		ManagedChannel channe2 = ManagedChannelBuilder.forAddress(host, 50052).usePlaintext().build();
		
		// granting access to the methods stored on the servers
		blockingStub2 = newHire1Grpc.newBlockingStub(channel);
		asyncStub2 = newHire1Grpc.newStub(channel);
		blockingStub = newHireGrpc.newBlockingStub(channe2);
		asyncStub = newHireGrpc.newStub(channe2);

		initializer();
	}

	// discovery service used to find devices broadcasting on the network based on
	// their service type
	// uses JmDNS
	private void discoverService(String service_type) {
		try {
			JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());
			jmdns.addServiceListener(service_type, new MyServiceListener());
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

	// listener used by the discovery service method, allows the addition and
	// resolution of services
	// that are broadcasting on the network
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

	// creating the frame for the GUI
	private void initializer() {
		frame = new JFrame();
		frame.setTitle("Client - Service Controller");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI(frame);
	}

	// customizing the GUI and adding functionality to it
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

		// Adds a security card permission to my cards array to be later sent over with
		// my createSecurityCardBtn
		// the permission is taken from the text field where the user inputs
		JButton addSecurityPermissionBtn = new JButton("Add permission to card");
		addSecurityPermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String messageRequest = addSecurityPermissionTxt.getText();
				addSecurityPermissionTxt.setText("");
				if(!(messageRequest.isEmpty())) {
					JOptionPane.showMessageDialog(frame,"Your permission has been added to your card: " + messageRequest);
					cards.add(messageRequest);
				} else {
					JOptionPane.showMessageDialog(frame,"No permissions have been entered in the text field");
				}
			}
		});
		p4.add(addSecurityPermissionBtn);

		// sends the contents of the card array to my security card server and creates
		// the security card
		JButton createSecurityCardBtn = new JButton("Create card");
		createSecurityCardBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Receives the response from the security card server
				StreamObserver<cardCreated> responseObserver = new StreamObserver<cardCreated>() {
					@Override
					// when the server responds a pop up is show letting the user know their card
					// has been created and what permissions are on that security card
					public void onNext(cardCreated value) {
						JOptionPane.showMessageDialog(frame, "Your card has been created with the following values: "+value.getValue());
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
				// sends the requests to the security card server
				StreamObserver<card> requestObserver = asyncStub2.createCard(responseObserver);
				try {
					// for each permission in the card array send a request to the server
					// once the array is finished we close the stream and ask for a response back
					// if there is no objects in the array we let the user know with a pop up
					if (cards.size() > 0) {
						for (int i = 0; i < cards.size(); i++) {
							requestObserver.onNext(card.newBuilder().setText(cards.get(i)).build());
							Thread.sleep(1000);
						}
						// closing the stream once the for loop has gone through all objects in the card
						// array
						requestObserver.onCompleted();
					} else {
						JOptionPane.showMessageDialog(frame, "No permissions to create a card have been set");
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

		// deleting a security card with a simple RPC call
		JButton cardDeleteBtn = new JButton("Delete Card");
		cardDeleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// gets the card to be deleted by the taking the text in the text field by the
				// button sends this value to the server and creates a pop up letting the user
				// know
				// that the card has been deleted
				String card = cardDeleteTxt.getText();
				if(card.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Please enter a card to delete");
				} else {
					SpecifyCard request = SpecifyCard.newBuilder().setText(card).build();
					CardDeleted response = blockingStub2.deleteCard(request);
					// creating the pop up to show the user the servers response
					JOptionPane.showMessageDialog(frame, response.getValue());
				}
				// clears the text field to allow the user to input more values after
				cardDeleteTxt.setText("");
			}
		});
		p5.add(cardDeleteBtn);
		panel2.add(p6);

		JButton SeeCardsBtn = new JButton("See Cards");
		p6.add(SeeCardsBtn);
		panel3.add(p7);
		// a server side streaming request that shows the user all current permissions
		// available for setting uses a while loop to print every response that the
		// server sends until there is no more the response in this instance isn't
		// dependent on any specific request so we just send over a simple statement to
		// trigger the response
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

		// in order for a user to be able to request a permission it must first exist
		// by default no permissions exist. This is a simple RPC call that sends a
		// request to the permission server that creates the permission the user inputs.
		// A pop up is shown to the user to let them know their permission has been set
		// if a user tries to create a permission that already exists they are told so.
		JButton CreatePermissionBtn = new JButton("Create Permission");
		CreatePermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String requestMessage = CreatePermissionTxt.getText();
				if(!(requestMessage.isEmpty())){
					NewPermission request = NewPermission.newBuilder().setText(requestMessage).build();
					CreatedPermission response = blockingStub.setPermissions(request);
					JOptionPane.showMessageDialog(frame, response.getValue());
					CreatePermissionTxt.setText("");
				} else {
					JOptionPane.showMessageDialog(frame, "No permission has been entered in the text field");
				}
			}
		});
		
		p8.add(CreatePermissionBtn);
		panel3.add(p9);

		JLabel SetPermissionLbl = new JLabel("Enter Permission");
		p9.add(SetPermissionLbl);

		SetPermissionTxt = new JTextField();
		p9.add(SetPermissionTxt);
		SetPermissionTxt.setColumns(10);

		// A bi-directional call between the client and the permissions server. Used to
		// let a user set permissions and get instance notification that they have been
		// set.
		// Once the user enters a permission to be set the server responds and a pop up
		// is
		// created to inform the user.
		JButton SetPermissionBtn = new JButton("Request Permission");
		SetPermissionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Receiving the responses from the server, creates a pop up to let the user
				// know that their permission has been set
				StreamObserver<permissionResponse> responseObserver = new StreamObserver<permissionResponse>() {
					@Override
					public void onNext(permissionResponse value) {
						JOptionPane.showMessageDialog(frame, value.getValue());
						if (value.getValue().contains("Your permission has been set:")) {
							permissions.add(SetPermissionTxt.getText());
							System.out.println("Array Size: " + permissions.size());
						}
					}

					@Override
					public void onError(Throwable t) {
						t.printStackTrace();
					}

					@Override
					public void onCompleted() {
						System.out.println("Am I here?");
					}
				};
				// sending the requests to the server based on what the user puts in the text
				// field, there is a delay between each request to ensure no conflicts, their is
				// a hard limit of three permissions per request based on the array size
				StreamObserver<permissionRequest> requestObserver = asyncStub.permissions(responseObserver);
				try {
					String messageRequest = SetPermissionTxt.getText();
					if(!(messageRequest.isEmpty())) {
						requestObserver.onNext(permissionRequest.newBuilder().setText(SetPermissionTxt.getText()).build());
						Thread.sleep(1000);
						if (permissions.size() >= 3) {
							requestObserver.onCompleted();
							SetPermissionBtn.setEnabled(false);
							JOptionPane.showMessageDialog(frame, "All possible permissions set");
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Please enter a permission to request");
					}
				} catch (RuntimeException e1) {
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
		p9.add(SetPermissionBtn);

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

		// server side streaming to see all current security cards that have been
		// created
		// uses a while loop to go through each response the server sends and populates
		// a scroll view
		SeeCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textResponse3.setText("");
				String requestMessage = "request";
				RequestCards request = RequestCards.newBuilder().setText(requestMessage).build();
				Iterator<CardsReturned> response = blockingStub2.seeCards(request);
				while (response.hasNext()) {
					CardsReturned temp = response.next();
					textResponse3.append(temp.getValue());
				}
			}
		});

		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

	}
}
