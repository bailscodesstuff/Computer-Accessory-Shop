package UsersAndProducts;

import java.util.Comparator;

public class DateComparator implements Comparator<Log>{

	@Override
	public int compare(Log log1, Log log2) {
		// TODO Auto-generated method stub
		return  log1.getDateToday().compareTo(log2.getDateToday());
	}
	
	

	

}
