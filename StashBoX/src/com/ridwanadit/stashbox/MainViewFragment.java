package com.ridwanadit.stashbox;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.res.Resources.NotFoundException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ridwanadit.stashbox.stashjson.StashArray;
import com.ridwanadit.stashbox.stashjson.StashJSONParser;
import com.ridwanadit.stashbox.stashjson.StashObject;

public class MainViewFragment extends Fragment {
	List<StashObject> stashlist = new ArrayList<StashObject>();
	StashListViewAdapter adapter;
	StashArray stashes;
	LinearLayout liststash;
	TextView addstash;
	View view;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.fragment_main_ui, container,false);
		addstash = (TextView) view.findViewById(R.id.textview_addstash);
		liststash = (LinearLayout) view.findViewById(R.id.listview_stash);
		adapter = new StashListViewAdapter(getActivity(), stashlist);
		liststash.setBackgroundResource(R.drawable.border_ui);		
		addstash.setOnClickListener(addStash);
		new getStashData().execute();
		return view;
	}

	public void test(){
		
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
			for (int count=0;count<adapter.getCount();count++){
				liststash.addView(adapter.getView(count, null, liststash));
			}
			ProgressBar progress = (ProgressBar) view.findViewById(R.id.progressBar1);
			progress.setVisibility(View.GONE);
		}
	}
	
	private final OnClickListener addStash = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			DialogFragment addStashDialog = new AddStashDialogFragment();
			addStashDialog.setTargetFragment(MainViewFragment.this, 10);
			addStashDialog.show(getFragmentManager(), "AddStashDialogFragment");
		}
	};
}
