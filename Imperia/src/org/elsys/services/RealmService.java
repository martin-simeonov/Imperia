package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;

import com.google.gson.Gson;

public class RealmService {

	private Gson gson;
	private HttpClient httpClient;

	public RealmService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void selectRealm(int realmId, final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Integer> request = new LinkedHashMap<String, Integer>();
		request.put("id", 5);
		request.put("realm_id", realmId);

		httpClient.sendRequest(gson.toJson(request), fl);
	}
}
