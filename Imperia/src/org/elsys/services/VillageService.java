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

	private Resource resources;
	private ArrayList<Building> buildings;

	public VillageService() {
		gson = new Gson();
	}

	public void populate(final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 2);
		request.put("sessionid", HttpClient.sessionId);

		HttpClient.sendRequest(gson.toJson(request), fl);
	}

	public void getResult() {
		// Get the response from server
		String json = HttpClient.getResponse().toString();

		Map<String, Object> hm = gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		// Create the resource object from JSON
		resources = gson.fromJson(gson.toJson(hm.get("resources")),
				new TypeToken<Resource>() {
				}.getType());

		// Create the buildings list from JSON
		buildings = gson.fromJson(gson.toJson(hm.get("buildings")),
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
