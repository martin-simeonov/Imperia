package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;
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

	public void populate(final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 2);

		httpClient.sendRequest(gson.toJson(request), fl);
	}

	public void getResult() {
		// Get the response from server
		String json = httpClient.getResponse().toString();

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
		getResult();
		return resources;
	}

	public ArrayList<Building> getBuildings() {
		getResult();
		return buildings;
	}

}
