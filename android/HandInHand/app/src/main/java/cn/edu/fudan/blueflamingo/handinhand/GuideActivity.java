package cn.edu.fudan.blueflamingo.handinhand;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gc.materialdesign.views.ButtonRectangle;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends ActionBarActivity {

    private ViewPager viewPager;
    private GuidePagerAdapter guidePagerAdapter;
    private List<View> guideView;

    private static final int[] pics = {R.drawable.guide1,
            R.drawable.guide2, R.drawable.guide3};
    private ImageView[] dots;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        guideView = new ArrayList<>();
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            iv.setBackgroundColor(getResources().getColor(R.color.yellowGuide));
            iv.setImageBitmap(readBitMap(this, pics[i]));
            guideView.add(iv);
        }
        ImageView iv = new ImageView(this);
        iv.setLayoutParams(mParams);
        iv.setBackgroundColor(getResources().getColor(R.color.yellowGuide));
        guideView.add(iv);
        viewPager = (ViewPager) findViewById(R.id.guide_viewpager);
        guidePagerAdapter = new GuidePagerAdapter(guideView);
        viewPager.setAdapter(guidePagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (i == guideView.size() - 1) {
                    Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onPageSelected(int i) {
                setCurDot(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        initDots();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guide, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class GuidePagerAdapter extends PagerAdapter {

        private List<View> viewList;

        public GuidePagerAdapter(List<View> views) {
            this.viewList = views;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(viewList.get(arg1));
        }

        @Override
        public int getCount() {
            if (viewList != null) {
                return viewList.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(viewList.get(arg1), 0);
            return viewList.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return (arg0 == arg1);
        }
    }

    private void initDots() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.guide_dot_container);
        dots = new ImageView[pics.length];
        for (int i = 0; i < pics.length; i++) {
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(true);
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = (Integer) v.getTag();
                    setCurDot(position);
                    setCurView(position);
                }
            });
            dots[i].setTag(i);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(false);
    }

    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        viewPager.setCurrentItem(position);
    }

    private void setCurDot(int position)
    {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }

        dots[position].setEnabled(false);
        dots[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    public static Bitmap readBitMap(Context context, int resId){

        BitmapFactory.Options opt = new BitmapFactory.Options();

        opt.inPreferredConfig = Bitmap.Config.RGB_565;

        opt.inPurgeable = true;

        opt.inInputShareable = true;

        // 获取资源图片

        InputStream is = context.getResources().openRawResource(resId);

        return BitmapFactory.decodeStream(is, null, opt);

    }
}
