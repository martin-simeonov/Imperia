package org.elsys.ui;

import java.util.ArrayList;

import org.elsys.imperia.R;
import org.elsys.listeners.OnServiceFinishListener;
import org.elsys.models.CustomError;
import org.elsys.models.Realm;
import org.elsys.models.User;
import org.elsys.services.LoginService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	public final static String REALMS_MESSAGE = "org.elsys.imperia.REALMS";

	private LoginService loginService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	/**
	 * Button login onClick
	 * 
	 * @param view
	 */
	public void login(View view) {
		final Button login = (Button) findViewById(R.id.login);
		login.setClickable(false);

		EditText username = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);

		// Create User object from TextView contents
		User user = new User(username.getText().toString(), password.getText()
				.toString());

		loginService = new LoginService();
		loginService.login(user, new OnServiceFinishListener() {

			@Override
			public void onServiceFinish() { // Wait for the http client to
											// finish
				login.setClickable(true);
				onLoginServiceFinish();
			}

		});
	}

	private void onLoginServiceFinish() {
		ArrayList<Realm> realms = loginService.getRealms();

		if (realms != null) {
			// Send the realm list to new RealmActivity
			Intent intent = new Intent(this, RealmActivity.class);
			intent.putExtra(REALMS_MESSAGE, realms);
			startActivity(intent);
		}
		showError();
	}

	private void showError() {
		// If there is error show it
		CustomError error = loginService.getError();
		if (error != null) {
			Toast toast = Toast.makeText(getApplicationContext(),
					error.getName(), Toast.LENGTH_SHORT);
			toast.show();
		}
	}

}
