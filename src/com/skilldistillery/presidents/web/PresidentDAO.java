package com.skilldistillery.presidents.web;

import java.util.List;

import com.skilldistillery.presidents.data.PresidentOBJ;

public interface PresidentDAO {
	
	public PresidentOBJ getPresident(int termNum);
	public void getPresidentDisplay(PresidentOBJ p);
	public List<PresidentOBJ> getFullList();
	public List<PresidentOBJ> getFilterList(String party);

/*		public Stock getStock(String symbol);
		public double getPrice(String symbol);
		public String getName(String symbol);
		public String getSymbol(String name);
		public List<Stock> getAllStocks();
*/
	
	}

	
