package org.elsys.models;

public class Resource {

	int wood;
	int iron;
	int stone;
	int gold;

	public Resource(int wood, int iron, int stone, int gold) {
		this.wood = wood;
		this.iron = iron;
		this.stone = stone;
		this.gold = gold;
	}

	public int getWood() {
		return wood;
	}

	public int getIron() {
		return iron;
	}

	public int getStone() {
		return stone;
	}

	public int getGold() {
		return gold;
	}

	public void setWood(int wood) {
		this.wood = wood;
	}

	public void setIron(int iron) {
		this.iron = iron;
	}

	public void setStone(int stone) {
		this.stone = stone;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

}
