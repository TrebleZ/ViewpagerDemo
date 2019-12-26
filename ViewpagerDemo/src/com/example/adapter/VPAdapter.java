package com.example.adapter;

import java.util.List;

import com.example.viewpagerdemo.MyOldtestAct;
import com.example.viewpagerdemo.R;
import com.example.viewpagerdemo.TestresultAct;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * test
 */
public class VPAdapter extends PagerAdapter {
	// 传递过来的页面view的集合
	private List<View> viewItems;
	// 每个item的页面view
	private View convertView;
	private MyOldtestAct mContext = null;
	private List<String> dataItems;
	ViewHolder holder = null;
	int[] mycount = new int[10];

	// 构造方法
	public VPAdapter(MyOldtestAct context, List<View> viewItems,
			List<String> dataItems) {
		this.mContext = context;
		this.viewItems = viewItems;
		this.dataItems = dataItems;
	}

	// 获取总数
	@Override
	public int getCount() {
		return viewItems.size();
	}

	// 官方推荐这么写
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	// 移除页卡
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(viewItems.get(position));
	}

	// 这里返回View
	@Override
	public Object instantiateItem(ViewGroup container, final int position) {
		// 初始化viewholder
		holder = new ViewHolder();
		// 取到每一个子项，进行赋值
		convertView = viewItems.get(position);
		holder.rapQuestion = (RadioGroup) convertView
				.findViewById(R.id.rap_question);
		holder.questionContent = (TextView) convertView
				.findViewById(R.id.questioncontent);
		holder.txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
		holder.questionContent.setText(dataItems.get(position));
		holder.txtTitle.setText(position + 1 + "/10");
		// 监听RadioGrop的选中事件
		holder.rapQuestion
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						if (position != viewItems.size() - 1) {
							mContext.setCurrentView(position + 1);
						}// 点击默认跳转到下一页
						switch (checkedId) {
						case R.id.chk_nothave:
							mycount[position] = 10;// 对应的分数值
							break;
						case R.id.chk_sometimes:
							mycount[position] = 5;
							break;
						case R.id.chk_usual:
							mycount[position] = 1;
							break;
						}
						if (position == viewItems.size() - 1) {
							/*
							 * 统计分数
							 */
							int num = 0;
							for (int i = 0; i < mycount.length; i++) {
								num = num + mycount[i];
							}
							// 跳转到测试结果页面
							Intent myintent = new Intent(mContext,
									TestresultAct.class);
							myintent.putExtra("num", String.valueOf(num));
							mContext.startActivity(myintent);
							mContext.finish();
						}
					}
				});
		container.addView(convertView);// 添加页卡
		return convertView; // 返回的是View对象进行显示
	}

	// ViewHolder控件容器
	class ViewHolder {
		RadioGroup rapQuestion;
		TextView questionContent;
		TextView txtTopnum;
		TextView txtBottomnum;
		TextView txtTitle;
	}

}
