package com.example.dreamore.seekbar_pop_pager;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SeekBarPagerAdapter extends ViewPagerAdapter<Integer> {
	private List<Integer> data;
	public SeekBarPagerAdapter(Context context, LinearLayout dot) {
		super(context, dot);
	}

	@Override
	public void setData(List<Integer> data) {
		this.data = data;
	}
	@Override
	public int getCount() {
		return data == null ? 0 : data.size();
	}

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(context, R.layout.pager_image, null);
        ImageView iv_pager = (ImageView) view.findViewById(R.id.iv_pager);
        if(data!=null&&data.size()!=0){
            iv_pager.setImageResource(data.get(position));
        }else{
            iv_pager.setImageResource(R.drawable.ic_launcher);
        }
        container.addView(view);
        return view;
    }
}
