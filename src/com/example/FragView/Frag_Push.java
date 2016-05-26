package com.example.FragView;

import com.example.realtext.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Frag_Push extends Fragment{

	
	private TextView Pus_tv1;
	private Button Pus_bt1;
	
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,ViewGroup container,
									Bundle savedInstanceState){
		
		View view=LayoutInflater.from(getActivity()).inflate(R.layout.fragment_push,container,false);
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
