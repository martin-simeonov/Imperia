package org.elsys.models;

public class Realm {
	private int id;
	private String name;
	
	public Realm(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
