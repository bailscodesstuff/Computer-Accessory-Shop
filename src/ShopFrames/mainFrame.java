package ShopFrames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UsersAndProducts.Admin;
//import UsersAndProducts.Admin;
import UsersAndProducts.User;
import UsersAndProducts.adminMainFrame;
import UsersAndProducts.usingTextfiles;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;

public class mainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launelh the application.
	 */
	
	//ONLY THE mainFrame is the thing that runs. SO no other classes need a main method !!!!!
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
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
	public mainFrame() throws IOException {
		ArrayList<User> userArray = usingTextfiles.readingUserFile();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 662, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton adminBttn = new JButton("ADMIN\r\n");
		adminBttn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adminMainFrame obj = null;
				try {
					obj = new adminMainFrame((Admin) userArray.get(0));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				obj.setVisible(true);         
				dispose();								
			}
		});
		adminBttn.setFont(new Font("Tahoma", Font.BOLD, 30));
		adminBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		adminBttn.setBounds(58, 265, 199, 68);
		contentPane.add(adminBttn);
		
		JButton customerBttn = new JButton("CUSTOMER");
		customerBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		customerBttn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserSelection obj = null;
				try {
					obj = new UserSelection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose(); 
			}
		});
		customerBttn.setFont(new Font("Tahoma", Font.BOLD, 30));
		customerBttn.setBounds(356, 255, 242, 78);
		contentPane.add(customerBttn);
		
		JLabel lblNewLabel = new JLabel("C");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 99));
		lblNewLabel.setBounds(216, 26, 83, 122);
		contentPane.add(lblNewLabel);
		
		JLabel lblAdminIcon = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/African-Boss-icon.png")).getImage();
		lblAdminIcon.setIcon(new ImageIcon(img));
		lblAdminIcon.setBounds(88, 130, 169, 136);
		contentPane.add(lblAdminIcon);
		
		JLabel lblNewLabel_1 = new JLabel("A");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 99));
		lblNewLabel_1.setBounds(267, 55, 88, 107);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("S");
		lblNewLabel_2.setForeground(Color.YELLOW);
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 99));
		lblNewLabel_2.setBounds(330, 27, 70, 120);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblCustImage = new JLabel("");
		Image custImg = new ImageIcon(this.getClass().getResource("/Shopping-icon.png")).getImage();
		lblCustImage.setIcon(new ImageIcon(custImg));
		lblCustImage.setBounds(385, 137, 182, 107);
		contentPane.add(lblCustImage);
	}
}
