package cn.edu.fudan.blueflamingo.handinhand;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonFlat;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

	FragmentManager fragmentManager;

	public LoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		fragmentManager = getFragmentManager();
		View v = inflater.inflate(R.layout.fragment_register, container, false);
		ButtonFlat btn_confirm = (ButtonFlat) v.findViewById(R.id.auth_btn_confirm);
		ButtonFlat btn_back = (ButtonFlat) v.findViewById(R.id.auth_btn_back);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fragmentManager.beginTransaction()
						.replace(R.id.auth_fragment, new AuthFragment())
						.commit();
			}
		});
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				fragmentManager.beginTransaction()
						.replace(R.id.auth_fragment, new AuthFragment())
						.commit();
			}
		});
		return v;
	}


}
