package cn.edu.fudan.blueflamingo.handinhand;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;

/**
 * The type Register fragment.
 */
public class RegisterFragment extends Fragment {

	private FragmentManager fragmentManager;

	private Global globalVal;
	private UserHelper userHelper = new UserHelper();
	private RegisterConfirmTask registerConfirmTask;

	private final static int SUCCESS = 0;
	private final static int INCONSISTENT_PASSWORD = 1;
	private final static int REPEATED_USERNAME = 2;
	private final static int UNKNOWN_ERROR = 3;

	private ButtonFlat btn_confirm;

    /**
     * Instantiates a new Register fragment.
     */
    public RegisterFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		fragmentManager = getFragmentManager();
		globalVal = (Global)getActivity().getApplication();
		View v = inflater.inflate(R.layout.fragment_register, container, false);
		btn_confirm = (ButtonFlat) v.findViewById(R.id.auth_btn_confirm);
		ButtonFlat btn_back = (ButtonFlat) v.findViewById(R.id.auth_btn_back);
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Activity parent = getActivity();
				EditText usernameEditText = (EditText) parent.findViewById(R.id.auth_form_username);
				EditText nicknameEditText = (EditText) parent.findViewById(R.id.auth_form_nickname);
				EditText passwd1EditText = (EditText) parent.findViewById(R.id.auth_form_password);
				EditText passwd2EditText = (EditText) parent.findViewById(R.id.auth_form_password2);
				String username = usernameEditText.getText().toString();
				String nickname = nicknameEditText.getText().toString();
				String passwd1 = passwd1EditText.getText().toString();
				String passwd2 = passwd2EditText.getText().toString();
				registerConfirmTask = new RegisterConfirmTask();
				registerConfirmTask.execute(username, nickname, passwd1, passwd2);
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


	private int checkUsernameAvaliable(String username) {
		int uExists = userHelper.count(username);
		if (uExists > 0) {
			return REPEATED_USERNAME;
		} else {
			return SUCCESS;
		}
	}

	private int checkPasswdConsistent(String pwd1, String pwd2) {
		if (!pwd1.equals(pwd2)) {
			return INCONSISTENT_PASSWORD;
		} else {
			return SUCCESS;
		}
	}

	private class RegisterConfirmTask extends AsyncTask<String, Integer, Integer> {

		private String _nickname;
		private int _uid;

		@Override
		protected void onPreExecute() {
			btn_confirm.setEnabled(false);
			Toast.makeText(getActivity(), "注册中...", Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			String username = params[0];
			String nickname = params[1];
			String passwd1 = params[2];
			String passwd2 = params[3];

			_nickname = nickname;

			if (checkUsernameAvaliable(username) != SUCCESS) {
				return REPEATED_USERNAME;
			}

			if (checkPasswdConsistent(passwd1, passwd2) != SUCCESS) {
				return INCONSISTENT_PASSWORD;
			}

			User user = new User(username, nickname, passwd1);
			int uid = userHelper.register(user);
			_uid = uid;
			if (uid != 0) {
				globalVal.setUid(uid);
				globalVal.setNickname(nickname);
				globalVal.setLoginFlag(true);
				return SUCCESS;
			} else {
				return UNKNOWN_ERROR;
			}
		}

		@Override
		protected void onPostExecute(Integer result) {
			btn_confirm.setEnabled(true);
			switch (result) {
				case SUCCESS:
					Toast.makeText(getActivity(), "注册成功", Toast.LENGTH_SHORT).show();
					globalVal.setNickname(_nickname);
					globalVal.setUid(_uid);
					//redirect to main activity
					getActivity().setResult(AuthActivity.REGISTERED, getActivity().getIntent());
					getActivity().finish();
					break;
				case INCONSISTENT_PASSWORD:
					Toast.makeText(getActivity(), "密码输入不一致", Toast.LENGTH_SHORT).show();
					break;
				case REPEATED_USERNAME:
					Toast.makeText(getActivity(), "用户名已被注册", Toast.LENGTH_SHORT).show();
					break;
				case UNKNOWN_ERROR:
					Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(getActivity(), "注册失败", Toast.LENGTH_SHORT).show();
			}
		}
	}

}
