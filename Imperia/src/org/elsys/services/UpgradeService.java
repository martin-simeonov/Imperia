package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.models.Building;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UpgradeService extends Service {

	public void upgrade(Building building) {
		Gson g = new Gson();
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 3);
		request.put("building_id", building.getId());
		request.put("level", building.getCurrentLevel());

		sendRequest(g.toJson(request));
	}

	public void getResult() {
		String json = getResponse();

		Gson g = new Gson();
		Map<String, Object> result = g.fromJson(json,
				new TypeToken<Map<String, Object>>() {
				}.getType());

		if (result.get("error") == null) {
			// proccess error
		}
	}

}
