package com.example.viewpagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvStart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvStart = (TextView) findViewById(R.id.tv_start);
		initData();
		tvStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, MyOldtestAct.class);
				startActivity(it);
			}
		});
	}

	private void initData() {
		// 1、直接new 一个线程类，传入参数实现Runnable接口的对象（new Runnable），相当于方法二
		new Thread(new Runnable() {
			@Override
			public void run() {
				// 写子线程中的操作
				 
			}
		}).start();

		// 2、通过实现Runnable接口
		Thread t = new Thread(new myRunnable());
		t.start();

		// 3、通过继承线程类实现
		new myThread().start();

	}

	// Thread是一个类，必须继承
	public class myThread extends Thread {
		@Override
		public void run() {
			super.run();
			// 写子线程中的操作
		}
	}

	// 实现Runnable是接口
	public class myRunnable implements Runnable {
		@Override
		public void run() {
			// 写子线程中的操作

		}

	}

}
