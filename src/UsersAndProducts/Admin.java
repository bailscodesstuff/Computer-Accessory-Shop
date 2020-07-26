package UsersAndProducts;

import java.awt.Component;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @author baile
 *
 */
public class Admin extends User{
	
	protected static int amountReq;
	protected static int origAmount;
	

	public Admin(String userID, String username, String surname, String doorNumber, String postcode, String town, 
			String userType) {
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.doorNumber = doorNumber;
		this.postcode = postcode;
		this.town = town;
		this.userType = userType;
		
		
	}

	
//	 method to ensure duplicate barcodes are not added to stock 
	
	public void checkDuplicateProduct(ArrayList productArray, DefaultTableModel dtmAddingProducts  ) {
		
		boolean doesntExist = false;
		
		int lastElement = productArray.size()-1;
		
		for(int i=0; i<productArray.size()-1; i++ ) {
			if(((Products) productArray.get(lastElement)).getBarcode().equals(((Products) productArray.get(i)).getBarcode())) {
				
				doesntExist=false;
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "you cannot add the same product twice, removing object.",
					    "Inane error", JOptionPane.ERROR_MESSAGE);
				productArray.remove(lastElement);
				System.out.println("you cannot add the same product twice!"+"removing object ....");
				
	
				
				break;
			}
			else {
				doesntExist = true;
				
			}
			
		}
	
		
		if(doesntExist == true) {
			try {
					if(((Products) productArray.get(lastElement)).getDeviceName().contains("keyboard")){
						usingTextfiles.updateKeyboard(productArray);
						
					}
					else if(((Products) productArray.get(lastElement)).getDeviceName().contains("mouse")) {
						usingTextfiles.updateMouse(productArray);
					}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
	}


	
	
	/* updates the product arraylist when you update the quantity of current stock */
	
	public void updateStock(int amountReq, int origAmount,	ArrayList<Products> productArray, String barcode) throws IOException {
			
		for (int i=0; i<productArray.size(); i++) {
			if(productArray.get(i).getBarcode().equals(barcode)){
				int newAmount = amountReq+origAmount;
				productArray.get(i).setQuantityinStock(newAmount);
			}
		}
	
		usingTextfiles.updateStockFile(productArray);
	}
	
	
	/* Validation methods to ensure you enter correct details when adding new stock.  */
	
	
	public String validateKeyboard(String barcode, String stock, String retPrice, String origPrice) {
		
		
		try {
			Integer.parseInt(barcode);
		}catch(Exception e) {
			return "Invalid Barcode!";
		}
		
		try {
			Integer.parseInt(stock);
		}catch(Exception e) {
			return "Invalid no. of stock";
		}
		
		
		try {
			Double.parseDouble(origPrice);
			
		}catch(Exception e) {
			return "Invalid Price";
		}
		
		try {
			Double.parseDouble(retPrice);
		}catch(Exception e) {
			return "Invalid Price";
		}
		
		return "";
	}
	
	
	public String validateMouse(String barcode, String stock, String retPrice, String origPrice, String buttons) {
		
		try {
			Integer.parseInt(barcode);
		}catch(Exception e) {
			return "Invalid Barcode!";
		}
		
		try {
			Integer.parseInt(stock);
			
		}catch(Exception e) {
			return "Invalid no. of stock";
		}
		
		try {
			Integer.parseInt(buttons);
		}catch(Exception e) {
			return "Invalid no. of Buttons";
		}
		
		try {
			Double.parseDouble(origPrice);
			
		}catch(Exception e) {
			return "Invalid Price";
		}
		
		try {
			Double.parseDouble(retPrice);
		}catch(Exception e) {
			return "Invalid Price";
		}
		
		return "";
	}
	
	public void updateExistingStock(JTable jtAdminProducts) {
		
		
		adminMainFrame.isInteger=true;
		if(jtAdminProducts.getSelectedRow()!=-1) {

			String barcode = jtAdminProducts.getValueAt(jtAdminProducts.getSelectedRow(), 0).toString();
			String stockNo = jtAdminProducts.getValueAt(jtAdminProducts.getSelectedRow(), 6).toString();
			int selectedRow = jtAdminProducts.getSelectedRow();
			int origAmount = Integer.parseInt(stockNo); 
			
			
			int updateOption = JOptionPane.showConfirmDialog(null, "Do you want to update the amount of this product in stock?", "helllo", JOptionPane.YES_NO_OPTION);

			if(updateOption ==0) {
				String amountRequested = JOptionPane.showInputDialog("Please enter the amount of extra stock you want to add");
				
				try {
					Integer.parseInt(amountRequested);
				}catch(Exception e1){
					Component frame = null;
					JOptionPane.showMessageDialog(frame, "please enter an appropriate integer value!",
						    "Inane error", JOptionPane.ERROR_MESSAGE);				
					adminMainFrame.isInteger=false;
				}
				
				int amountReq = Integer.parseInt(amountRequested);
				
				int newAmount = amountReq+origAmount;
				jtAdminProducts.setValueAt(newAmount,selectedRow,6);
				
				try {
					ArrayList<Products> productArray = usingTextfiles.getProductFile();
					for (int i=0; i<productArray.size(); i++) {
						if(productArray.get(i).getBarcode().equals(barcode)){
							productArray.get(i).setQuantityinStock(newAmount);
						}
					}
				
					usingTextfiles.updateStockFile(productArray);
					
					
					
					
					
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
				


		}else {
			Component frame = null;
			JOptionPane.showMessageDialog(frame, "please select a product you want to update first.",
				    "Inane error", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	

}
