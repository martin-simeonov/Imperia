package org.elsys.models;

public class Building {

	private int id;
	private String name;
	private int currentLevel;
	private int maxLevel;
	private boolean inProgress;
	private String endTime;
	private Resource nextLevelResources;
	private boolean canUpgrade;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public boolean isInProgress() {
		return inProgress;
	}

	public String getEndTime() {
		return endTime;
	}

	public Resource getNextLevelResources() {
		return nextLevelResources;
	}

	public boolean isCanUpgrade() {
		return canUpgrade;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public void setInProgress(boolean inProgress) {
		this.inProgress = inProgress;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public void setNextLevelResources(Resource nextLevelResources) {
		this.nextLevelResources = nextLevelResources;
	}

	public void setCanUpgrade(boolean canUpgrade) {
		this.canUpgrade = canUpgrade;
	}
}
