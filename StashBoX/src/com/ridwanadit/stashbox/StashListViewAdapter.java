package com.ridwanadit.stashbox;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ridwanadit.stashbox.stashjson.StashObject;

public class StashListViewAdapter extends ArrayAdapter<StashObject>{
	private final Context context;
	private final List<StashObject> list;

	static class ViewHolder {
		//TextView stashremove;
		TextView stashname;
		TextView stashamount;
		//Not yet implemented
		//TextView stashincrease;
		//TextView stashdecrease;
	}

	public StashListViewAdapter(Context context, List<StashObject> list) {
		super(context, R.layout.adapter_stash, list);
		this.list=list;
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View rowView = convertView;
		if (rowView==null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			rowView = inflater.inflate(R.layout.adapter_stash, null);
			ViewHolder viewHolder = new ViewHolder();
			//viewHolder.stashremove = (TextView) rowView.findViewById(R.id.stashremove);
			viewHolder.stashname = (TextView) rowView.findViewById(R.id.stashname);
			viewHolder.stashamount = (TextView) rowView.findViewById(R.id.stashamount);
			//Initialize stashincrease & stashdecrease here
			rowView.setTag(viewHolder);
		}
		ViewHolder holder = (ViewHolder) rowView.getTag();
		StashObject stash = list.get(position);
		if (!stash.equals(null)){
			Log.d("stash", stash.toString());
			holder.stashname.setText(stash.getNama());
			holder.stashamount.setText(stash.getJumlah());
		}
		return rowView;
	}


}
