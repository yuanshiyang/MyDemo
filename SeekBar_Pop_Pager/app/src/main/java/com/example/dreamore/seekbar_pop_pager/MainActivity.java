package com.example.dreamore.seekbar_pop_pager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements MySeekBar.OnMyPageChangeListener,ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private MySeekBar mySeekBar;
    private List<Integer> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<Integer>();
        int[] imageids = new int[] {R.drawable.a_pic,R.drawable.b_pic,R.drawable.c_pic,R.drawable.d_pic,R.drawable.d_pic,R.drawable.e_pic};
        for(int i=0;i<imageids.length;i++){
            list.add(imageids[i]);
        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        SeekBarPagerAdapter adapter = new SeekBarPagerAdapter(this, null);
        viewPager.setOnPageChangeListener(this);
        viewPager.setAdapter(adapter);
        mySeekBar = (MySeekBar) findViewById(R.id.mySeekBar);
        mySeekBar.setOnMyPageChangeListener(this);
        mySeekBar.setMax(imageids.length-1);
        mySeekBar.setMaxPage(imageids.length-1);
        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onPageChanged(int currentPage) {
        viewPager.setCurrentItem(currentPage);
    }
    // 这个方法在手指操作屏幕的时候发生变化。有三个值：0（END）,1(PRESS) , 2(UP) 。
    @Override
    public void onPageScrollStateChanged(int state) {

    }
    // 这个方法会在屏幕滚动过程中不断被调用。
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    // 当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），手指抬起来就会立即执行这个方法，position就是当前滑动到的页面。
    @Override
    public void onPageSelected(int position) {
        mySeekBar.setProgress(position);

    }
}

