package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.models.Building;
import org.elsys.models.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VillageService {

	private Gson gson;
	private HttpClient httpClient;

	private Resource resources;
	private ArrayList<Building> buildings;

	public VillageService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void populate() {
		// Form JSON String for the request
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 2);

		httpClient.sentRequest(gson.toJson(request));
	}

	public void sendRequest(String json) {
		// http send request
	}

	public void getResult() {
		// Get the response from server
		String json = gson.toJson(httpClient.getResponse());

		Map<String, Object> hm = gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		// Create the resource object from JSON
		resources = gson.fromJson(hm.get("resources").toString(),
				new TypeToken<Resource>() {
				}.getType());

		// Create the buildings list from JSON
		buildings = gson.fromJson(hm.get("buildings").toString(),
				new TypeToken<ArrayList<Building>>() {
				}.getType());
	}

	public Resource getResources() {
		// getResult();
		// resources = new Resource(50000, 2500, 2000, 1500);
		return resources;
	}

	public ArrayList<Building> getBuildings() {
		// getResult();
		// return buildings;

		/*
		 * buildings = new ArrayList<Building>(); Building b = new Building();
		 * b.setId(1); b.setName("Fortress"); b.setCurrentLevel(1);
		 * b.setMaxLevel(10); b.setInProgress(false); b.setEndTime("false");
		 * b.setNextLevelResources(new Resource(500, 250, 200, 150));
		 * b.setCanUpgrade(true); buildings.add(b);
		 * 
		 * Building b1 = new Building(); b1.setId(2); b1.setName("Barracks");
		 * b1.setCurrentLevel(3); b1.setMaxLevel(10); b1.setInProgress(true);
		 * b1.setEndTime("21-12-3 22:45:01"); b1.setNextLevelResources(new
		 * Resource(1000, 1000, 2000, 1500)); b1.setCanUpgrade(false);
		 * buildings.add(b1);
		 * 
		 * Building b2 = new Building(); b2.setId(3); b2.setName("City Hall");
		 * b2.setCurrentLevel(10); b2.setMaxLevel(10); b2.setInProgress(true);
		 * b2.setEndTime("21-12-3 22:45:01"); b2.setNextLevelResources(new
		 * Resource(10000, 10000, 20000, 15000)); b2.setCanUpgrade(false);
		 * buildings.add(b2);
		 * 
		 * Building b3 = new Building(); b3.setId(4); b3.setName("Temple");
		 * b3.setCurrentLevel(4); b3.setMaxLevel(10); b3.setInProgress(false);
		 * b3.setEndTime("21-12-3 22:45:01"); b3.setNextLevelResources(new
		 * Resource(1000, 1000, 2000, 15000)); b3.setCanUpgrade(true);
		 * buildings.add(b3);
		 */

		return buildings;
	}

}
