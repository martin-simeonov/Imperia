package org.elsys.imperia;

import org.elsys.models.Building;
import org.elsys.models.Resource;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class BuildingAdapter extends ArrayAdapter<Building> {

	Context context;
	int layoutResourceId;
	Building data[] = null;

	public BuildingAdapter(Context context, int layoutResourceId,
			Building[] data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		BuildingHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		// Store the View contents in holder
		holder = new BuildingHolder();
		holder.txtName = (TextView) row.findViewById(R.id.name);
		holder.txtCurrentLevel = (TextView) row.findViewById(R.id.currentLevel);
		holder.txtEndTime = (TextView) row.findViewById(R.id.endTime);
		holder.txtWood = (TextView) row.findViewById(R.id.wood);
		holder.txtIron = (TextView) row.findViewById(R.id.iron);
		holder.txtStone = (TextView) row.findViewById(R.id.stone);
		holder.txtGold = (TextView) row.findViewById(R.id.gold);
		holder.btnUpgrade = (Button) row.findViewById(R.id.upgrade);

		// Set all contents values
		Building building = data[position];
		holder.txtName.setText(building.getName());
		holder.txtCurrentLevel.append(Integer.toString(building
				.getCurrentLevel()));
		if (building.isInProgress()) {
			holder.txtEndTime.append(building.getEndTime());
		}
		if (!building.isCanUpgrade()) {
			holder.btnUpgrade.setClickable(false);
		}

		Resource r = building.getNextLevelResources();
		holder.txtWood.append(Integer.toString(r.getWood()));
		holder.txtIron.append(Integer.toString(r.getIron()));
		holder.txtStone.append(Integer.toString(r.getStone()));
		holder.txtGold.append(Integer.toString(r.getGold()));

		return row;
	}

	// Holder for View contents
	static class BuildingHolder {
		TextView txtName;
		TextView txtCurrentLevel;
		TextView txtEndTime;
		TextView txtWood;
		TextView txtIron;
		TextView txtStone;
		TextView txtGold;
		Button btnUpgrade;
	}
}
