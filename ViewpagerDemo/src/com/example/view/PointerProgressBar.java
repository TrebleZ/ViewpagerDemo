package com.example.view;

import com.example.viewpagerdemo.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class PointerProgressBar extends View {
	private int start;
	// 构造方法，带有attrs自定义属性
	public PointerProgressBar(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		paint = new Paint();
		TypedArray mTypeArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);
		// 获取自定义属性和默认值
		roundColor = mTypeArray.getColor(
				R.styleable.RoundProgressBar_roundColor, Color.GRAY);
		roundProgressColor = mTypeArray.getColor(
				R.styleable.RoundProgressBar_roundProgressColor, Color.RED);
		textColor = mTypeArray.getColor(R.styleable.RoundProgressBar_textColor,
				Color.WHITE);
		textSize = mTypeArray.getDimension(
				R.styleable.RoundProgressBar_textSize, 15);
		roundWidth = mTypeArray.getDimension(
				R.styleable.RoundProgressBar_roundWidth, 5);
		max = mTypeArray.getInteger(R.styleable.RoundProgressBar_max, 100);
		innerRadius = mTypeArray.getDimension(
				R.styleable.RoundProgressBar_innerRadius, 30);
		innerColor = mTypeArray.getColor(
				R.styleable.RoundProgressBar_innerColor, Color.BLUE);
		pointerAngle = mTypeArray.getInteger(
				R.styleable.RoundProgressBar_pointerAngle, 15);
		mTypeArray.recycle();
	}

	public PointerProgressBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public PointerProgressBar(Context context) {
		this(context, null);
	}

	/**
	 * 画笔
	 */
	private Paint paint;

	/**
	 * 内圆半径
	 */
	private float innerRadius;

	/**
	 * 内圆颜色
	 */
	private int innerColor;

	/**
	 * 指针偏角
	 */
	private int pointerAngle;

	/**
	 * 圆环的颜色
	 */
	private int roundColor;

	/**
	 * 圆环进度的颜色
	 */
	private int roundProgressColor;

	/**
	 * 中间进度百分比的字符串颜色
	 */
	private int textColor;

	/**
	 * 中间进度百分比的字符串大小
	 */
	private float textSize;

	/**
	 * 圆环的宽度
	 */
	private float roundWidth;

	/**
	 * 最大进度
	 */
	private int max;

	/**
	 * 当前进度
	 */
	private int progress;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		start++;
		/**
		 * 画最外层的空心半圆弧
		 */
		int center = getWidth() / 2;
		int radius = (int) (center - roundWidth / 2);// 半径
		paint.setColor(roundColor);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(roundWidth);// 圆弧边线宽度
		paint.setAntiAlias(true); // 圆弧抗锯齿
		RectF oval = new RectF(center - radius, center - radius, center
				+ radius, center + radius);// 指定圆弧的外轮廓矩形区域
		canvas.drawArc(oval, -180, 180, false, paint);
		/**
		 * 画中间的圆心圆
		 */
		paint.setStrokeWidth(0);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(innerColor);
		canvas.drawCircle(center, center, innerRadius, paint);
		/**
		 * 计算各点坐标
		 */
		float angle = (float) (start * 18 / 10);// 需求是从-90~90度刚好旋转了180度
		float cos = (float) Math.cos(Math.PI * angle / 180), sin = (float) Math
				.sin(Math.PI * angle / 180);
		float startX = center - center * cos, startY = center - center * sin;
		float cos1 = (float) Math.cos(Math.PI * (angle + pointerAngle) / 180);
		float sin1 = (float) Math.sin(Math.PI * (angle + pointerAngle) / 180);
		float end1X = center - innerRadius * cos1, end1Y = center - innerRadius
				* sin1;
		float cos2 = (float) Math.cos(Math.PI * (angle - pointerAngle) / 180);
		float sin2 = (float) Math.sin(Math.PI * (angle - pointerAngle) / 180);
		float end2X = center - innerRadius * cos2, end2Y = center - innerRadius
				* sin2;
		Log.e("start ", "angle =" + angle + ",sin = " + sin + ",cos = " + cos
				+ ",x=" + startX + ",y=" + startY);
		/**
		 * 画圆环进度
		 */
		paint.setColor(roundColor);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(roundWidth);
		paint.setColor(roundProgressColor);
		canvas.drawArc(oval, -180, angle, false, paint);
		/**
		 * 画指针
		 */
		paint.setColor(innerColor);
		paint.setStrokeWidth(0);
		paint.setStyle(Paint.Style.FILL);
		Path path = new Path();
		path.moveTo(startX, startY);
		path.lineTo(end1X, end1Y);
		path.lineTo(end2X, end2Y);
		path.close();
		canvas.drawPath(path, paint);
		/**
		 * 画文本进度
		 */
		paint.setStrokeWidth(0);
		paint.setColor(textColor);
		paint.setTextSize(textSize);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		float textWidth = paint.measureText(start + "");
		canvas.drawText(start + "", center - textWidth / 2, center + textSize
				/ 2, paint);
		if (start < (int) (((float) progress / (float) max) * 100))
			invalidate();// 不断做刷新操作
	}

	public float getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(float innerRadius) {
		this.innerRadius = innerRadius;
	}

	public int getRoundColor() {
		return roundColor;
	}

	public void setRoundColor(int roundColor) {
		this.roundColor = roundColor;
	}

	public int getRoundProgressColor() {
		return roundProgressColor;
	}

	public void setRoundProgressColor(int roundProgressColor) {
		this.roundProgressColor = roundProgressColor;
	}

	public int getTextColor() {
		return textColor;
	}

	public void setTextColor(int textColor) {
		this.textColor = textColor;
	}

	public float getTextSize() {
		return textSize;
	}

	public void setTextSize(float textSize) {
		this.textSize = textSize;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

	public synchronized int getMax() {
		return max;
	}

	public synchronized void setMax(int max) {
		if (max < 0)
			throw new IllegalArgumentException("max not less than 0");
		this.max = max;
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		if (max < 0)
			throw new IllegalArgumentException("progress not less than 0");
		if (progress > max)
			progress = max;
		if (progress <= max) {
			this.progress = progress;
			postInvalidate();
		}
	}

	public int getInnerColor() {
		return innerColor;
	}

	public void setInnerColor(int innerColor) {
		this.innerColor = innerColor;
	}

}
