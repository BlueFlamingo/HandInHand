package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.gc.materialdesign.views.ButtonFlat;
import com.gc.materialdesign.views.ButtonRectangle;
import com.jpardogo.android.googleprogressbar.library.FoldingCirclesDrawable;

import java.util.ArrayList;

import cn.edu.fudan.blueflamingo.handinhand.adapter.AnswerAdapter;
import cn.edu.fudan.blueflamingo.handinhand.adapter.CommentAdapter;
import cn.edu.fudan.blueflamingo.handinhand.adapter.QuestionAdapter;
import cn.edu.fudan.blueflamingo.handinhand.adapter.SimpleAnswerAdapter;
import cn.edu.fudan.blueflamingo.handinhand.lib.AppUtility;
import cn.edu.fudan.blueflamingo.handinhand.middleware.SearchHelper;
import cn.edu.fudan.blueflamingo.handinhand.model.ExAnswer;
import cn.edu.fudan.blueflamingo.handinhand.model.ExComment;
import cn.edu.fudan.blueflamingo.handinhand.model.ExQuestion;

/**
 * The type Search activity.
 */
public class SearchActivity extends ActionBarActivity {

	private ArrayList<ArrayList<Object>> searchArrayList = new ArrayList<>();
	private ArrayList<ExQuestion> questionArrayList = new ArrayList<>();
	private ArrayList<ExAnswer> answerArrayList = new ArrayList<>();
	private ArrayList<ExComment> commentArrayList = new ArrayList<>();

	private RecyclerView mQuestionRecycleView;
	private QuestionAdapter questionAdapter;
	private RecyclerView mAnswerRecycleView;
	private SimpleAnswerAdapter answerAdapter;
	private RecyclerView mCommentRecycleView;
	private CommentAdapter commentAdapter;

	private TextView tabLabel1;
	private TextView tabLabel2;
	private TextView tabLabel3;
	private ViewPager viewPager;
	private View cursor;

	private ProgressBar progressBar;

	private int offset;
	private int currentTabId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		progressBar = (ProgressBar) findViewById(R.id.search_progressbar);

		initCursor();
		initTab();
		bindTabClick();

