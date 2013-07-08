package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.models.Building;
import org.elsys.models.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class VillageService extends Service {

	private Resource resources;
	private ArrayList<Building> buildings;

	public void resources() {
		Gson g = new Gson();
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 2);

		sendRequest(g.toJson(request));
	}

	public void sendRequest(String json) {
		// http send request
	}

	public String getResponse() {
		// http response
		return null;
	}

	public void getResult() {
		String json = getResponse();

		Gson g = new Gson();
		Map<String, Object> hm = g.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		resources = g.fromJson(hm.get("resources").toString(),
				new TypeToken<Resource>() {
				}.getType());
		buildings = g.fromJson(hm.get("buildings").toString(),
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
