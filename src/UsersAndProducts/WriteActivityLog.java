package UsersAndProducts;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteActivityLog {
	
	private String fileName;
	private ArrayList<Log> loglist;

	public WriteActivityLog(String fileName, ArrayList<Log> loglist) {
		this.fileName = fileName;
		this.loglist = loglist;
		// TODO Auto-generated constructor stub
	}

	public void rewriteFile() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("ActivityLog.txt"));
			for(Log logInfo : loglist) {
				bw.write(logInfo.toString() + "\n");
			}
		}catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		finally {
			try {
				if(bw != null) {
					bw.close();
				}
			} catch(IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}
