package com.example.FragView;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.LittleThings.MyFragmentPageAdapter;
import com.example._Travel.Tra_guide;
import com.example._Travel.Tra_traveler;
import com.example.realtext.R;

public class Frag_Travel extends Fragment{
	
	Resources res;
	private ViewPager mPager;
	private ArrayList<Fragment> al_fragment;
	private ImageView img_underline;
	private TextView tv_guide,
					 tv_traveler;
	Fragment guide,
			 traveler;
	
	
	private int currIndex = 0;
	private int underlineWidth;
	private int offset=0;
	private int position_one;
	
	private final static int num = 2;
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
								Bundle savedInstanceState){
		
		View view=inflater.inflate(R.layout.fragment_travel,null);
		
		res=getResources();
		/**2016/5/4  嵌入fragment的viewpager
		 * 
		 */
		//设置按钮下划线的属性
		InitWidth(view);
		InitTextView(view);
		//设置ViewPager
		InitViewPager(view);
		
		TranslateAnimation animation = new TranslateAnimation(position_one, offset,0,0);
		tv_traveler.setTextColor(res.getColor(R.color.black));
		animation.setFillAfter(true);
		animation.setDuration(300);
		img_underline.startAnimation(animation);
		return view;
	}

	
	
	private void InitWidth(View parentView){
		img_underline=(ImageView) parentView.findViewById(R.id.imag_underline);
		underlineWidth=img_underline.getLayoutParams().width;
		DisplayMetrics dm = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenW=dm.widthPixels;
		//定义下划线的初始位置
		offset=(int)((screenW/num-underlineWidth)/2);
		int avg=(int)(screenW/num);
		position_one=avg + offset;
		
	}
	
	private void InitTextView(View parentView){
		tv_guide=(TextView) parentView.findViewById(R.id.tv_guide);
		tv_traveler=(TextView) parentView.findViewById(R.id.tv_traveler);
		
		tv_guide.setOnClickListener(new MyOnClickListener(0));
		tv_traveler.setOnClickListener(new MyOnClickListener(1));
		
	}
	
	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;
		public MyOnClickListener(int i){
			index=i;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index,true);
		}
	}
	
	private void InitViewPager(View parentView){
		mPager=(ViewPager) parentView.findViewById(R.id.vPager);
		al_fragment=new ArrayList<Fragment>();
		
		guide=new Tra_guide();
		traveler=new Tra_traveler();
		
		al_fragment.add(guide);
		al_fragment.add(traveler);
		
		mPager.setAdapter(new MyFragmentPageAdapter(getChildFragmentManager(),al_fragment));
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPager.setCurrentItem(0,true);
		
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener{

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			Animation animation=null;
			switch(arg0){
			case 0:
				if(currIndex==1){
					animation=new TranslateAnimation(position_one, offset, 0, 0);
					tv_traveler.setTextColor(res.getColor(R.color.black));
				}
				tv_guide.setTextColor(res.getColor(R.color.white));
				break;
			case 1:
				if(currIndex==0){
					animation=new TranslateAnimation(offset, position_one, 0, 0);
					tv_guide.setTextColor(res.getColor(R.color.black));
				}
				tv_traveler.setTextColor(res.getColor(R.color.white));
				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);
			animation.setDuration(300);
			img_underline.startAnimation(animation);
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public void setMenuVisibility(boolean menuVisible){
		super.setMenuVisibility(menuVisible);
		if(this.getView()!=null){
			this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
		}
	
	}
}
