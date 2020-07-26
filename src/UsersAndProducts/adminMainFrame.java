package UsersAndProducts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ShopFrames.mainFrame;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

public class adminMainFrame extends JFrame {

	private JPanel contentPane;
	private JTable jtAdminProducts;
	private DefaultTableModel dtmAddingProducts;
	
	
	private JTextField tfBrand;
	private JTextField tfColour;
	private JTextField tfBarcode;
	
	private JTextField tfStock;
	private JTextField tfOrigCos;
	private JTextField tfRetPrice;

	private JTextField tfBttnNo;
	private JLabel lblBarcode;
	private JLabel lblDevName;
	private JLabel lblDevType;
	private JLabel lblBrand;
	private JLabel lblColour;
	private JLabel lblConnect;
	private JLabel lblStockNo;
	private JLabel lblOrigCost;
	private JLabel lblRetPrice;
	private JLabel lblLayout;
	private JLabel lblButtons;
	private JButton btnAddKeyboard;
	private JButton bttnAddMouse;
	public Object[] rowData;
	private JButton bttnUpdateStock;
	private JComboBox cbConnectivity;
	private JComboBox cbDevName;
	private JComboBox cbDevTy;
	private JComboBox cbLayout;
 
	protected static boolean isInteger;

	/**
	 * Launch the application.
	 */
	public static void main(Admin admin) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminMainFrame frame = new adminMainFrame(admin);
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
	public adminMainFrame(Admin admin) throws IOException {
		
		
		ArrayList<Keyboard> keyboardArray = usingTextfiles.getKeyboardFile();
		ArrayList<Mouse> mouseArray = usingTextfiles.getMouseFile();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 804, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
	// button to view products by descending quantity in stock.
		JButton bttnViewProducts = new JButton("VIEW PRODUCTS");
		bttnViewProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dtmAddingProducts.setRowCount(0);
				
				try {
					ArrayList<Products> productArray = usingTextfiles.getProductFile();
					StockComparator sc = new StockComparator();
					Collections.sort(productArray, sc);
					Collections.reverse(productArray);
					
					
					for(int i=0; i<productArray.size(); i++) {
					
					if(productArray.get(i).getDeviceName().contains("keyboard")) {
						Object[] rowData = new Object[] {productArray.get(i).getBarcode(), productArray.get(i).getDeviceName(),
								productArray.get(i).getDeviceType(),productArray.get(i).getBrand(),productArray.get(i).getColour(),
								productArray.get(i).getConnectivity(), Integer.toString(productArray.get(i).getQuantityinStock()), productArray.get(i).getRetailCost(),
								productArray.get(i).getOriginalCost(),((Keyboard) productArray.get(i)).getKeyboardLayout()};
						dtmAddingProducts.addRow(rowData);
						
					}else {
						Object[] rowData = new Object[] {productArray.get(i).getBarcode(), productArray.get(i).getDeviceName(),
								productArray.get(i).getDeviceType(),productArray.get(i).getBrand(),productArray.get(i).getColour(),
								productArray.get(i).getConnectivity(), Integer.toString(productArray.get(i).getQuantityinStock()), productArray.get(i).getRetailCost(),
								productArray.get(i).getOriginalCost(),((Mouse) productArray.get(i)).getNoOfButtons()};
						dtmAddingProducts.addRow(rowData);
						
					}
					
						
				
			}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				e1.printStackTrace();
				}
				
			

			
			}
				
			
				
				
			
			
		});
		bttnViewProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		bttnViewProducts.setBounds(596, 283, 165, 30);
		contentPane.add(bttnViewProducts);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 325, 734, 178);
		contentPane.add(scrollPane);
		
		jtAdminProducts = new JTable();
		jtAdminProducts.setAutoCreateRowSorter(true);
		
		dtmAddingProducts = new DefaultTableModel();
		
		
		dtmAddingProducts.setColumnIdentifiers(new Object[] {"barcode", "device name", "device type", "brand", "colour", "connectivity",
				"no. in stock","original cost", "retail price", "extraInfo"});
	
		jtAdminProducts.setModel(dtmAddingProducts);
	

		scrollPane.setViewportView(jtAdminProducts);
		
		cbConnectivity = new JComboBox(productInfo.Connectivity.values());
		cbConnectivity.setBounds(105, 182, 96, 20);
		contentPane.add(cbConnectivity);
		
		
		cbDevName = new JComboBox(productInfo.DeviceName.values());
		cbDevName.setBounds(105, 58, 96, 20);
		contentPane.add(cbDevName);
		
		
		tfBrand = new JTextField();
		tfBrand.setBounds(105, 120, 96, 20);
		contentPane.add(tfBrand);
		tfBrand.setColumns(10);
		
		tfColour = new JTextField();
		tfColour.setBounds(105, 151, 96, 20);
		contentPane.add(tfColour);
		tfColour.setColumns(10);
		
		
		tfBarcode = new JTextField();
		tfBarcode.setBounds(105, 27, 96, 20);
		contentPane.add(tfBarcode);
		tfBarcode.setColumns(10);
		
		cbDevTy = new JComboBox(productInfo.DeviceType.values());
		cbDevTy.setBounds(105, 89, 96, 20);
		contentPane.add(cbDevTy);
		
		
		
		tfStock = new JTextField();
		tfStock.setBounds(105, 213, 96, 20);
		contentPane.add(tfStock);
		tfStock.setColumns(10);
		
		tfOrigCos = new JTextField();
		tfOrigCos.setBounds(105, 244, 96, 20);
		contentPane.add(tfOrigCos);
		tfOrigCos.setColumns(10);
		
		tfRetPrice = new JTextField();
		tfRetPrice.setBounds(105, 277, 96, 20);
		contentPane.add(tfRetPrice);
		tfRetPrice.setColumns(10);
		
		cbLayout = new JComboBox(productInfo.Layout.values());
		cbLayout.setBounds(342, 120, 96, 20);
		contentPane.add(cbLayout);
		
		
		tfBttnNo = new JTextField();
		tfBttnNo.setBounds(342, 182, 96, 20);
		contentPane.add(tfBttnNo);
		tfBttnNo.setColumns(10);
		
		lblBarcode = new JLabel("Barcode");
		lblBarcode.setBounds(10, 30, 49, 14);
		contentPane.add(lblBarcode);
		
		lblDevName = new JLabel("Device Name:");
		lblDevName.setBounds(10, 61, 85, 14);
		contentPane.add(lblDevName);
		
		lblDevType = new JLabel("Device Type:");
		lblDevType.setBounds(10, 92, 95, 14);
		contentPane.add(lblDevType);
		
		lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(10, 123, 49, 14);
		contentPane.add(lblBrand);
		
		lblColour = new JLabel("Colour:");
		lblColour.setBounds(10, 154, 49, 14);
		contentPane.add(lblColour);
		
		lblConnect = new JLabel("Connectivity:");
		lblConnect.setBounds(10, 185, 85, 14);
		contentPane.add(lblConnect);
		
		lblStockNo = new JLabel("No. in Stock:");
		lblStockNo.setBounds(10, 216, 95, 14);
		contentPane.add(lblStockNo);
		
		lblOrigCost = new JLabel("Original Cost:");
		lblOrigCost.setBounds(10, 247, 85, 14);
		contentPane.add(lblOrigCost);
		
		lblRetPrice = new JLabel("Retail Price:");
		lblRetPrice.setBounds(10, 280, 85, 14);
		contentPane.add(lblRetPrice);
		
		lblLayout = new JLabel("Layout:");
		lblLayout.setBounds(294, 123, 49, 14);
		contentPane.add(lblLayout);
		
		lblButtons = new JLabel("No. Buttons:");
		lblButtons.setBounds(270, 185, 79, 14);
		contentPane.add(lblButtons);
		
		
		JLabel lblResp = new JLabel("");
		lblResp.setBounds(318, 30, 222, 30);
		contentPane.add(lblResp);
		
