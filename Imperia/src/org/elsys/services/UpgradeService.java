package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;
import org.elsys.models.Building;
import org.elsys.models.CustomError;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UpgradeService {

	private Gson gson;
	private HttpClient httpClient;
	private CustomError error;

	public UpgradeService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void upgrade(Building building, final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 3);
		request.put("building_id", building.getId());
		request.put("level", building.getCurrentLevel());

		httpClient.sendRequest(gson.toJson(request), fl);
	}

	public void getResult() {
		String json = httpClient.getResponse().toString();

		Map<String, Object> hm = gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		error = gson.fromJson(hm.get("error").toString(),
				new TypeToken<CustomError>() {
				}.getType());
	}

	public CustomError getError() {
		return error;
	}

}
