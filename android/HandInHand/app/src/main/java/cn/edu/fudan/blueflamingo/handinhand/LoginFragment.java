package cn.edu.fudan.blueflamingo.handinhand;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

	private FragmentManager fragmentManager;

	private Global globalVal;

	private UserHelper userHelper = new UserHelper();

	private LoginConfirmTask loginConfirmTask;
	private ButtonFlat btn_confirm;
	private ButtonFlat btn_back;

    /**
     * Instantiates a new Login fragment.
     */
    public LoginFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		fragmentManager = getFragmentManager();
		globalVal = (Global) getActivity().getApplication();
		View v = inflater.inflate(R.layout.fragment_login, container, false);
		btn_confirm = (ButtonFlat) v.findViewById(R.id.auth_btn_confirm);
		btn_back = (ButtonFlat) v.findViewById(R.id.auth_btn_back);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity parent = getActivity();
				EditText usernameEditText = (EditText) parent.findViewById(R.id.auth_form_username);
				EditText passwdEditText = (EditText) parent.findViewById(R.id.auth_form_password);
				String username = usernameEditText.getText().toString();
				String passwd = passwdEditText.getText().toString();
				loginConfirmTask = new LoginConfirmTask();
                loginConfirmTask.execute(username, passwd);
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

	private class LoginConfirmTask extends AsyncTask<String, Integer, Boolean> {

		private ProgressBar progressbar;

		@Override
		protected void onPreExecute() {
            btn_confirm.setEnabled(false);
            Toast.makeText(getActivity(),"登陆中...",Toast.LENGTH_SHORT).show();
			progressbar = (ProgressBar) getActivity().findViewById(R.id.auth_login_progressbar);
		}

		@Override
		protected Boolean doInBackground(String... params) {
			String username = params[0];
			String passwd = params[1];
			publishProgress(20);
			if (userHelper.authenticate(username, passwd) != 0) {
				publishProgress(60);
				User user = userHelper.getbasic(username);
				publishProgress(80);
				globalVal.setNickname(user.getNickname());
				globalVal.setUid(user.getId());
				publishProgress(100);
				return true;
			} else {
				return false;
			}
		}

		@Override
		protected void onProgressUpdate(Integer... progresses) {
			progressbar.setProgress(progresses[0]);
		}

		@Override
		protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getActivity(), "登陆成功", Toast.LENGTH_SHORT).show();
                globalVal.setLoginFlag(true);
                getActivity().setResult(AuthActivity.LOGINED, getActivity().getIntent());
                getActivity().finish();
            } else {
                globalVal.setLoginFlag(false);
                Toast.makeText(getActivity(), "登陆失败", Toast.LENGTH_SHORT).show();
            }
            btn_confirm.setEnabled(true);
        }

		@Override
		protected void onCancelled() {
			progressbar.setProgress(0);
			progressbar.setVisibility(View.GONE);
			btn_confirm.setEnabled(true);
		}
	}

}
