package org.elsys.client;

import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.elsys.listeners.OnServiceFinishListener;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class HttpClient {

	private JSONObject res;
	public static final String url = "http://imperia123.net23.net/api.php";

	public JSONObject getResponse() {
		return res;
	}

	public void sendRequest(String input, final OnServiceFinishListener finish) {
		AsyncHttpClient client = new AsyncHttpClient();

		StringEntity entity = null;
		try {
			entity = new StringEntity(input);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		client.post(null, url, entity, "application/json",
				new JsonHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject response) {
						System.out.println(response.toString());
						res = response;
						finish.onServiceFinish();
					}
				});
	}

}
