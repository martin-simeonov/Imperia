package org.elsys.imperia;

import java.util.ArrayList;

import org.elsys.models.Realm;
import org.elsys.services.RealmService;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RealmActivity extends ListActivity {

	RealmService realmService;

	ArrayList<Realm> realms;
	ArrayList<String> realmNames;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_realm);

		createRealmList();

		ListView listView = (ListView) findViewById(android.R.id.list);
		// Add onItemClickListener for elements of listView
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0,
					android.view.View arg1, int arg2, long arg3) {
				String selectedRealm = (String) (arg0.getItemAtPosition(arg2));
				selectRealm(selectedRealm);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.realm, menu);
		return true;
	}

	@SuppressWarnings("unchecked")
	private void createRealmList() {
		Intent intent = getIntent();

		// Get the realm list sent by LoginActivity
		realms = (ArrayList<Realm>) intent
				.getSerializableExtra(LoginActivity.REALMS_MESSAGE);

		// Form List of only realm names
		ArrayList<String> realmNames = new ArrayList<String>();
		for (Realm r : realms) {
			realmNames.add(r.getName());
		}

		// Add realm names to listView
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, realmNames);
		setListAdapter(adapter);
	}

	/**
	 * Called by ListView onItemClickListener
	 * @param selectedRealm
	 */
	private void selectRealm(String selectedRealm) {
		realmService = new RealmService();
		// Find which realm was selected
		for (Realm r : realms) {
			if (r.getName().equals(selectedRealm)) {
				// Pass the id of the selected realm to realmService
				realmService.selectRealm(r.getId());
			}
		}

		// Start new VillageActivity
		Intent intent = new Intent(this, VillageActivity.class);
		startActivity(intent);
	}
}
