package com.skilldistillery.presidents.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import com.skilldistillery.presidents.data.PresidentOBJ;


public class PresidentFileDAO implements PresidentDAO {
	private static final String filename = "WEB-INF/presidentsdata.txt";
	private ServletContext servletContext;
	private List<PresidentOBJ> presidents;
	private List<PresidentOBJ> current;
	private Map<Integer, PresidentOBJ> mapPresident = new HashMap<>();
	
	public PresidentFileDAO(ServletContext context) {
		servletContext = context;
		presidents = new ArrayList<>();
		loadPresidentsFromFile();
		current = presidents;
	}

	private void loadPresidentsFromFile() {
		System.out.println("Entering loadPresidentsFile()");
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		InputStream is = servletContext.getResourceAsStream(filename);
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line;
			while ((line = buf.readLine()) != null) {
				String[] data = line.split(",");
				int termNum = Integer.parseInt(data[0]);
				String name = data[1];
				String termDate = data[2];
				String image = data[3];
				String party = data[4];
				String factoid = data[5];
				presidents.add(new PresidentOBJ(party, name, image, factoid, termNum, termDate));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		System.out.println("Exiting loadPresidentsFile()");
		
	}
	
	
	@Override
	public PresidentOBJ getPresident(int termNum) {
		return mapPresident.get(termNum);
	}

	@Override
	public void getPresidentDisplay(PresidentOBJ p) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PresidentOBJ> getFullList() {
		// TODO Auto-generated method stub
		return presidents;
	}

	   @Override
	    public List<PresidentOBJ> getFilterList(String party) {
	        List<PresidentOBJ> answer = new ArrayList<>();
	        party = party.toLowerCase();
	        for (int i = 0; i < presidents.size(); i++) {
	            String theParty = presidents.get(i).getParty().toLowerCase();
	            if (theParty.contains(party)) {
	                answer.add(presidents.get(i));
	            }
	        }
	        return answer;
	    }

	public List<PresidentOBJ> getCurrent() {
		return current;
	}

	public void setCurrent(List<PresidentOBJ> current) {
		this.current = current;
	}

}
