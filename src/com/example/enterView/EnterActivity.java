package com.example.enterView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.realtext.MainActivity;
import com.example.realtext.R;

public class EnterActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enter_view);
		
		new Handler().postDelayed(new Runnable(){
			
			@Override
			public void run(){
				Intent start=new Intent();
				start.setClass(EnterActivity.this, person_login.class);
				startActivity(start);
				finish();
			}
			
		},1500);
	}
}
