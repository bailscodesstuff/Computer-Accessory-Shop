package UsersAndProducts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ShopFrames.UserSelection;
import ShopFrames.mainFrame;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PurchaseFrame extends JFrame {

	private JPanel contentPane;
	private Customer customer;
	private List<Basket> customerBasket;
	private JTable jtFinalBasket;
	private JTextField tfEmail;
	private JTextField tfCardDetails;
	private JTextField tfSecruityCode;
	private DefaultTableModel dtmBasket;
	/**
	 * Launch the application.
	 */
	public static void main(Customer customer, List<Basket> customerBasket ) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseFrame frame = new PurchaseFrame(customer, customerBasket);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	/**
	 * @param customer
	 * @param customerBasket
	 * @throws IOException
	 */
	
	public PurchaseFrame(Customer customer, List<Basket> customerBasket) throws IOException {
		
		this.customer = customer;
		this.customerBasket = customerBasket;
		Basket basket = new Basket();
		Log log = new Log();
		ArrayList<User> userArray = usingTextfiles.readingUserFile();
		ArrayList<Products> productArray = usingTextfiles.getProductFile();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 470);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCustInfo = new JLabel("Hi, " + customer.getUsername());
		lblCustInfo.setBounds(10, 40, 179, 14);
		contentPane.add(lblCustInfo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(186, 39, 464, 112);
		contentPane.add(scrollPane);
		
		jtFinalBasket = new JTable();
		dtmBasket = new DefaultTableModel();
		
		
		dtmBasket.setColumnIdentifiers(new Object[] {"barcode", "retail price" , "no. in basket"});
		jtFinalBasket.setModel(dtmBasket);
	
		scrollPane.setViewportView(jtFinalBasket);
		
		// loads the customer's basket from the previous frame
		
		JButton bttnLoadBasket = new JButton("View Basket");
		bttnLoadBasket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dtmBasket.setRowCount(0);
				
				for(int i=0; i<customerBasket.size(); i++) {
					Object[] basketData = new Object[] {customerBasket.get(i).getBarcode(), customerBasket.get(i).getRetailCost(), customerBasket.get(i).getQuantityOfItem()};
				
					dtmBasket.addRow(basketData);
				}
				
			}
		});
		bttnLoadBasket.setBounds(51, 128, 125, 23);
		contentPane.add(bttnLoadBasket);
		
		JLabel lblNewLabel = new JLabel("This is your basket:");
		lblNewLabel.setBounds(187, 24, 143, 14);
		contentPane.add(lblNewLabel);
		
		/**
		 * Methods below are the cancel/save for later codes, they use enums to get the correct info to add to the textfile
		 * 
		 */		
		
		JButton bttnCancel = new JButton("Cancel Purchase");
		bttnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					basket.confirmPurchase(LogfileInfo.CustomerDecision.CANCELLED, customerBasket, customer, LogfileInfo.PaymentType.NOPAYMENT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "you cancelled your purchase. Now redirecting you to home page....");
				
				mainFrame obj = null;
				try {
					obj = new mainFrame();
					
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();
				
				
			}
		});
		bttnCancel.setBounds(349, 162, 135, 23);
		contentPane.add(bttnCancel);
		
		JButton bttnSave = new JButton("Save For Later");
		bttnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					basket.confirmPurchase(LogfileInfo.CustomerDecision.SAVED, customerBasket, customer, LogfileInfo.PaymentType.NOPAYMENT);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Your purchase has been saved for later. Now redirecting you to home page....");
				
				mainFrame obj = null;
				try {
					obj = new mainFrame();
					
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();
			}
		});
		bttnSave.setBounds(515, 162, 135, 23);
		contentPane.add(bttnSave);
		
		JLabel lblCardResp = new JLabel("");
		lblCardResp.setBounds(10, 391, 664, 14);
		contentPane.add(lblCardResp);
		
		JLabel lblPaypalResp = new JLabel("");
		lblPaypalResp.setBounds(116, 281, 445, 14);
		contentPane.add(lblPaypalResp);
		
		
		tfEmail = new JTextField();
		tfEmail.setBounds(117, 246, 213, 20);
		contentPane.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email: ");
		lblNewLabel_1.setBounds(51, 249, 49, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPaypal = new JLabel("Pay With Paypal:");
		lblPaypal.setBounds(10, 221, 116, 14);
		contentPane.add(lblPaypal);
		
		/**
		 * Methods below are the payment options. Includes validation checks/messages to ensure the details entered are valid.
		 * 
		 */
		JButton bttnPaypalPay = new JButton("Pay with Paypal");
		bttnPaypalPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean response = customer.validateEmail(tfEmail.getText());
				
				if(response == true) {
					try {
						customer.confirmPurchase(customerBasket, productArray);
					
						
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "thank you for your purchase. you paid £"+ basket.getTotal(customerBasket) + " by Paypal");
					
						basket.confirmPurchase(LogfileInfo.CustomerDecision.PURCHASED, customerBasket, customer, LogfileInfo.PaymentType.PAYPAL);
						
						mainFrame obj = null;
						try {
							obj = new mainFrame();
							
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						obj.setVisible(true);
						dispose();	

					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				
				}else {
					lblPaypalResp.setText("Invalid email! Make sure it has @, and .co.uk OR .com domains.");
				}
				
			}
		});
		bttnPaypalPay.setBounds(365, 245, 135, 23);
		contentPane.add(bttnPaypalPay);
		
		JLabel lblNewLabel_2 = new JLabel("Card Number:");
		lblNewLabel_2.setBounds(51, 329, 138, 14);
		contentPane.add(lblNewLabel_2);
		
		tfCardDetails = new JTextField();
		tfCardDetails.setBounds(170, 329, 125, 20);
		contentPane.add(tfCardDetails);
		tfCardDetails.setColumns(10);
		
		JLabel lblSecCode = new JLabel("Security Code");
		lblSecCode.setBounds(40, 363, 120, 14);
		contentPane.add(lblSecCode);
		
		tfSecruityCode = new JTextField();
		tfSecruityCode.setBounds(170, 360, 64, 20);
		contentPane.add(tfSecruityCode);
		tfSecruityCode.setColumns(10);
		
		JButton bttnCardPay = new JButton("Pay with Card\r\n");
		bttnCardPay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean response = customer.validateCardInfo(tfCardDetails.getText(), tfSecruityCode.getText());
			
				if(response == true) {
					try {
						customer.confirmPurchase(customerBasket, productArray);
						
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "thank you for your purchase. you paid "+ basket.getTotal(customerBasket) + " by Credit Card");

						basket.confirmPurchase(LogfileInfo.CustomerDecision.PURCHASED, customerBasket, customer, LogfileInfo.PaymentType.CREDITCARD);
						log.updateLog();
						
						mainFrame obj = null;
						try {
							obj = new mainFrame();
							
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						obj.setVisible(true);
						dispose();	

					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}else {
					lblCardResp.setText("Invalid entry for Card info ! Please ensure that card number is 16 digits, and the security code"
							+ "is 3 digits long.");
				}
			
			
			
			}
		});
		bttnCardPay.setBounds(365, 354, 135, 23);
		contentPane.add(bttnCardPay);
		

	
		

	}
}
