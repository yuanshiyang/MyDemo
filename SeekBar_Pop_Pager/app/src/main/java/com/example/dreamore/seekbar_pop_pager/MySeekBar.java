package com.example.dreamore.seekbar_pop_pager;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

public class MySeekBar extends SeekBar implements SeekBar.OnSeekBarChangeListener{
	private Context context;
	private PopupWindow pop;
	private int maxPage = 100 ;
	private TextView tv;
	private int currentPage = 0;
	private int lastPage = 0;
	private int xoff;
	public MySeekBar(Context context) {
		super(context);
	}

	public MySeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MySeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		initView();
	}
	
	private void initView() {
		tv = new TextView(context);
		tv.setBackgroundResource(R.drawable.kuangkuang);
		tv.setTextSize(14);
		tv.setPadding(0, 4, 0, 0);
		tv.setGravity(Gravity.CENTER_HORIZONTAL);
		tv.setTextColor(Color.parseColor("#FFFFFF"));
		pop = new PopupWindow(context);
		pop.setContentView(tv); 
		pop.setBackgroundDrawable(null);
		pop.setAnimationStyle(0);
		pop.setWidth(MyUtils.dip2px(context, 65)); 
		pop.setHeight(MyUtils.dip2px(context, 40)); 
		xoff = -(pop.getWidth())/2;
		setOnSeekBarChangeListener(this);
	}

	public void setMaxPage(int max){
		this.maxPage = max;
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		currentPage = (int)(progress*1.0f/getMax() * maxPage) ;
		popUpdate(seekBar);
		if (pl!=null  && currentPage!=lastPage) {
			pl.onPageChanged(currentPage);
		}
		lastPage = currentPage;
	}


	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		popShow(seekBar);
	}


	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		popHidden();
	}
	/**
	 * 更新pop的位置和显示的内容
	 * @param view
	 */
	private void popUpdate(View view) {
		int width = view.getWidth();
		tv.setText((currentPage+1)+"页");
		xoff = -(   (pop.getWidth())/2  - (int)(getProgress()*1.0f/ getMax() * width) );
		int yoff = -(pop.getHeight()+view.getHeight()+35);
		pop.update(view,xoff,yoff,-1,-1);
	}
	/**
	 * 显示pop
	 * @param view pop显示位置的参照物
	 */
	private void popShow(View view) {
		tv.setText((currentPage+1)+"页");
		int yoff = -(pop.getHeight()+view.getHeight()+35);
		pop.showAsDropDown(view,xoff,yoff);
	}
	/**
	 * 隐藏pop
	 */
	private void popHidden() {
		if (pop.isShowing()) {
			pop.dismiss();
		}
	}

	OnMyPageChangeListener pl ;
	public OnMyPageChangeListener getPl() {
		return pl;
	}
	public void setOnMyPageChangeListener(OnMyPageChangeListener pl) {
		this.pl = pl;
	}

	public interface OnMyPageChangeListener{
		void onPageChanged(int currentPage);
	}
}
