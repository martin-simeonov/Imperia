package org.elsys.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.elsys.models.Realm;
import org.elsys.models.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LoginService extends Service {

	public void login(User user) {
		Gson g = new Gson();
		Map<String, Object> request = new LinkedHashMap<String, Object>();
		request.put("id", 1);
		request.put("username", user.getUsername());
		request.put("password", user.getPassword());

		sendRequest(g.toJson(request));
	}

	public void sendRequest(String json) {
		// Httpclient .send(json);
	}

	public String getResponse() {
		// httpclient get response(json
		String json = "{id:1, realm:[{id:1,name:\"marto\"},{id:6,name:\"ivan\"},{id:3,name:\"daniel\"}], error:{}}";

		return json;
	}

	public ArrayList<Realm> getResult() {
		String json = getResponse();

		Gson g = new Gson();
		Map<String, Object> hm = g.fromJson(json,
				new TypeToken<LinkedHashMap<String, Object>>() {
				}.getType());
		ArrayList<Realm> realms = g.fromJson(hm.get("realm").toString(),
				new TypeToken<ArrayList<Realm>>() {
				}.getType());
		return realms;
	}

}
