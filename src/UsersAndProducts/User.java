package UsersAndProducts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

public abstract class User {
	
	String userID;
	String username,surname,postcode, town; 
	String userType;
	String doorNumber;

	public String getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public String getSurname() {
		return surname;
	}

	public String getPostcode() {
		return postcode;
	}


	public String getTown() {
		return town;
	}

	public String getUserType() {
		return userType;
	}



	public String getDoorNumber() {
		return doorNumber;
	}

	

	public String toString() {
		
		return (getUserID() + "," + getUsername() + "," + getSurname() + "," + getDoorNumber() + "," + getPostcode() + "," + getTown()
				+ "," + getUserType());
	}


}
		

		
				

		
			
			










				
				
				
				
	
