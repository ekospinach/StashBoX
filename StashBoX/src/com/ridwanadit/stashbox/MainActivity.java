package com.ridwanadit.stashbox;


import java.io.File;
import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class MainActivity extends FragmentActivity {
	private static final String STASH_FILENAME = "stash.json";
	
	public static final String STASHPREF_NAME = "StashArray";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mainfragment);
		File f = new File(getApplicationContext().getFilesDir(),STASH_FILENAME);
		if (!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {e.printStackTrace();
			}
		}
		if (findViewById(R.id.fragment_container)!=null){
			if(savedInstanceState!=null){
				return;
			}

			MainViewFragment mainfragment = new MainViewFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainfragment).commit();
		}
	}

}
