package com.example.realtext;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ZxingView extends Activity{

	private Button zReturn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_zxing);
		
		initZxingView();
	}


	protected void initZxingView() {
		// TODO Auto-generated method stub
		
		zReturn=(Button) findViewById(R.id.zxing_return);
		
		zReturn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				finish();
				
			}
		});
		
	}
	
	
	
	
}
