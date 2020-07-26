package UsersAndProducts;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import ShopFrames.UserSelection;
import ShopFrames.mainFrame;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class CustomerMainFrame extends JFrame {

	protected static final Object response = null;
	private JPanel contentPane;
	private JTable jtProducts;
	private DefaultTableModel dtmProducts;


	

	private boolean keyboardSearched;
	
	private JScrollPane scrollPane;
	private JButton btnAddtoBasket;
	private JTable jtCustomerBasket;
	private JScrollPane basketScrolPane;
	private DefaultTableModel dtmBasket;
	private JButton bttnConfirmPurchase;
	private JButton bttnCancelPurchase;
	private JButton bttnSaveLater;
	private JLabel lblFilter;
	private JLabel lblFilterBrand;
	private JLabel lblFilterLayout;
	private JComboBox cbBrandFilter;


	protected static boolean duplicate;
	private JComboBox cbLayout;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btntest;
	private Component brandList;
	
	private String CheckQuantityWanted;
	protected static boolean allowUpdateBasket;
	private Customer customer;
	private List<Basket> customerBasket = new ArrayList<Basket>();
	private JButton btnUserSelection;
	private JLabel lblBasket;
	protected static boolean isInteger;
	private boolean brandFilter;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(Customer customer,  List<Basket> customerBasket) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerMainFrame frame = new CustomerMainFrame(customer, customerBasket);
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
	public CustomerMainFrame(Customer customer, List<Basket> customerBasket) throws IOException {
		this.customer = customer;
		this.customerBasket = customerBasket;
		
		

		
		
		ArrayList<String> brandList = usingTextfiles.getFilterBrands();
		ArrayList<Keyboard> keyboardArray = usingTextfiles.getKeyboardFile();
		ArrayList<Products> availableProducts = usingTextfiles.getAvailableProducts();
	
		
		
		//this gives user option not to filter
		brandList.add("None");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 567);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 128, 114));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 81, 618, 206);
		contentPane.add(scrollPane);

		loadAllProducts(availableProducts);
		
		

		
		
		basketScrolPane = new JScrollPane();
		basketScrolPane.setEnabled(false);
		basketScrolPane.setBounds(10, 374, 640, 145);
		contentPane.add(basketScrolPane);
		
		jtCustomerBasket = new JTable();
		jtCustomerBasket.setEnabled(false);
		dtmBasket = new DefaultTableModel();
		dtmBasket.setColumnIdentifiers(new Object[] {"barcode", "device name", "device type", "brand", "colour", "connectivity",
													"no. in basket","retail price", "extraInfo"});
		jtCustomerBasket.setModel(dtmBasket);
		basketScrolPane.setViewportView(jtCustomerBasket);

	//adds a product to basket. includes all validation methods that allow you to update quantity of current products in your basket.
		btnAddtoBasket = new JButton("Add to Basket");
		btnAddtoBasket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				customer.addToBasket(jtProducts, jtCustomerBasket, availableProducts);
				}
			
		});
		
		btnAddtoBasket.setBounds(481, 298, 147, 22);
		contentPane.add(btnAddtoBasket);
		

		
		String[] arrayBrands = new String[brandList.size()];
		for(int i=0; i<arrayBrands.length; i++) {
			arrayBrands[i] = brandList.get(i);
		}
		cbBrandFilter = new JComboBox(arrayBrands);
		cbBrandFilter.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String filter=cbBrandFilter.getSelectedItem().toString();
				BrandFilter(filter, jtProducts, dtmProducts);
		
			}
		});
		cbBrandFilter.setBounds(119, 48, 107, 22);
		contentPane.add(cbBrandFilter);
		
		String layoutNames[] = {"None", "UK", "US", };
		cbLayout = new JComboBox(layoutNames);
		cbLayout.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
					if(keyboardSearched == true) {
				String filter=cbLayout.getSelectedItem().toString();
					BrandFilter(filter, jtProducts, dtmProducts);
					
					}else {
						Component frame = null;
						JOptionPane.showMessageDialog(frame, "you searched for a mouse! you cant change the keyboard layout!",
							    "Inane error", JOptionPane.ERROR_MESSAGE);
					}

			}
		
			});
		
		
		cbLayout.setBounds(385, 48, 107, 22);
		contentPane.add(cbLayout);
		
		
		JComboBox cbDeviceType = new JComboBox(productInfo.DeviceName.values());
		cbDeviceType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String filter=cbDeviceType.getSelectedItem().toString();
				if(filter == "keyboard" || filter == "None") {
					keyboardSearched = true;
				}else {
					keyboardSearched = false;
				}
			
				BrandFilter(filter, jtProducts, dtmProducts);
			}
		});

		cbDeviceType.setBounds(383, 15, 109, 22);
		contentPane.add(cbDeviceType);
		
		
		btntest = new JButton("CONTINUE TO CHECKOUT");
		btntest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btntest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				//creates an arraylist out of the rows in the customer basket JTable
				List<Basket> customerBasket = new ArrayList<Basket>();
				for(int row =0; row<jtCustomerBasket.getRowCount(); row++ ) {
					customerBasket.add(new Basket(jtCustomerBasket.getValueAt(row, 0).toString(), jtCustomerBasket.getValueAt(row, 7).toString(),
							(int) jtCustomerBasket.getValueAt(row, 6)));
				}
				
				if(!customerBasket.isEmpty()) {
				
				PurchaseFrame obj = null;
				try {
					obj = new PurchaseFrame(customer,customerBasket);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				obj.setVisible(true);
				dispose();	
			}else {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Your basket is empty. Please add products before continuing to checkout.",
					    "Inane error", JOptionPane.ERROR_MESSAGE);
			}
			}
			
		});
		btntest.setBounds(660, 467, 199, 52);
		contentPane.add(btntest);
		
		JLabel lblBrand = new JLabel("Brand:");
		lblBrand.setBounds(43, 36, 49, 34);
		contentPane.add(lblBrand);
		
		JLabel lblLayout = new JLabel("Keyboard Layout:\r\n");
		lblLayout.setBounds(271, 48, 142, 14);
		contentPane.add(lblLayout);
		
		JLabel labelCustInfo = new JLabel("Hi, " + customer.getUsername());
		labelCustInfo.setBounds(27, 21, 131, 14);
		contentPane.add(labelCustInfo);
	
		
		JLabel lblDeviceName = new JLabel("Device Name");
		lblDeviceName.setBounds(271, 19, 102, 14);
		contentPane.add(lblDeviceName);
		
		btnUserSelection = new JButton("LOGOUT\r\n");
		btnUserSelection.setToolTipText("returns you to the customer selection frame.");
		btnUserSelection.addMouseListener(new MouseAdapter() {
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
		btnUserSelection.setBounds(634, 49, 217, 23);
		contentPane.add(btnUserSelection);
		
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
		btnGoBack.setBounds(634, 15, 217, 23);
		contentPane.add(btnGoBack);
		
		lblBasket = new JLabel("Your current basket:");
		lblBasket.setBounds(10, 355, 254, 14);
		contentPane.add(lblBasket);

		

	}
		


	public void BrandFilter (String filter, JTable jtProducts, DefaultTableModel dtmSortBy){
		
		TableRowSorter<DefaultTableModel> sorter  = new TableRowSorter<DefaultTableModel>(dtmSortBy);
		jtProducts.setRowSorter(sorter);
		
		if(filter != "None") {
			sorter.setRowFilter(RowFilter.regexFilter(filter));
		}else {
			jtProducts.setRowSorter(sorter);
		}

	}
	


	
//loads products into the jtable,

	public void loadAllProducts(ArrayList<Products> availableProducts) {

			jtProducts = new JTable();
			dtmProducts = new DefaultTableModel();
			dtmProducts.setColumnIdentifiers(new Object[] {"barcode", "device name", "device type", "brand", "colour", "connectivity",
														"no. in stock","retail price", "extra Info"});
			jtProducts.setModel(dtmProducts);

			scrollPane.setViewportView(jtProducts);
			dtmProducts.setRowCount(0);
			

		
			for(int i=0; i<availableProducts.size(); i++) {
					
					StockComparator sc = new StockComparator();
					Collections.sort(availableProducts, sc);
					Collections.reverse(availableProducts);
			
					
				if(availableProducts.get(i).getDeviceName().contains("keyboard")) {
					Object[] rowData = new Object[] {availableProducts.get(i).getBarcode(), availableProducts.get(i).getDeviceName(),
							availableProducts.get(i).getDeviceType(),availableProducts.get(i).getBrand(),availableProducts.get(i).getColour(),
							availableProducts.get(i).getConnectivity(), Integer.toString(availableProducts.get(i).getQuantityinStock()), availableProducts.get(i).getRetailCost(),
							((Keyboard) availableProducts.get(i)).getKeyboardLayout()};
					dtmProducts.addRow(rowData);
					
				}else if(availableProducts.get(i).getDeviceName().contains("mouse")){
					Object[] rowData = new Object[] {availableProducts.get(i).getBarcode(), availableProducts.get(i).getDeviceName(),
							availableProducts.get(i).getDeviceType(),availableProducts.get(i).getBrand(),availableProducts.get(i).getColour(),
							availableProducts.get(i).getConnectivity(), Integer.toString(availableProducts.get(i).getQuantityinStock()), availableProducts.get(i).getRetailCost(),
							((Mouse) availableProducts.get(i)).getNoOfButtons()};
					dtmProducts.addRow(rowData);
				
				}	
		
		}
			
			

	}




}
	
	


	


	
	
		

	
	

	
	


