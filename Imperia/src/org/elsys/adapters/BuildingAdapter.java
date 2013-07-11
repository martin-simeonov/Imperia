package org.elsys.adapters;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.elsys.imperia.R;
import org.elsys.models.Building;
import org.elsys.models.Resource;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
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
		final TextView txtEndTime = (TextView) row.findViewById(R.id.endTime);
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
			Calendar c = Calendar.getInstance();
			Date date1 = c.getTime();
			Date date2 = null;

			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				date2 = sdf.parse(building.getEndTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			long diff = date2.getTime() - date1.getTime();

			new CountDownTimer(diff, 1000) {

				public void onTick(long millisUntilFinished) {
					txtEndTime.setText("seconds remaining: "
							+ millisUntilFinished / 1000);
				}

				public void onFinish() {
					txtEndTime.setText("Done!");
				}
			}.start();
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
