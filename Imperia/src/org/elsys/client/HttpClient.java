package org.elsys.client;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class HttpClient {
	private JSONObject res;

	public JSONObject getResponse() {
		return res;
	}

	public void sentRequest(String input) {
		AsyncHttpClient client = new AsyncHttpClient();
		StringEntity entity = null;
		try {
			entity = new StringEntity(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		client.post(null, "omdbapi.com", entity, "application/json",
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						res = response;
						System.out.println(new Gson().toJson(response));
					}
				});
	}

}
