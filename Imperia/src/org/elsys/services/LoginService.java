package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.client.HttpClient;
import org.elsys.listeners.OnServiceFinishListener;
import org.elsys.models.CustomError;
import org.elsys.models.Realm;
import org.elsys.models.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginService {

	private Gson gson;
	private CustomError error;

	public LoginService() {
		gson = new Gson();
	}

	public void login(User user, OnServiceFinishListener fl) {
		// Form JSON String for the request
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 1);
		request.put("username", user.getUsername());
		request.put("password", user.getPassword());
		request.put("sessionid", "null");

		HttpClient.sendRequest(gson.toJson(request), fl);
	}

	public ArrayList<Realm> getRealms() {
		String json = HttpClient.getResponse().toString();

		Map<String, Object> hm = gson.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());

		String sessionId = (String) hm.get("sessionid");
		if (sessionId != null) {
			HttpClient.sessionId = sessionId;
		}

		if (hm.get("realms") != null) {
			// Create the list of realms from the JSON response
			ArrayList<Realm> realms = gson.fromJson(
					gson.toJson(hm.get("realms")),
					new TypeToken<ArrayList<Realm>>() {
					}.getType());

			return realms;
		}

		// Process errors
		error = gson.fromJson(gson.toJson(hm.get("error")),
				new TypeToken<CustomError>() {
				}.getType());

		return null;
	}

	public CustomError getError() {
		return error;
	}

}
