package com.example.LittleThings;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPageAdapter extends FragmentPagerAdapter {
	
	private ArrayList<Fragment> list_fragment;
	
	public MyFragmentPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}
	
	public  MyFragmentPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.list_fragment = fragments;
    }

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_fragment.size();
	}


	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return list_fragment.get(arg0);
	}
	
	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

}
