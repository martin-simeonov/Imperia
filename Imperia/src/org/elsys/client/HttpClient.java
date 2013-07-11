package org.elsys.client;

import org.elsys.listeners.OnServiceFinishListener;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpClient {

	private static JSONObject res;
	public static final String url = "http://192.168.0.100/elsysgame/api.php?request";
	public static String sessionId;

	public static AsyncHttpClient client = new AsyncHttpClient();

	public static JSONObject getResponse() {
		return res;
	}

	public static void sendRequest(String json,
			final OnServiceFinishListener finish) {

		RequestParams params = new RequestParams();
		params.put("request", json);
		System.out.println(params);

		client.post(url, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				System.out.println(response.toString());
				res = response;
				finish.onServiceFinish();
			}

			public void onFailure(Throwable e, JSONObject errorResponse) {
				e.printStackTrace();
			}
		});
	}

}
