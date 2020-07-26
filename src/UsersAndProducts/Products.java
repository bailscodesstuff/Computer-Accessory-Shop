package UsersAndProducts;


import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;


public abstract class Products {
	public String barcode;
	public String deviceName;
	public String deviceType;
	public String brand;
	public String colour;
	public String connectivity;
	public int quantityinStock;
	public Double originalCost;
	public Double retailCost;
	public int quantityOfItem;
	public int newAmountAdded;
	



	public int getQuantityOfItem() {
		return quantityOfItem;
	}



	public void setQuantityOfItem(int quantityOfItem) {	
		this.quantityOfItem=quantityOfItem;
	}



	public String getBarcode() {
		return barcode;
	}






	public String getDeviceName() {
		return deviceName;
	}





	public String getDeviceType() {
		return deviceType;
	}



	public String getBrand() {
		return brand;
	}



	public String getColour() {
		return colour;
	}





	public String getConnectivity() {
		return connectivity;
	}





	public int getQuantityinStock() {
		return quantityinStock;
	}

	public void setQuantityinStock(int quantityinStock) {
		this.quantityinStock = quantityinStock;
	}


	public Double getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(Double originalCost) {
		this.originalCost = originalCost;
	}

	public Double getRetailCost() {
		return retailCost;
	}

	public void setRetailCost(Double retailCost) {
		this.retailCost = retailCost;
	}


	
	
	



	

}
	class Mouse extends Products{
	public String noOfButtons; //the last element in stockdata line
	
	
	
		public Mouse(String barcode, String deviceName, String deviceType, String brand, String colour, String connectivity,
			int quantityinStock, Double originalCost, Double retailCost, String noOfButtons) {
			super();
		this.barcode = barcode;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantityinStock = quantityinStock;
		this.originalCost = originalCost;
		this.retailCost = retailCost;	
		this.noOfButtons = noOfButtons;
		// TODO Auto-generated constructor stub
	}
		
		
	



		public String getNoOfButtons() {
			return noOfButtons;
		}

		public void setNoOfButtons(String noOfButtons) {
			this.noOfButtons = noOfButtons;
		}

		public String toString() {
			return (getBarcode() + "," + getDeviceName() + "," + getDeviceType() + "," + getBrand() + "," + 
					getColour() + "," + getConnectivity() + "," + getQuantityinStock() + "," + getOriginalCost()
					 + "," + getRetailCost() + "," + getNoOfButtons());
		}

}

class Keyboard extends Products{

	public String keyboardLayout;
	
	
		public Keyboard(String barcode, String deviceName, String deviceType, String brand, String colour,
			String connectivity, int quantityinStock, Double originalCost, Double retailCost, String keyboardLayout) {
			super();
		this.barcode = barcode;
		this.deviceName = deviceName;
		this.deviceType = deviceType;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantityinStock = quantityinStock;
		this.originalCost = originalCost;
		this.retailCost = retailCost;	
		this.keyboardLayout = keyboardLayout;
	}
		
		
		



		public String getKeyboardLayout() {
			return keyboardLayout;
		}

		public void setKeyboardLayout(String keyboardLayout) {
			this.keyboardLayout = keyboardLayout;
		}

		public String toString() {
			return (getBarcode() + "," + getDeviceName() + "," + getDeviceType() + "," + getBrand() + "," + 
					getColour() + "," + getConnectivity() + "," + getQuantityinStock() + "," + getOriginalCost()
					 + "," + getRetailCost() + "," + getKeyboardLayout());
		}

}


