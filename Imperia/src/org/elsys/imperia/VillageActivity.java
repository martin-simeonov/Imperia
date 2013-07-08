package org.elsys.imperia;

import org.elsys.models.Resource;
import org.elsys.services.VillageService;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class VillageActivity extends Activity {

	private VillageService villageService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_village);
		createVillage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.village, menu);
		return true;
	}

	public void createVillage() {
		villageService = new VillageService();
		villageService.resources();

	}

	public void setResourceBar() {
		Resource resource = villageService.getResources();
		TextView wood = (TextView) findViewById(R.id.wood);
		TextView iron = (TextView) findViewById(R.id.iron);
		TextView stone = (TextView) findViewById(R.id.stone);
		TextView gold = (TextView) findViewById(R.id.gold);

		wood.setText(wood.getText().toString() + " " + resource.getWood());
		iron.setText(iron.getText().toString() + " " + resource.getIron());
		stone.setText(stone.getText().toString() + " " + resource.getStone());
		gold.setText(gold.getText().toString() + " " + resource.getGold());
	}

}
