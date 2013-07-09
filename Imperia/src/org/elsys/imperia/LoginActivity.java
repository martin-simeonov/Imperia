package org.elsys.imperia;

import java.util.ArrayList;

import org.elsys.models.Realm;
import org.elsys.models.User;
import org.elsys.services.LoginService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {

	public final static String REALMS_MESSAGE = "org.elsys.imperia.REALMS";

	private LoginService logService;

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
		EditText username = (EditText) findViewById(R.id.username);
		EditText password = (EditText) findViewById(R.id.password);

		// Create User object from TextView contents
		User user = new User(username.getText().toString(), password.getText()
				.toString());

		logService = new LoginService();
		logService.login(user);

		ArrayList<Realm> realms = logService.getRealms();

		// Send the realm list to new RealmActivity
		Intent intent = new Intent(this, RealmActivity.class);
		intent.putExtra(REALMS_MESSAGE, realms);
		startActivity(intent);
	}

}
