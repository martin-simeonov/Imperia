package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.models.Realm;
import org.elsys.models.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginService {

	private Gson gson;
	private HttpClient httpClient;

	public LoginService() {
		gson = new Gson();
		httpClient = new HttpClient();
	}

	public void login(User user) {
		// Form JSON String for the request
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 1);
		request.put("username", user.getUsername());
		request.put("password", user.getPassword());

		httpClient.sentRequest(gson.toJson(request));
	}

	public ArrayList<Realm> getRealms() {
		String json = gson.toJson(httpClient.getResponse());

		Map<String, Object> hm = gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		// Create the list of realms from the JSON response
		ArrayList<Realm> realms = gson.fromJson(hm.get("realm").toString(),
				new TypeToken<ArrayList<Realm>>() {
				}.getType());

		return realms;
	}

}
