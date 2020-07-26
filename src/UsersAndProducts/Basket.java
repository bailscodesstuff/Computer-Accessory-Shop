package UsersAndProducts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.TableModel;

import UsersAndProducts.LogfileInfo.CustomerDecision;
import UsersAndProducts.LogfileInfo.PaymentType;

public class Basket {
	
	String barcode;
	String retailCost;
	int quantityOfItem;
	Products Mouse;
	Products Keyboard;
	
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getRetailCost() {
		return retailCost;
	}

	public void setRetailCost(String retailCost) {
		this.retailCost = retailCost;
	}

	public int getQuantityOfItem() {
		return quantityOfItem;
	}

	public void setQuantityOfItem(int quantityOfItem) {
		this.quantityOfItem = quantityOfItem;
	}

	public Basket(String barcode, String retailCost, int quantityOfItem) {
		this.barcode=barcode;
		this.retailCost = retailCost;
		this.quantityOfItem = quantityOfItem;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public String toString() {
		return (getBarcode() + "," + getRetailCost() + "," + getQuantityOfItem());
	}
	
	public Basket() {
		super();
	}
	
	
//	updates logfile with the customers purchase info
	public void confirmPurchase(CustomerDecision choice, List<Basket> customerBasket, Customer customer, PaymentType paymentDetails) throws IOException {
		
		ArrayList<Log> loglist = new ArrayList<Log>();
		
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String today = formatter.format(date);
		
		
		BufferedWriter bw = null;
		try {
			
			
			bw = new BufferedWriter(new FileWriter("ActivityLog.txt", true));
			
			for(int basketItem=0; basketItem<customerBasket.size(); basketItem++) {
				String newInfo[] = new String[8];
				newInfo[0] = customer.getUserID();
				newInfo[1] = customer.getPostcode();
				newInfo[2] = customerBasket.get(basketItem).getBarcode();
				newInfo[3] = customerBasket.get(basketItem).getRetailCost();
				newInfo[4] = String.valueOf(customerBasket.get(basketItem).getQuantityOfItem());	
				newInfo[5] = String.valueOf(choice);
				newInfo[6] = String.valueOf(paymentDetails);
				newInfo[7] = today;
				
				
			bw.append( newInfo[0] + "," + newInfo[1] + "," + newInfo[2] + "," + newInfo[3] + "," + newInfo[4]
						+ "," + newInfo[5] + "," +newInfo[6]  + "," + newInfo[7]);
			
			bw.newLine();
		
			}
			customerBasket.clear();
		
		}catch (Exception e1) {
			e1.printStackTrace();
		}

		bw.close();
		Log log = new Log();
		log.updateLog();
		
	}
	

	
	public double getTotal(List<Basket> customerBasket) {
		
	double total = 0;
	for(int i=0; i<customerBasket.size(); i++) {
		double cost = Double.valueOf(customerBasket.get(i).getRetailCost());
		int quantity = customerBasket.get(i).getQuantityOfItem();
		double sum = cost*quantity;
		
		total = total + sum;
	}
	
	System.out.println(total);
		return total;
	}
	
	
	
	
	
	
	
	
		
		
	
	
	

}
