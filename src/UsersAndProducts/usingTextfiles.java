package UsersAndProducts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.Test;



//this class contains everything to do w interacting with textfiles.
	//e.g reading textfiles, writing to textfiles (could be done in gui code) , activity logs

public class usingTextfiles {
	

	protected String fileName;
	

	 
	public static ArrayList<User> userArray;
	public static ArrayList<Keyboard> keyboardArray;
	public static ArrayList<Mouse> mouseArray;
	public static ArrayList<Products> productArray;
	public static ArrayList<Log> loglist;
	


	

	public static ArrayList<User> readingUserFile() throws IOException {
		
		FileReader file = new FileReader("UserAccounts.txt"); 
		BufferedReader reader = new BufferedReader(file);
		
		ArrayList<User> userArray = new ArrayList<User>();
		String line = null;
		String[] lineInfo;
		int i = 0;
		
		while(true) {
			//this reads each line
			line = reader.readLine();
			if(line!= null) {
				//lineInfo splits the line into individual parts, each entry in between the comma is an element 
					//think of lineInfo as an informal array (you access the info using the same method as accessing array elements.)
				lineInfo = line.split(",");
				
				if(lineInfo[6].contains("admin")) {
					userArray.add(new Admin(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], lineInfo[6]));
				}
				else {
					userArray.add(new Customer(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], lineInfo[6]));
				}
				i+=1;
			}else {
				break;
			}
		}
		return userArray;
	}


		
	public static ArrayList<Keyboard> getKeyboardFile() throws IOException{
		FileReader file = new FileReader("Stock.txt"); 
		BufferedReader reader = new BufferedReader(file);
		

		ArrayList<Keyboard> keyboardArray = new ArrayList<Keyboard>();
		
		String line = null;
		String[] lineInfo;
		int i = 0;
		//create array list. 
		
		while(true) {
			line = reader.readLine();
			if(line!= null) {
				lineInfo = line.split(",");
				
				if (lineInfo[1].contains("keyboard")) {
					keyboardArray.add(new Keyboard(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], Integer.parseInt(lineInfo[6]), Double.parseDouble(lineInfo[7]), Double.parseDouble(lineInfo[8]), lineInfo[9]));				
					}
				i++;
			}else {
				break;	
			}

	}
		reader.close();
		return keyboardArray;
		
	}
	
	public static ArrayList<Mouse> getMouseFile() throws IOException{
		FileReader file = new FileReader("Stock.txt"); 
		BufferedReader reader = new BufferedReader(file);
		ArrayList<Mouse> mouseArray = new ArrayList<Mouse>();
		
		String line = null;
		String[] lineInfo; 
		int i = 0;
		while(true) {
			line = reader.readLine();
			if(line!= null) {
				lineInfo = line.split(",");
				
				if (lineInfo[1].contains("mouse")) {
					mouseArray.add(new Mouse(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], Integer.parseInt(lineInfo[6]), Double.parseDouble(lineInfo[7]), Double.parseDouble(lineInfo[8]), lineInfo[9]));				
					}
				i++;
			}else {
				break;	
			}

	}
		reader.close();

		return mouseArray;
		
	}
	
	
	
public static void updateKeyboard(ArrayList<Keyboard> keyboardArray) throws IOException{
	
	Set<Keyboard> KeyboardSet = new LinkedHashSet<>();
	KeyboardSet.addAll(keyboardArray);
	keyboardArray.clear();
	keyboardArray.addAll(KeyboardSet);
	
	BufferedWriter bw = new BufferedWriter(new FileWriter("Stock.txt", true));
	int lastElement = keyboardArray.size()-1;
		
		bw.append(keyboardArray.get(lastElement).getBarcode()+","+keyboardArray.get(lastElement).getDeviceName()+","+
				keyboardArray.get(lastElement).getDeviceType()+","+keyboardArray.get(lastElement).getBrand()+","+
				keyboardArray.get(lastElement).getColour()+","+keyboardArray.get(lastElement).getConnectivity()+","+
				keyboardArray.get(lastElement).getQuantityinStock()+","+keyboardArray.get(lastElement).getOriginalCost()+","+
				keyboardArray.get(lastElement).getRetailCost()+","+keyboardArray.get(lastElement).getKeyboardLayout());
		
		bw.newLine();
		bw.close();
		
		
	}

public static void updateMouse(ArrayList<Mouse> mouseArray) throws IOException{
	
	int lastElement = mouseArray.size()-1;

	BufferedWriter bw = null;
	try {
		bw = new BufferedWriter(new FileWriter("Stock.txt", true));
		
		bw.append(mouseArray.get(lastElement).getBarcode()+","+mouseArray.get(lastElement).getDeviceName()+","+
				mouseArray.get(lastElement).getDeviceType()+","+mouseArray.get(lastElement).getBrand()+","+
				mouseArray.get(lastElement).getColour()+","+mouseArray.get(lastElement).getConnectivity()+","+
				mouseArray.get(lastElement).getQuantityinStock()+","+mouseArray.get(lastElement).getOriginalCost()+","+
				mouseArray.get(lastElement).getRetailCost()+","+mouseArray.get(lastElement).getNoOfButtons());
		bw.newLine();
		
		} catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	finally {
		bw.close();
		}
		
	}

