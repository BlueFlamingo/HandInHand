package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AuthFragment extends Fragment {

	private Global globalVal;

	private FragmentManager fragmentManager;

	public AuthFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		globalVal = (Global) getActivity().getApplication();
		fragmentManager = getFragmentManager();
		View v = inflater.inflate(R.layout.fragment_auth, container, false);
		bindBtnAction(v);

		return v;
	}

	private void bindBtnAction(View v) {
		com.gc.materialdesign.views.ButtonRectangle btn_login
				= (com.gc.materialdesign.views.ButtonRectangle) v.findViewById(R.id.auth_btn_login);
		com.gc.materialdesign.views.ButtonRectangle btn_logout
				= (com.gc.materialdesign.views.ButtonRectangle) v.findViewById(R.id.auth_btn_register);
		com.gc.materialdesign.views.ButtonFlat btn_hangout
				= (com.gc.materialdesign.views.ButtonFlat) v.findViewById(R.id.auth_btn_hangout);
		btn_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				authLogin();
			}
		});
		btn_logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				authRegister();
			}
		});
		btn_hangout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				authHangout();
			}
		});
	}

	private void authLogin() {
		globalVal.setLoginFlag(true);
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.auth_fragment, new LoginFragment(),"login");
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	private void authRegister() {
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.auth_fragment, new RegisterFragment(),"register");
		fragmentTransaction.addToBackStack(null);
		fragmentTransaction.commit();
	}

	private void authHangout() {
		getActivity().setResult(AuthActivity.HANG_OUT, getActivity().getIntent());
		getActivity().finish();
	}
}
