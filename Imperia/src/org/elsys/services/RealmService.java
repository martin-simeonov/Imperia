package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;

import com.google.gson.Gson;

public class RealmService {

	private Gson gson;

	public RealmService() {
		gson = new Gson();
	}

	public void selectRealm(int realmId, final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 5);
		request.put("realmid", realmId);
		request.put("sessionid", HttpClient.sessionId);

		HttpClient.sendRequest(gson.toJson(request), fl);
	}
}
