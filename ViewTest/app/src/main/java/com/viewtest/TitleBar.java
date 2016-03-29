package com.viewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zml on 2016/3/28.
 */
public class TitleBar extends RelativeLayout {
    private Button leftButton,rightButton;
    private TextView titleTextView;

    private LayoutParams mlblayoutParams,mrlayoutparams,mtlayoutparams;

    // 左按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;
    // 右按钮的属性值，即我们在atts.xml文件中定义的属性
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    // 标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;
    // 映射传入的接口对象
    private topbarClickListener mListener;
    private int leftBtBColor;
    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 设置topbar的背景
        setBackgroundColor(0xFFF59563);
        // 通过这个方法，将你在atts.xml中定义的declare-styleable
        // 的所有属性的值存储到TypedArray中
        TypedArray ta = context.obtainStyledAttributes(attrs,
                R.styleable.TitleBar);

        mLeftTextColor = ta.getColor(
                R.styleable.TitleBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(
                R.styleable.TitleBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TitleBar_leftText);

        mRightTextColor = ta.getColor(
                R.styleable.TitleBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(
                R.styleable.TitleBar_rightBackground);
        mRightText = ta.getString(R.styleable.TitleBar_rightText);

        mTitleTextSize = ta.getDimension(
                R.styleable.TitleBar_mtitleTextSize, 10);
        mTitleTextColor = ta.getColor(
                R.styleable.TitleBar_mtitleTextColor, 0);
        mTitle = ta.getString(R.styleable.TitleBar_mtitletext);

        // 获取完TypedArray的值后，一般要调用
        // recyle方法来避免重新创建的时候的错误
        ta.recycle();
        leftButton = new Button(context);
       rightButton = new Button(context);
        titleTextView = new TextView(context);

        leftButton.setTextColor(mLeftTextColor);
        leftButton.setBackground(mLeftBackground);
        leftButton.setText(mLeftText);

        rightButton.setTextColor(mRightTextColor);
        rightButton.setBackground(mRightBackground);
        rightButton.setText(mRightText);

        titleTextView.setText(mTitle);
        titleTextView.setTextColor(mTitleTextColor);
        titleTextView.setTextSize(mTitleTextSize);
        titleTextView.setGravity(Gravity.CENTER);

        mrlayoutparams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mrlayoutparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);
        addView(rightButton, mrlayoutparams);

        mlblayoutParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        mlblayoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);
        // 添加到ViewGroup
        addView(leftButton, mlblayoutParams);

        mtlayoutparams=new LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mtlayoutparams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        addView(titleTextView, mtlayoutparams);


        // 按钮的点击事件，不需要具体的实现，
        // 只需调用接口的方法，回调的时候，会有具体的实现
        rightButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        leftButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }
    /**
     * 设置按钮的显示与否 通过id区分按钮，flag区分是否显示
     *
     * @param id   id
     * @param flag 是否显示
     */
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                leftButton.setVisibility(View.VISIBLE);
            } else {
                rightButton.setVisibility(View.VISIBLE);
            }
        } else {
            if (id == 0) {
                leftButton.setVisibility(View.GONE);
            } else {
                rightButton.setVisibility(View.GONE);
            }
        }
    }

    // 暴露一个方法给调用者来注册接口回调
    // 通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }
    public interface topbarClickListener {
        // 左按钮点击事件
        void leftClick();
        // 右按钮点击事件
        void rightClick();
    }
}