		ButtonRectangle searchAction = (ButtonRectangle) findViewById(R.id.search_btn_action);
		searchAction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				(new SearchTask()).execute();
			}
		});
		final EditText searchInput = (EditText) findViewById(R.id.search_input);
		searchInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					(new SearchTask()).execute();
				}
				return false;
			}
		});
	}

	private void bindTabClick() {
		tabLabel1 = (TextView) findViewById(R.id.search_tab_label1);
		tabLabel2 = (TextView) findViewById(R.id.search_tab_label2);
		tabLabel3 = (TextView) findViewById(R.id.search_tab_label3);

		tabLabel1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(0);
			}
		});
		tabLabel2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(1);
			}
		});
		tabLabel3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				viewPager.setCurrentItem(3);
			}
		});
	}

	private void initCursor() {
		cursor = (ImageView) findViewById(R.id.search_tab_cursor);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int cursorWidth = displayMetrics.widthPixels / 3;
		int cursorHeight = displayMetrics.heightPixels / 100;
		cursor.getLayoutParams().width = cursorWidth;
		offset = (displayMetrics.widthPixels - cursorWidth) / 2;
	}

	private void initQuestionTab(View v) {
		mQuestionRecycleView = (RecyclerView) v.findViewById(R.id.search_question_recyclerview);
		mQuestionRecycleView.setLayoutManager(new LinearLayoutManager(this));
		mQuestionRecycleView.setItemAnimator(new DefaultItemAnimator());
		mQuestionRecycleView.setHasFixedSize(true);
		questionAdapter = new QuestionAdapter(this, questionArrayList);
		questionAdapter.setOnItemClickListener(new QuestionAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Intent qItemIntent = new Intent(getApplicationContext(), QuestionItemActivity.class);
				ExQuestion exQuestion = questionArrayList.get(position);
				qItemIntent.putExtra("qid", exQuestion.getId());
				qItemIntent.putExtra("uid", exQuestion.getUid());
				qItemIntent.putExtra("title", exQuestion.getTitle());
				qItemIntent.putExtra("content", exQuestion.getContent());
				qItemIntent.putExtra("watchNum", exQuestion.getScore1());
				qItemIntent.putExtra("MODE", QuestionItemActivity.FROM_QUESTION_LIST);
				startActivity(qItemIntent);
			}
		});
		mQuestionRecycleView.setAdapter(questionAdapter);
	}

	private void initAnswerTab(View v) {
		mAnswerRecycleView = (RecyclerView) v.findViewById(R.id.search_answer_recyclerview);
		mAnswerRecycleView.setLayoutManager(new LinearLayoutManager(this));
		mAnswerRecycleView.setItemAnimator(new DefaultItemAnimator());
		mAnswerRecycleView.setHasFixedSize(true);
		answerAdapter = new SimpleAnswerAdapter(this, answerArrayList);
		answerAdapter.setOnItemClickListener(new SimpleAnswerAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				final Intent aItemIntent = new Intent(getApplicationContext(), AnswerItemActivity.class);
				final ExAnswer exAnswer = answerArrayList.get(position);
				aItemIntent.putExtra("aid", exAnswer.getId());
				aItemIntent.putExtra("uid", exAnswer.getUid());
				aItemIntent.putExtra("qid", exAnswer.getQid());
				aItemIntent.putExtra("nickname", exAnswer.getNickname());
				aItemIntent.putExtra("portrait", exAnswer.getUserHead());
				aItemIntent.putExtra("content", exAnswer.getContent());
				aItemIntent.putExtra("approvNum", exAnswer.getScore1());
				//TODO:title传递问题
				startActivity(aItemIntent);
			}
		});
		mAnswerRecycleView.setAdapter(answerAdapter);
	}

	private void initCommentTab(View v) {
		mCommentRecycleView = (RecyclerView) v.findViewById(R.id.search_comment_recyclerview);
		mCommentRecycleView.setLayoutManager(new LinearLayoutManager(this));
		mCommentRecycleView.setItemAnimator(new DefaultItemAnimator());
		mCommentRecycleView.setHasFixedSize(true);
		commentAdapter = new CommentAdapter(this, commentArrayList);
		//TODO:comment跳跃
		mCommentRecycleView.setAdapter(commentAdapter);
	}

	private void initTab() {
		viewPager = (ViewPager) findViewById(R.id.search_viewPager);
		LayoutInflater layoutInflater = getLayoutInflater();
		ArrayList<View> views = new ArrayList<>();
		views.add(layoutInflater.inflate(R.layout.tab_question, null));
		views.add(layoutInflater.inflate(R.layout.tab_answer, null));
		views.add(layoutInflater.inflate(R.layout.tab_comment, null));
		initQuestionTab(views.get(0));
		initAnswerTab(views.get(1));
		initCommentTab(views.get(2));
		viewPager.setAdapter(new TabAdapater(views));
		viewPager.setCurrentItem(0);
		viewPager.setOnPageChangeListener(new TabListener());
	}

	private class TabListener implements ViewPager.OnPageChangeListener {

        /**
         * The Two.
         */
        int two = offset * 2;

		@Override
		public void onPageScrollStateChanged(int arg) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
				case 0:
					if (currentTabId == 1) {
						animation = new TranslateAnimation(offset, 0, 0, 0);
					} else if (currentTabId == 2) {
						animation = new TranslateAnimation(two, 0, 0, 0);
					}
					break;
				case 1:
					if (currentTabId == 0) {
						animation = new TranslateAnimation(0, offset, 0, 0);
					} else if (currentTabId == 2) {
						animation = new TranslateAnimation(two, offset, 0, 0);
					}
					break;
				case 2:
					if (currentTabId == 0) {
						animation = new TranslateAnimation(0, two, 0, 0);
					} else if (currentTabId == 1) {
						animation = new TranslateAnimation(offset, two, 0, 0);
					}
					break;
			}
			currentTabId = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			cursor.startAnimation(animation);
		}

	}

	private class TabAdapater extends PagerAdapter {

        /**
         * The View array list.
         */
        ArrayList<View> viewArrayList;

		@Override
		public int getCount() {
			return viewArrayList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object o) {
			((ViewPager) container).removeView(viewArrayList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(viewArrayList.get(position));
			return viewArrayList.get(position);
		}

        /**
         * Instantiates a new Tab adapater.
         *
         * @param viewArrayList the view array list
         */
        public TabAdapater(ArrayList<View> viewArrayList) {
			super();
			this.viewArrayList = viewArrayList;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_search, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		return super.onOptionsItemSelected(item);
	}

	private class SearchTask extends AsyncTask<String, Integer, Integer> {

		private SearchHelper searchHelper = new SearchHelper();

		@Override
		protected void onPreExecute() {
			progressBar.setVisibility(View.VISIBLE);
			progressBar.setIndeterminateDrawable(new FoldingCirclesDrawable.Builder(getApplicationContext())
					.build());
            Toast.makeText(getApplicationContext(),"搜索中...",Toast.LENGTH_SHORT).show();
		}

		@Override
		protected Integer doInBackground(String... params) {
			String searchWords;
			EditText sIn = (EditText) findViewById(R.id.search_input);
			searchWords = sIn.getText().toString();
			Log.d(AppUtility.APPNAME, searchWords);
			searchArrayList = searchHelper.Search(searchWords);
			questionArrayList.clear();
			answerArrayList.clear();
			commentArrayList.clear();
			for (Object item : searchArrayList.get(0)) {
				questionArrayList.add((ExQuestion) item);
				//Log.d("search question", String.valueOf(((ExQuestion) item).getId()));
			}
			for (Object item : searchArrayList.get(1)) {
				answerArrayList.add((ExAnswer) item);
				//Log.d("search answer", String.valueOf(((ExAnswer) item).getId()));
			}
			for (Object item : searchArrayList.get(2)) {
				commentArrayList.add((ExComment) item);
				//Log.d("search comment", String.valueOf(((ExComment) item).getId()));
			}
			return 0;
		}

		@Override
		protected void onPostExecute(Integer res) {
			questionAdapter.notifyDataSetChanged();
			answerAdapter.notifyDataSetChanged();
			commentAdapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(),"搜索完成！",Toast.LENGTH_SHORT).show();
			progressBar.setVisibility(View.GONE);
		}

	}

}
