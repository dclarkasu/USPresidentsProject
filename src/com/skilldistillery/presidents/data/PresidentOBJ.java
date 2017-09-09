package com.skilldistillery.presidents.data;

public class PresidentOBJ {
private String party, name, image, factoid, termDate; 
private int termNum;




//CONSTRUCTOR
public PresidentOBJ(String party, String name, String image, String factoid, int termNum, String termDate) {
	super();
	this.party = party;
	this.name = name;
	this.image = image;
	this.factoid = factoid;
	this.termNum = termNum;
	this.termDate = termDate;
}

//GETTERS AND SETTERS
public String getParty() {
	return party;
}

public void setParty(String party) {
	this.party = party;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}

public String getFactoid() {
	return factoid;
}

public void setFactoid(String factoid) {
	this.factoid = factoid;
}

public int getTermNum() {
	return termNum;
}

public void setTermNum(int termNum) {
	this.termNum = termNum;
}

public String getTermDate() {
	return termDate;
}

public void setTermDate(String termDate) {
	this.termDate = termDate;
} 


	
	
}
