package org.elsys.imperia;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class RealmActivity extends ListActivity {

	public static final String VILLAGE_MESSAGE = "org.elsys.imperia.VILLAGE";

	ArrayList<String> realms;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realm);

		Intent intent = getIntent();
		realms = intent.getStringArrayListExtra(LoginActivity.REALMS_MESSAGE);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, realms);
		setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.realm, menu);
		return true;
	}

	public void createVillage() {
		Intent intent = new Intent(this, VillageActivity.class);
		intent.putExtra(VILLAGE_MESSAGE, true);
		startActivity(intent);
	}

}
