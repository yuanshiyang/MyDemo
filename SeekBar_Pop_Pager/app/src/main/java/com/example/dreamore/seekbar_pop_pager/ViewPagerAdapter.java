package com.example.dreamore.seekbar_pop_pager;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public abstract class ViewPagerAdapter<T> extends PagerAdapter {
	protected Context context;
	private LinearLayout dot;
	private List<T> data;
	List<RadioButton> rbList;
	public ViewPagerAdapter(Context context, LinearLayout dot) {
		super();
		this.context = context;
		this.dot = dot;
		rbList = new ArrayList<RadioButton>();
	}
	/**
	 * 如果有点的话，实现了该方法需调用initDots(int number)；
	 * 若无点（即ViewPagerAdapter（context,null））的话，则不须调用。
     */
	public void setData(List<T> data) {
		this.data = data;
		if (data!=null) {
			initDots(data.size());
		}
	}
	/**
	 * 有点的话count=Integer.MAX_VALUE；
	 * 没点的话count=data.size();
	 */
	@Override
	public abstract int getCount();

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view==object;
	}
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
		object = null;
	}
	/**
	 * 初始化点
	 */
	public void initDots(int number) {
		if(dot!=null){
			dot.removeAllViews();
		}
		rbList.clear();
		for (int i = 0; i < number; i++) {
			LinearLayout child = (LinearLayout) View.inflate(context,R.layout.viewpager_dot, null);
			RadioButton rb = (RadioButton) child.findViewById(R.id.rb);
			rbList.add(rb);
			dot.addView(child);
		}
		if (rbList.get(0) != null) {
			rbList.get(0).setChecked(true);
		}
	}
	public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

		private int curPosition =0 ;

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int position) {
			clearChecked();
			if(data!= null && data.size()!=0){
				curPosition = (position) % data.size();
				rbList.get(curPosition).setChecked(true);
			}
		}
		public void clearChecked() {
			for (RadioButton rb : rbList) {
				if (rb != null) {
					rb.setChecked(false);
				}
			}
		}
	}
}
