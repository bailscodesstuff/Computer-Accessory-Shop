package UsersAndProducts;

import java.util.Comparator;

public class StockComparator implements Comparator<Products> {

	@Override
	public int compare(Products o1, Products o2) {
		// TODO Auto-generated method stub
		if(o1.getQuantityinStock() < o2.getQuantityinStock()) return -1;
		if(o1.getQuantityinStock() > o2.getQuantityinStock()) return 1;
		else return 0;
	}

}
