package org.elsys.services;

import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;

import com.google.gson.Gson;

public class LogoutService {

	private Gson gson;
	private HttpClient httpClient;

	public LogoutService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void logout(final OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 5);

		httpClient.sendRequest(gson.toJson(request), fl);
	}

}
