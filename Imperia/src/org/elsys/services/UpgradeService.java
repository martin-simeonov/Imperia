package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.models.Building;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UpgradeService {

	private Gson gson;
	private HttpClient httpClient;

	public UpgradeService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void upgrade(Building building) {
		// Form JSON String for the request
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 3);
		request.put("building_id", building.getId());
		request.put("level", building.getCurrentLevel());

		httpClient.sentRequest(gson.toJson(request));
	}

	public void getResult() {
		String json = gson.toJson(httpClient.getResponse());

		Map<String, Object> result = gson.fromJson(json,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (result.get("error") == null) {
			// proccess error
		}
	}

}
