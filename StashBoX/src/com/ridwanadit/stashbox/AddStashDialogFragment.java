package com.ridwanadit.stashbox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ridwanadit.stashbox.stashjson.StashObject;

public class AddStashDialogFragment extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()) ;
		LayoutInflater inflater = getActivity().getLayoutInflater();
		final View view = inflater.inflate(R.layout.dialog_addstash, null);
		builder.setView(view)
			.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
	
				@Override
				public void onClick(DialogInterface dialog, int id) {
					MainViewFragment f = (MainViewFragment) getFragmentManager().findFragmentById(R.id.fragment_container);
					StashObject stash = new StashObject();
					stash.setNama(((EditText)view.findViewById(R.id.addstash_nama)).getText().toString());
					stash.setNo_rek(((EditText)view.findViewById(R.id.addstash_norek)).getText().toString());
					stash.setJumlah(((EditText)view.findViewById(R.id.addstash_jumlah)).getText().toString());
					Toast.makeText(getActivity(), "Sukses", Toast.LENGTH_SHORT).show();
					f.adapter.add(stash);
					f.liststash.addView(f.adapter.getView(f.adapter.getCount()-1, null, f.liststash));
				}
			})
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	
				@Override
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			});
		return builder.create();
	}


}
