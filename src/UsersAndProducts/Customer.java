package UsersAndProducts;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class Customer extends User {
	
	int numberItemsInBasket;

	ArrayList<Products> ALcustomerBasket;
	
			
	public Customer(String userID, String username, String surname, String doorNumber, String postcode, String town, 
		String userType) {
		this.userID = userID;
		this.username = username;
		this.surname = surname;
		this.doorNumber = doorNumber;
		this.postcode = postcode;
		this.town = town;
		this.userType = userType;
		this.numberItemsInBasket = 0; 
	}
	
	
	

//once customer confirms their purchase, the product array list is updated accordingly.
	public void confirmPurchase(List<Basket> customerBasket, ArrayList<Products> productArray) throws IOException {
		
			
			for(int i=0; i<productArray.size(); i++) {
				for(int j=0; j<customerBasket.size(); j++) {
					if(productArray.get(i).getBarcode().contains(customerBasket.get(j).getBarcode())) {
						
						int quantityInStock = (productArray.get(i).getQuantityinStock()) - (customerBasket.get(j).getQuantityOfItem());
						
						productArray.get(i).setQuantityinStock(quantityInStock);
					}
				}
				
			}
			
			usingTextfiles.updateStockFile(productArray);	
		}
	
public void addToBasket(JTable jtProducts, JTable jtCustomerBasket, ArrayList<Products> availableProducts) {
		
	
			CustomerMainFrame.isInteger=true;
				if(jtProducts.getSelectedRow()!=-1) {
		
				String itemBarcode = jtProducts.getValueAt(jtProducts.getSelectedRow(), 0).toString();
				String origStockQuantity = (String) jtProducts.getValueAt(jtProducts.getSelectedRow(), 6);
		
				String CheckQuantityWanted = JOptionPane.showInputDialog("Enter amount wanted");
				
				try {
					Integer.parseInt(CheckQuantityWanted);
				}catch(Exception e1){
					 JOptionPane.showMessageDialog(null, "You have entered a non numeric value!");
				CustomerMainFrame.isInteger=false;
				}
					
				
					int basketQuantity = Integer.parseInt(CheckQuantityWanted);
					int stockQuantity = Integer.parseInt(origStockQuantity);
					
					//comapres the amount the user wants to add against the current stock value.
					if(basketQuantity <= stockQuantity) {
										try {
											for(int i=0; i<availableProducts.size(); i++) {
												//if the barcodes are equal, this method is run which updates the quantity in the basket/
												if(availableProducts.get(i).getBarcode() == itemBarcode) {
														duplicateProductCheck(availableProducts,itemBarcode,stockQuantity,basketQuantity, jtCustomerBasket);
				
												}
											}
											displayBasket(basketQuantity, jtProducts, jtCustomerBasket);
											 }catch (Exception e1) {
													e1.printStackTrace();
											 }
					 }else {
							Component frame = null;
							JOptionPane.showMessageDialog(frame, "you cannot have that many items! There are only "+ origStockQuantity + " in stock!",
								    "Inane error", JOptionPane.ERROR_MESSAGE);
						}
				
		
				
			
				 
			
		}else {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "please select a product before adding to basket.",
					    "Inane error", JOptionPane.ERROR_MESSAGE);
			}
			
			}
 

//method that gets the row from the product jtable, into the customer's basket jtable.
		//it transfers data from one row to another.
public void displayBasket(int basketQuantity, JTable jtProducts, JTable jtCustomerBasket) {
	
	if(CustomerMainFrame.duplicate == false) {
	//displays basket
	TableModel model = jtProducts.getModel();
	
	//individual variables
	int[] rowSelected = jtProducts.getSelectedRows();


	//entire array of product info
	Object[] row = new Object[9];
	TableModel model2 = jtCustomerBasket.getModel();
	

		for(int i=0; i<rowSelected.length; i++) {
		row[0] = model.getValueAt(rowSelected[i], 0);
		row[1] = model.getValueAt(rowSelected[i], 1);
		row[2] = model.getValueAt(rowSelected[i], 2);
		row[3] = model.getValueAt(rowSelected[i], 3);
		row[4] = model.getValueAt(rowSelected[i], 4);
		row[5] = model.getValueAt(rowSelected[i], 5);
		row[6] = basketQuantity;
		row[7] = model.getValueAt(rowSelected[i], 7);
		row[8] = model.getValueAt(rowSelected[i], 8);
	
		((DefaultTableModel) model2).addRow(row);
		
		}	
		
	
	}

}

	
//checks for duplicate barcodes in basket. if they are duplicates, the customer is able to update the quantity in their basket.	
	public void duplicateProductCheck(ArrayList availableProducts, String itemBarcode, int stockQuantity, int basketQuantity, JTable jtCustomerBasket) {
		
		int duplicateCheck = jtCustomerBasket.getRowCount();
		for (int basketRow=0; basketRow<duplicateCheck; basketRow++) {
		    
			String basketBarcode = jtCustomerBasket.getValueAt(basketRow, 0).toString();
		

			if (itemBarcode == basketBarcode) {
		      //make this yes or no option
		      int updateOption = JOptionPane.showConfirmDialog(null, "Do you want to update the amount in basket?", "helllo", JOptionPane.YES_NO_OPTION);
		      String findQuantity = (String) jtCustomerBasket.getValueAt(basketRow, 6).toString();
		     
		      //gets the value in the basket RIGHHT NOW 
		      int currentQuantity = Integer.parseInt(findQuantity);
		      
		      if(updateOption==0 && currentQuantity<stockQuantity) {
		    	  changeExistingQuantity(stockQuantity, basketQuantity, currentQuantity, basketRow, jtCustomerBasket);
		    	  
		      }else {
		    	  Component frame = null;
		    	  JOptionPane.showMessageDialog(frame, "you've currently got all the available stock in your basket! To confirm this selection so we can "
		    	  		+ "update our servers, please checkout.",
						    "Inane error", JOptionPane.ERROR_MESSAGE);
		    	  
		      }

		      CustomerMainFrame.duplicate = true;
		      break; 
		    } else {
		    	CustomerMainFrame.duplicate=false;
		    }
		
			}
		}
	
	//method that updates the quantity in the customer basket jtable.
	public void changeExistingQuantity(int stockQuantity, int basketQuantity, int currentQuantity, int basketRow, JTable jtCustomerBasket){
		CustomerMainFrame.allowUpdateBasket = true;

		if(currentQuantity < stockQuantity) {
		 	
		try {

				int newQuantity = currentQuantity+basketQuantity;

				if(newQuantity<=stockQuantity) {
					
					jtCustomerBasket.setValueAt(newQuantity, basketRow, 6);
					
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Invalid request! There are only " + (stockQuantity-currentQuantity) + " left that you can possibly put in your basket!");
				}
			
			} catch(InputMismatchException nfe) {
		        
		        JOptionPane.showMessageDialog(null, "You have entered a non numeric value!");
		    }
			
			
		}
		
		
		
		
	}
	
	
// validations for payment info (card or email)
	
	public boolean validateCardInfo(String cardNumber, String cardCode) {
		
		
		
		try {
			Long.parseLong(cardNumber);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		try {
			Integer.parseInt(cardCode);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		if(cardNumber.length()==16 && cardCode.length()==3) {
			return true;
		}else {
			return false;
		}
		
			
		

}
	
	public boolean validateEmail(String email) {
		
		if(email.contains("@") && (email.contains(".co.uk") || email.contains(".com"))) {
			return true;
		}else {
			return false;
		}
	}


}
	


	
	
	
	
	
	

	
	
	

	


