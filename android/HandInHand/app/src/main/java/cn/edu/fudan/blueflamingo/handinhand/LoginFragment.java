package cn.edu.fudan.blueflamingo.handinhand;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gc.materialdesign.views.ButtonFlat;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

	private FragmentManager fragmentManager;

	private Global globalVal;

	public LoginFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		fragmentManager = getFragmentManager();
		globalVal = (Global) getActivity().getApplication();
		View v = inflater.inflate(R.layout.fragment_login, container, false);
		ButtonFlat btn_confirm = (ButtonFlat) v.findViewById(R.id.auth_btn_confirm);
		ButtonFlat btn_back = (ButtonFlat) v.findViewById(R.id.auth_btn_back);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO: check if avaliable
				//return to main activity
				globalVal.setLoginFlag(true);
				getActivity().setResult(Activity.RESULT_OK, getActivity().getIntent());
				getActivity().finish();
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
