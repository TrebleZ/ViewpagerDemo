package com.example.viewpagerdemo;

import com.example.view.PointerProgressBar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TestresultAct extends Activity {

	private TextView tvNum;
	private PointerProgressBar pointerProgressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_testresult);
		initView();
		initData();
	}

	private void initData() {
		Intent it = getIntent();
		String num = it.getStringExtra("num");
		pointerProgressBar.setProgress(Integer.parseInt(num));
	}

	private void initView() {
		tvNum = (TextView) findViewById(R.id.tv_warn);
		pointerProgressBar=(PointerProgressBar) findViewById(R.id.roundProgressBar);
	}
}
