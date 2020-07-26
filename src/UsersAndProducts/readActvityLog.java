package UsersAndProducts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class readActvityLog {
	private String fileName;
	private ArrayList<Log> loglist;

	public readActvityLog(String fileName, ArrayList<Log> loglist) {
		this.fileName = fileName;
		this.loglist = loglist;

	}
	public void readFile() {
		Scanner fileScanner = null;
		try {
			File inputFile = new File("ActivityLog.txt");
			fileScanner = new Scanner(inputFile);
			Log logInfo = null;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date addDate = null;
		
			while(fileScanner.hasNextLine()) {
				String[] logDetails = fileScanner.nextLine().split(",");
				addDate = formatter.parse(logDetails[7].trim());
				logInfo = new Log(logDetails[0],logDetails[1],logDetails[2],logDetails[3],logDetails[4], 
						logDetails[5],logDetails[6],addDate);
				loglist.add(logInfo);
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		finally {
			fileScanner.close();
		}
	}
			
			
			
		

}
