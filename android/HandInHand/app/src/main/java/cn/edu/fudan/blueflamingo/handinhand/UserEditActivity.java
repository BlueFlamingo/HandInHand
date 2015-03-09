package cn.edu.fudan.blueflamingo.handinhand;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.lib.DiskLruCache;
import cn.edu.fudan.blueflamingo.handinhand.middleware.UserHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.User;


/**
 * The type User edit activity.
 */
public class UserEditActivity extends ActionBarActivity {

	private final static String[] genderArray = {"男", "女"};
	private int uid;
	private UserHelper userHelper = new UserHelper();
	private User currentUser;
	private User userCopy;

	private static final int PHOTO_REQUEST_TAKEPHOTO = 1;// 拍照
	private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
	private static final int PHOTO_REQUEST_CUT = 3;// 结果

	private File tempFile;
	private File filePath;
	private String fileName;
	private Bitmap photo;

	private static final int cacheSize = 1024 * 1024 * 10;		//10M cache
	private DiskLruCache mDiskLruCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_edit);
		initToolbar();
		uid = getIntent().getExtras().getInt("uid");

		(new LoadProfileTask()).execute();
		filePath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "HandInHand");
		if (!filePath.exists()) {
			if (!filePath.mkdir()) {
				Log.d("HandInHand", "Failed to create directory");
			}
		}

		try {
			File cacheDir = AppUtility.getDiskCacheDir(this, AppUtility.APPNAME);
			if (!cacheDir.exists()) {
				cacheDir.mkdirs();
			}
			mDiskLruCache = mDiskLruCache.open(cacheDir, AppUtility.getAppVersion(this),
					1, cacheSize);
		} catch (Exception e) {
			Log.d(AppUtility.APPNAME, "failed to open disk cache");
		}

		ButtonFlat changePortrait = (ButtonFlat) findViewById(R.id.user_edit_btn_editPortrait);
		changePortrait.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
	}

	private void initToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.user_edit_toolbar);
		if (toolbar != null) {
			toolbar.setTitle("编辑个人信息");
			toolbar.inflateMenu(R.menu.menu_user_edit);
			setSupportActionBar(toolbar);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_user_edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		switch (id) {
			case R.id.action_save:
				(new SaveProfileTask()).execute();
				break;
			case R.id.action_cancel:
				finish();
		}

		return super.onOptionsItemSelected(item);
	}

	private class LoadProfileTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			currentUser = userHelper.getByUid(uid);
			//make a copy
			userCopy = new User();
			userCopy.signature = currentUser.signature;
			userCopy.portrait = currentUser.portrait;
			userCopy.nickname = currentUser.nickname;
			userCopy.male = currentUser.male;

			try {
				String portraitMD5 = AppUtility.md5(currentUser.portrait);
				DiskLruCache.Snapshot snapshot = mDiskLruCache.get(portraitMD5);
				if (snapshot == null) {
					//cache miss
					DiskLruCache.Editor editor = mDiskLruCache.edit(portraitMD5);
					OutputStream outputStream = editor.newOutputStream(0);
					if (AppUtility.downloadUrlToStream(AppUtility.STORAGE_URL + currentUser.portrait, outputStream)) {
						editor.commit();
					} else {
						editor.abort();
					}
					Log.d("handinhand", "DOWNLOADING FROM NETWORK");
					mDiskLruCache.flush();
				}
			} catch (Exception e) {
				Log.d(AppUtility.APPNAME, e.toString());
			}
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			ImageView portraitImageView = (ImageView) findViewById(R.id.user_edit_portrait);
			EditText nicknameEditText = (EditText) findViewById(R.id.user_edit_nickname);
			Spinner genderSpinner = (Spinner) findViewById(R.id.user_edit_gender_spinner);
			EditText signatureEditText = (EditText) findViewById(R.id.user_edit_signature);

			try {
				String portraitMD5 = AppUtility.md5(currentUser.portrait);
				DiskLruCache.Snapshot snapshot = mDiskLruCache.get(portraitMD5);
				if (snapshot != null) {
					//cache hit
					InputStream inputStream = snapshot.getInputStream(0);
					Bitmap portrait = BitmapFactory.decodeStream(inputStream);
					portraitImageView.setImageBitmap(portrait);
				}
			} catch (Exception e) {
				Log.d(AppUtility.APPNAME, e.toString());
			}

			nicknameEditText.setText(currentUser.getNickname());
			genderSpinner.setSelection(currentUser.getMale());
			signatureEditText.setText(currentUser.getSignature());
		}

	}

	private class SaveProfileTask extends AsyncTask<Integer, Integer, Integer> {

		private EditText nicknameEditText;
		private Spinner genderSpinner;
		private EditText signatureEditText;

		@Override
		protected void onPreExecute() {
			nicknameEditText = (EditText) findViewById(R.id.user_edit_nickname);
			genderSpinner = (Spinner) findViewById(R.id.user_edit_gender_spinner);
			signatureEditText = (EditText) findViewById(R.id.user_edit_signature);
			nicknameEditText.setEnabled(false);
			genderSpinner.setEnabled(false);
			signatureEditText.setEnabled(false);
		}

		@Override
		protected Integer doInBackground(Integer... params) {
			String nickname = nicknameEditText.getText().toString();
			int gender = genderSpinner.getSelectedItemPosition();
			String signature = signatureEditText.getText().toString();
			currentUser.nickname = nickname;
			currentUser.male = gender;
			currentUser.signature = signature;

			if (!User.equals(currentUser, userCopy)) {
				if (!currentUser.portrait.equals(userCopy.portrait)) {
					currentUser.portrait = AppUtility.trimUpload(userHelper.uploadFile(tempFile.toString()));
					try {
						DiskLruCache.Editor editor = mDiskLruCache.edit(AppUtility.md5(currentUser.portrait));
						if (editor != null) {
							OutputStream outputStream = editor.newOutputStream(0);
							if (photo.compress(Bitmap.CompressFormat.PNG, 70, outputStream)) {
								editor.commit();
							} else {
								editor.abort();
							}
						}
						mDiskLruCache.flush();
					} catch (Exception e) {
						Log.d("HandInHand", e.toString());
					}
				}
				Log.d("HandInHand", "saving profile");
				return userHelper.update(currentUser);
			} else {
				//没有变化
				return -1;
			}
		}

		@Override
		protected void onPostExecute(Integer res) {
			switch (res) {
				case -1:
					if (tempFile != null) {
						if (tempFile.exists()) {
							tempFile.delete();
						}
					}
					finish();
					break;
				case 0:
					Toast.makeText(getApplicationContext(), "保存失败！", Toast.LENGTH_SHORT).show();
					finish();
					break;
				default:
					Toast.makeText(getApplicationContext(), "保存成功！", Toast.LENGTH_SHORT).show();
					try {
						if (tempFile.exists()) {
							tempFile.delete();
						}
					} catch (Exception e) {
						;
					}
					finish();
			}
		}

	}

	// 使用系统当前日期加以调整作为照片的名称
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat("'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	private void showDialog() {
		new AlertDialog.Builder(this)
				.setTitle("选择一张照片作为头像")
				.setPositiveButton("拍照", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						//创建一个以当前时间为名称的文件
						fileName = getPhotoFileName();
						tempFile = new File(filePath, fileName);
						Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
						startActivityForResult(photoIntent, PHOTO_REQUEST_TAKEPHOTO);
					}
				})
				.setNegativeButton("相册", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						Intent galleryIntent = new Intent(Intent.ACTION_PICK, null);
						galleryIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
						startActivityForResult(galleryIntent, PHOTO_REQUEST_GALLERY);
					}
				})
				.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case PHOTO_REQUEST_TAKEPHOTO:
				startPhotoZoom(Uri.fromFile(tempFile), 300);
				break;
			case PHOTO_REQUEST_GALLERY:
				if (data != null) {
					startPhotoZoom(data.getData(), 300);
				} else {
					Log.d("PHOTO_REQUEST_GALLERY", "error");
				}
				break;
			case PHOTO_REQUEST_CUT:
				if (data != null) {
					setPortrait(data);
				} else {
					Log.d("PHOTO_REQUEST_CUT", "error");
				}
		}
	}

	private void startPhotoZoom(Uri uri, int size) {
		Intent cropIntent = new Intent("com.android.camera.action.CROP");
		cropIntent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		cropIntent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		cropIntent.putExtra("aspectX", 1);
		cropIntent.putExtra("aspectY", 1);
		// outputX,outputY 是剪裁图片的宽高
		cropIntent.putExtra("outputX", size);
		cropIntent.putExtra("outputY", size);
		cropIntent.putExtra("return-data", true);
		startActivityForResult(cropIntent, PHOTO_REQUEST_CUT);
	}

	private void setPortrait(Intent picdata) {
		Bundle bundle = picdata.getExtras();
		if (bundle != null) {
			photo = bundle.getParcelable("data");
			if (tempFile == null) {//如果是从相册获取的话
				fileName = getPhotoFileName();
				tempFile = new File(filePath, fileName);
			}
			//修改user
			currentUser.portrait = fileName;
			Log.d("HandInHand", currentUser.portrait);
			try {
				final FileOutputStream out = new FileOutputStream(tempFile);
				//存入本地目录
				photo.compress(Bitmap.CompressFormat.PNG, 100, out);
				out.flush();
				out.close();
			} catch (Exception e) {
				Log.d("HandInHand", e.toString());
			}
			ImageView portraitImageView = (ImageView) findViewById(R.id.user_edit_portrait);
			portraitImageView.setImageBitmap(photo);
		}
	}
}
