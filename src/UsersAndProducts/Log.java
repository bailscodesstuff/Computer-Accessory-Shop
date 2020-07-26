package UsersAndProducts;

import UsersAndProducts.LogfileInfo.CustomerDecision;
import UsersAndProducts.LogfileInfo.PaymentType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import UsersAndProducts.Customer;
import UsersAndProducts.PurchaseFrame;




/**
 * This class' purpose is to create 'Log' objects from each line in the textfile. 
 * Doing so allowed me to sort the logfile in reverse order. 
 *
 */
public class Log {
	
	String userID,postcode, quantityOfItem;
	String barcode,retailCost,choice, paymentDetails;
	Date dateToday;

	public Log(String userID, String postcode, String barcode, String retailCost,String quantityOfItem, String choice, 
					String paymentDetails, Date dateToday ) {
		// TODO Auto-generated constructor stub
		this.userID = userID;
		this.postcode = postcode;
		this.barcode = barcode;
		this.retailCost = retailCost;
		this.quantityOfItem = quantityOfItem; 
		this.choice = choice;
		this.paymentDetails = paymentDetails;
		this.dateToday = dateToday;
	}
	
		public Log() {
			super();
		// TODO Auto-generated constructor stub
	}

		public Date getDateToday() {
		return (this.dateToday);
	}
		
		public String getUserID() {
			return (this.userID);
		}

		public String getPostcode() {
			return (this.postcode);
		}

		public String getQuantityOfItem() {
			return (this.quantityOfItem);
		}

		public String getBarcode() {
			return (this.barcode);
		}

		public String getRetailCost() {
			return (this.retailCost);
		}

		public String getChoice() {
			return (this.choice);
		}

		public String getPaymentDetails() {
			return (this.paymentDetails);
		}
		

		@Override
		public String toString() {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return (getUserID()+","+getPostcode()+","+ getBarcode() +","+ getRetailCost() +","+ getQuantityOfItem() +","+
			getChoice() +","+ getPaymentDetails() +","+ formatter.format(getDateToday()));
		}


		//sorts the logfile in reverse date order.
		
		public void updateLog() {
			ArrayList<Log> loglist = new ArrayList<Log>();
			readActvityLog ral = new readActvityLog("ActvityLog.txt", loglist);
			ral.readFile();
			
			
			DateComparator dc = new DateComparator();
			Collections.sort(loglist, dc);
			Collections.reverse(loglist);

			WriteActivityLog wal = new WriteActivityLog("ActvityLog.txt", loglist);
			wal.rewriteFile();
			
			
			
		}
		
	
	
	
}
