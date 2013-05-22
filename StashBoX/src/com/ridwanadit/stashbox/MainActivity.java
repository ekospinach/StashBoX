package com.ridwanadit.stashbox;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mainfragment);
		if (findViewById(R.id.fragment_container)!=null){
			if(savedInstanceState!=null){
				return;
			}

			MainViewFragment mainfragment = new MainViewFragment();
			getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mainfragment).commit();
		}
	}

}
