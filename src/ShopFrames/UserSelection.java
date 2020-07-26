package ShopFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import UsersAndProducts.Customer;
import UsersAndProducts.CustomerMainFrame;
import UsersAndProducts.User;
import UsersAndProducts.usingTextfiles;
import java.awt.Color;

public class UserSelection extends JFrame {

	private JPanel contentPane;
	private JTable table;








	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSelection frame = new UserSelection();
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
	public UserSelection() throws IOException {
		ArrayList<User> userArray = usingTextfiles.readingUserFile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 805, 560);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 235, 205));
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Please select which user you are.");
		lblNewLabel.setBounds(21, 69, 207, 14);
		contentPane.add(lblNewLabel);
		
		JButton bttnCust1 = new JButton(userArray.get(1).getUsername());
		bttnCust1.setBackground(new Color(255, 222, 173));
		bttnCust1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerMainFrame obj = null;
				try {
					obj = new CustomerMainFrame((Customer) userArray.get(1), null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();	
				
			}
		});
		bttnCust1.setBounds(51, 118, 341, 75);
		contentPane.add(bttnCust1);
		
		JButton user2_Button = new JButton(userArray.get(2).getUsername());
		user2_Button.setBackground(new Color(255, 222, 173));
		user2_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerMainFrame obj = null;
				try {
					obj = new CustomerMainFrame((Customer) userArray.get(2), null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();	
			}
		});
		user2_Button.setBounds(264, 234, 341, 75);
		contentPane.add(user2_Button);
		
		JButton user3_Button = new JButton(userArray.get(3).getUsername());
		user3_Button.setBackground(new Color(255, 222, 173));
		user3_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerMainFrame obj = null;
				try {
					obj = new CustomerMainFrame((Customer) userArray.get(3), null);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();	
			}
		});
		user3_Button.setBounds(394, 370, 368, 75);
		contentPane.add(user3_Button);
		
		JButton btnGoBack = new JButton("<-- Back To Main Frame");
		btnGoBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame obj = null;
				try {
					obj = new mainFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();	
				
				
			}
		});
		btnGoBack.setBounds(24, 11, 217, 23);
		contentPane.add(btnGoBack);
		

		
	}
}
