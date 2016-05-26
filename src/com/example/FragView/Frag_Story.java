package com.example.FragView;

import com.example.realtext.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Frag_Story extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
					Bundle savedInstanceState){
		
		View view=LayoutInflater.from(getActivity())
					.inflate(R.layout.fragment_story,container,false);
		
		
		return view;
	}
	
	@Override
	public void setMenuVisibility(boolean menuVisible){
		super.setMenuVisibility(menuVisible);
		if(this.getView()!=null){
			this.getView().setVisibility(menuVisible?View.VISIBLE:View.GONE);
		}
		
		
	}
	
	
	

}