public static ArrayList<Products> getProductFile() throws IOException {
	FileReader file = new FileReader("Stock.txt"); 
	BufferedReader reader = new BufferedReader(file);
	

	ArrayList<Products> productArray = new ArrayList<Products>();
	
	String line = null;
	String[] lineInfo;
	int i = 0;
	//create array list. 
	
	while(true) {
		line = reader.readLine();
		if(line!= null) {
			lineInfo = line.split(",");
			
				
			if (lineInfo[1].contains("keyboard")) {
				productArray.add(new Keyboard(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], Integer.parseInt(lineInfo[6]), Double.parseDouble(lineInfo[7]), Double.parseDouble(lineInfo[8]), lineInfo[9]));				
				}
			
			if (lineInfo[1].contains("mouse")) {
				productArray.add(new Mouse(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], Integer.parseInt(lineInfo[6]), Double.parseDouble(lineInfo[7]), Double.parseDouble(lineInfo[8]), lineInfo[9]));	
			}
			i++;
			}else {
				break;
			}
			
		}
		
	
	reader.close();
	
	
	return productArray;
}


@Test
	public static ArrayList<Products> getAvailableProducts() throws IOException {
		
		//get the product arrayList. if the 6th element = 0 then delete that entry,
		
		ArrayList<Products> productArray = usingTextfiles.getProductFile();
		ArrayList<Products> availableProducts = new ArrayList<Products>();
		//usingTextfiles.get
		for(int i=0; i<productArray.size(); i++) {
			
			if(productArray.get(i).getQuantityinStock() != 0) {
			
				availableProducts.add(productArray.get(i));
				
			}
		}
		return availableProducts;

	}



	public static ArrayList<String> getFilterBrands() throws IOException {
		HashSet<String> brandNames = new HashSet<String>();
		ArrayList<Products> availProducts = getAvailableProducts();
		

		for(int i=0; i<availProducts.size(); i++) {
			 brandNames.add(availProducts.get(i).getBrand());
		
		}
		
		ArrayList<String> listBrandNames = new ArrayList<String>(brandNames);
		
		
		return listBrandNames;
		
		}

	public static void editItemInfo(String itemBarcode, int basketQuantity ) throws IOException {
		boolean newEdit=true;
		usingTextfiles.getProductFile();
		for(int i=0; i<productArray.size(); i++) {
		if(productArray.get(i).getBarcode().contains(itemBarcode)) {
			int newQuant = productArray.get(i).getQuantityOfItem() - basketQuantity;
			productArray.get(i).setQuantityinStock(newQuant);

	
		}
		
	}
		
	}

	
	public static void updateStockAfterPurchase(List<Basket> customerBasket, ArrayList<Products> productArray) throws IOException {
		
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
		
		// this updates the arraylist
	public static void updateStockFile(ArrayList<Products> productArray) throws IOException {
		
		PrintWriter writer = new PrintWriter("Stock.txt");
		writer.print("");
		writer.close();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("Stock.txt", true));
		
		for(int i=0; i<productArray.size(); i++) {
			if(productArray.get(i).getDeviceName().contains("keyboard")) {
			bw.write(productArray.get(i).getBarcode() +","+ productArray.get(i).getDeviceName()+","+
		productArray.get(i).getDeviceType() +","+ productArray.get(i).getBrand()+","+ productArray.get(i).getColour()+","+
		productArray.get(i).getConnectivity() +","+	productArray.get(i).getQuantityinStock() +","+productArray.get(i).getOriginalCost()
		+","+ productArray.get(i).getRetailCost() +","+((Keyboard) productArray.get(i)).getKeyboardLayout());
			
			bw.newLine();
			}else { 
				bw.write(productArray.get(i).getBarcode() +","+ productArray.get(i).getDeviceName()+","+
						productArray.get(i).getDeviceType() +","+ productArray.get(i).getBrand()+","+ productArray.get(i).getColour()+","+
						productArray.get(i).getConnectivity() +","+	productArray.get(i).getQuantityinStock() +","+productArray.get(i).getOriginalCost()
						+","+ productArray.get(i).getRetailCost() +","+((Mouse) productArray.get(i)).getNoOfButtons());
			bw.newLine();
			}
		
		
		}
			bw.close();	
			}
		
	

	public static ArrayList<Log> getActivityLogFile() throws IOException, ParseException{

		FileReader  file = new FileReader("ActivityLog.txt");
		BufferedReader reader = new BufferedReader(file);
		
		ArrayList<Log> loglist = new ArrayList<Log>();
		
		String line = null;
		String[] lineInfo;
		int i = 0;
		//create array list. 
		try {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date addDate = null;
		while(true) {
			line = reader.readLine();
			if(line!= null) {
				lineInfo = line.split(",");
	
				addDate = formatter.parse(lineInfo[7].trim());
				
				loglist.add(new Log(lineInfo[0], lineInfo[1], lineInfo[2], lineInfo[3], lineInfo[4], lineInfo[5], lineInfo[6], addDate));
			
			}else {
				break;
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
		reader.close();
		}
		
		return loglist;
		}
			
	
	
	public static void rewriteLogFile(ArrayList<Log> loglist) throws IOException {
		//ArrayList<Log> loglist = new ArrayList<Log>();
	
		DateComparator dc = new DateComparator();
		Collections.sort(loglist, dc);
		Collections.reverse(loglist);
		
		PrintWriter writer = new PrintWriter("ActvitiyLog.txt");
		writer.print("");
		writer.close();
		
		
		BufferedWriter bw = new BufferedWriter(new FileWriter("ActivityLog.txt", true));
		
		for(int i=0; i<loglist.size(); i++) {
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			
			bw.write(loglist.get(i).getUserID() +","+ loglist.get(i).getPostcode() +","+ loglist.get(i).getBarcode() +","+ 
					loglist.get(i).getRetailCost()+","+loglist.get(i).getQuantityOfItem()+","+loglist.get(i).getChoice()+","+
					loglist.get(i).getPaymentDetails() +","+ loglist.get(i).getDateToday());
		bw.newLine();
		}
		bw.close();
		
		
	}
	

	
	
}
	
	
	





	
	
	
















		

	
	

	
	
	
	
	
	

	



	