//	allows user to add a new keyboard includes validation checks to stop duplicate barcodes from being added.
		
		btnAddKeyboard = new JButton("ADD KEYBOARD");
		btnAddKeyboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String response = admin.validateKeyboard(tfBarcode.getText(),tfStock.getText(),tfRetPrice.getText() ,tfOrigCos.getText());
				
				if(response == "") {
				
					keyboardArray.add(new Keyboard(tfBarcode.getText(),cbDevName.getSelectedItem().toString(),
						cbDevTy.getSelectedItem().toString(), tfBrand.getText(), tfColour.getText(), cbConnectivity.getSelectedItem().toString(), Integer.parseInt(tfStock.getText()),
						Double.parseDouble(tfOrigCos.getText()), Double.parseDouble(tfRetPrice.getText()),cbLayout.getSelectedItem().toString()));

					admin.checkDuplicateProduct(keyboardArray, dtmAddingProducts);
				}else {
					lblResp.setText(response);
				}

			}
		});
		btnAddKeyboard.setBounds(486, 119, 181, 23);
		contentPane.add(btnAddKeyboard);
		
		
//	allows user to add new mouse , includes validation checks to stop duplicate barcodes from being added.
		bttnAddMouse = new JButton("ADD MOUSE");
		bttnAddMouse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String response = admin.validateMouse(tfBarcode.getText(),tfStock.getText(),tfRetPrice.getText() ,tfOrigCos.getText(),tfBttnNo.getText());
				
				if(response == "") {
				mouseArray.add(new Mouse(tfBarcode.getText(), cbDevName.getSelectedItem().toString(),
						 cbDevTy.getSelectedItem().toString(), tfBrand.getText(), tfColour.getText(), cbConnectivity.getSelectedItem().toString(), Integer.parseInt(tfStock.getText()),
						 Double.parseDouble(tfOrigCos.getText()), Double.parseDouble(tfRetPrice.getText()), tfBttnNo.getText()));

				
				admin.checkDuplicateProduct(mouseArray, dtmAddingProducts);
				}else {
					lblResp.setText(response);
				}
			}
		});
		bttnAddMouse.setBounds(486, 181, 181, 23);
		contentPane.add(bttnAddMouse);
		
		
// allows admin to update quantity of current stock available in the product file. 
		
		bttnUpdateStock = new JButton("Update Existing Stock");
		bttnUpdateStock.setToolTipText("Select a product in the table below that you want to update the quantity of!");
		bttnUpdateStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				admin.updateExistingStock(jtAdminProducts);
			}
		});
		bttnUpdateStock.setBounds(263, 283, 165, 30);
		contentPane.add(bttnUpdateStock);
		
		JButton btnGoBack = new JButton("Back To Main Frame");
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
		btnGoBack.setBounds(571, 11, 190, 20);
		contentPane.add(btnGoBack);
		
		
		
		
		
		
	}
}
