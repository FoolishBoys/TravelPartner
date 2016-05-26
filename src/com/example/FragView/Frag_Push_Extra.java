package com.example.FragView;

import com.example.realtext.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Frag_Push_Extra extends Activity{

	String[] str_id={"user1","user2","user3","user4","user5","user6"};
	String[] str_passw={"111111","222222","333333","444444","555555","666666"};
	private String str="获得第二页面的返回值";
	
	private Context mContext;
	private Button return_bt;
	private Button bt_denglu;
	private CheckBox CB_remb;
	private AutoCompleteTextView ACt_id;
	private EditText edt_passw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frag_push_extra);
		
		
		return_bt = (Button) findViewById(R.id.return_bt);
		edt_passw=(EditText) findViewById(R.id.push_edt_passw);
		bt_denglu=(Button) findViewById(R.id.push_extra_bt_DENGLU);
		CB_remb=(CheckBox) findViewById(R.id.push_extra_CheckB_remb);
		ACt_id=(AutoCompleteTextView) findViewById(R.id.push_ACt_id);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line , str_id);
		ACt_id.setAdapter(adapter);
		
		return_bt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				
				intent.putExtra("push_text", str);
				
				setResult(1, intent);
				
			}
		});
		
		bt_denglu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				login();
			}
		});
		
		
	}
	
	public void login(){
		
		
		Intent intent_denglu= new Intent();
		
		String Tem_passw=edt_passw.getText().toString();
		String Tem_id=ACt_id.getText().toString();
		Boolean a=comp(Tem_id,Tem_passw);
		
		if(a == true){
			Toast.makeText(Frag_Push_Extra.this,"密码正确" ,Toast.LENGTH_SHORT).show();
			finish();
		}
		if(a == false)
			Toast.makeText(Frag_Push_Extra.this,"用户名或密码错误，请重新输入！" ,Toast.LENGTH_SHORT).show();
		
		if(CB_remb.isChecked()){
			boolean flag = UserInfoUtils.saveUserInfo(Tem_id, Tem_passw);
			
			if(flag){
				Toast.makeText(mContext, "信息保存成功", Toast.LENGTH_SHORT);
				return ;
			}else{
				Toast.makeText(mContext, "信息保存错误", Toast.LENGTH_SHORT);
				return ;
			}
		
		}else{
			Toast.makeText(mContext, "请注意，登陆信息未保存", Toast.LENGTH_SHORT);
		}
	}
	
	protected Boolean comp(String id,String password){
		
		int ss;
		for(ss=0;ss<str_id.length;ss++){
			if(str_id[ss].equals(id))
				break;
		}
		if(ss<str_id.length&&ss>=0){
			if(str_passw[ss].equals(password))
				return true;
			else
				return false;
		}	
		else
			return false;
	}
	
	
}
