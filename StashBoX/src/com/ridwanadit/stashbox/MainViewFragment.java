package com.ridwanadit.stashbox;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ridwanadit.stashbox.stashjson.StashArray;
import com.ridwanadit.stashbox.stashjson.StashJSONParser;
import com.ridwanadit.stashbox.stashjson.StashObject;

public class MainViewFragment extends Fragment {
	List<StashObject> stashlist = new ArrayList<StashObject>();
	StashListViewAdapter adapter;
	StashArray stashes;
	ListView liststash;
	TextView addstash;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_main_ui, container,false);
		addstash = (TextView) view.findViewById(R.id.textview_addstash);
		liststash = (ListView) view.findViewById(R.id.listview_stash);
		adapter=new StashListViewAdapter(getActivity(), stashlist);
		liststash.setAdapter(adapter);
		liststash.setBackgroundResource(R.drawable.border_ui);
		liststash.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterview, View view, int position,
					long id) {
				Toast.makeText(getActivity(), adapter.getItem(position).getNama(), Toast.LENGTH_SHORT).show();
			}
		});
		new getStashData().execute();
		return view;
	}
	
	
	private class getStashData extends AsyncTask<Void, Void, StashArray> {

		@Override
		protected StashArray doInBackground(Void... params) {
			try {
				InputStream	in = getResources().openRawResource(R.raw.stash);
				stashes = StashJSONParser.parse(in);
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return stashes;
		}

		@Override
		protected void onPostExecute(StashArray result) {
		
			super.onPostExecute(result);
			for (StashObject stash : result.getStash()) {
				adapter.add(stash);
				Log.d("stashname", stash.getNama());
				Log.d("stashamount", stash.getJumlah());
			}
			adapter.notifyDataSetChanged();
		}
		
		
		
	}
}
