package org.elsys.ui;

import java.util.ArrayList;

import org.elsys.adapters.BuildingAdapter;
import org.elsys.client.HttpClient;
import org.elsys.imperia.R;
import org.elsys.listeners.OnServiceFinishListener;
import org.elsys.models.Building;
import org.elsys.models.CustomError;
import org.elsys.models.Resource;
import org.elsys.services.LogoutService;
import org.elsys.services.UpgradeService;
import org.elsys.services.VillageService;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VillageActivity extends Activity {

	private VillageService villageService;
	private UpgradeService upgradeService;
	private LogoutService logoutService;

	private ArrayList<Building> buildingList;

	private ListView listView1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_village);

		// Add the ListView header
		listView1 = (ListView) findViewById(R.id.listView1);
		View header = (View) getLayoutInflater().inflate(
				R.layout.listview_buildingheader_row, null);
		listView1.addHeaderView(header);

		createVillage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.village, menu);
		return true;
	}

	private void createVillage() {
		villageService = new VillageService();
		villageService.populate(new OnServiceFinishListener() {

			@Override
			public void onServiceFinish() {
				setResourceBar();
				setBuildings();
			}

		});
	}

	private void setResourceBar() {
		// Get the resources from villageService
		Resource resource = villageService.getResources();
		TextView wood = (TextView) findViewById(R.id.wood);
		TextView iron = (TextView) findViewById(R.id.iron);
		TextView stone = (TextView) findViewById(R.id.stone);
		TextView gold = (TextView) findViewById(R.id.gold);

		// Set the values to the TextViews
		wood.setText("Wood:" + resource.getWood());
		iron.setText("Iron:" + resource.getIron());
		stone.setText("Stone:" + resource.getStone());
		gold.setText("Gold:" + resource.getGold());
	}

	private void setBuildings() {
		// Get the building list
		buildingList = villageService.getBuildings();
		Building[] buildings = buildingList.toArray(new Building[buildingList
				.size()]);

		// Create new BuildingAdapter for the ListView
		BuildingAdapter adapter = new BuildingAdapter(this,
				R.layout.listview_item_row, buildings);

		listView1.setAdapter(adapter);
	}

	/**
	 * ListItem buttonUpgrade onClick method
	 * 
	 * @param v
	 */
	public void upgrade(View v) {
		final Button upgrade = (Button) findViewById(R.id.upgrade);
		upgrade.setClickable(false);

		upgradeService = new UpgradeService();

		// Get the name of list item, which called the method
		RelativeLayout rl = (RelativeLayout) v.getParent();
		TextView txtView = (TextView) rl.findViewById(R.id.name);
		String name = txtView.getText().toString();

		Building searchBuilding = null;
		// Find the building object with the given name
		for (Building b : buildingList) {
			if (b.getName().equals(name)) {
				searchBuilding = b;
			}
		}

		// Send request for upgrade to upgradeService
		upgradeService.upgrade(searchBuilding, new OnServiceFinishListener() {

			@Override
			public void onServiceFinish() {
				upgrade.setClickable(true);
				// upgradeService.getResult();
				if (upgradeService.getError() == null) {
					createVillage();
				}
				showError();
			}

		});
	}

	private void showError() {
		// If there is error show it
		CustomError error = upgradeService.getError();
		if (error != null) {
			Toast toast = Toast.makeText(getApplicationContext(),
					error.getName(), Toast.LENGTH_SHORT);
			toast.show();
		}
	}

	public void logout(View v) {
		logoutService = new LogoutService();
		logoutService.logout(new OnServiceFinishListener() {

			@Override
			public void onServiceFinish() {
				// Kill the application proccess
				HttpClient.sessionId = "null";
				loginScreen();
			}

		});
	}

	private void loginScreen() {
		Intent intent = new Intent(this, LoginActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
}
