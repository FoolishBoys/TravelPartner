package com.example.enterView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.realtext.MainActivity;
import com.example.realtext.R;
import com.example.sqlDataBases.InfoDao;
import com.example.sqlDataBases.OpenHelper;

public class person_login extends Activity implements OnClickListener{
	
	private Context mcontext;
	
	private EditText ps_ID;
	private EditText ps_Passw;
	private Button bt_loginin;
	private Button bt_loginup;
	private ImageView img_head;
	
	private static final String DB_FILE = "UserInfo.db",
						 		DB_TABLE = "UserInfo";
	private SQLiteDatabase db;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.person_login);
		
		init();
	}
	/**2016/4/11 登陆机制
	 * coding by foolishboy
	 * 
	 * 1、获取用户ID
	 * 2、通过用户ID获取数据库中的用户密码
	 * 3、进行匹配
	 * 4、成功进入，不成功弹框
	 * 
	 */
	public void init() {
		// TODO Auto-generated method stub
		
		/**2016/4/27
		 * 习惯纠正！！ 
		 * 1、既然想方便，就要注意在头部init一个 Context的上下文对象，
		 * 		！**然后 mcontext=this;不能忘记
		 * 2、不然就最好是使用ClassName.this
		 * 		或者getApplicationContext();
		 */
		mcontext=this;
		 ps_ID = (EditText) findViewById(R.id.ID);
		 ps_Passw = (EditText) findViewById(R.id.Password);
		 bt_loginin = (Button) findViewById(R.id.login_in);
		 bt_loginup = (Button) findViewById(R.id.login_up);
		 
		 img_head = (ImageView) findViewById(R.id.Person);
		
		OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_FILE, null, 1);
		db=openHelper.getReadableDatabase();
		
		bt_loginin.setOnClickListener(this);
		bt_loginup.setOnClickListener(this);
		

	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.login_in:
				login();
				break;
			case R.id.login_up:
				Intent intent2 = new Intent(person_login.this,login_up_default.class);
				startActivity(intent2);
				
				break;
		}
	}
	
	
	/**2016/4/27 14:18
	 * 重要bug！！检讨：
	 * cursor{name,UserID,UserPassword}
	 * 
	 * 由于cursor.getString(index)中的角标填写为3导致错误
	 * 错误信息：NullpointerException：...couldn't read row 0，col 3... （空指针）
	 * 
	 * 耗时1天半
	 */
	public void login() {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent(person_login.this,MainActivity.class);
		InfoDao dao = new InfoDao(mcontext);
		Cursor cursor = null;
		
		String loginin_id=ps_ID.getText().toString().trim();
		String loginin_passw=ps_Passw.getText().toString().trim();
		
		if(TextUtils.isEmpty(loginin_id)||TextUtils.isEmpty(loginin_passw)){
			Toast.makeText(mcontext, "用户名密码不能为空!!", 0).show();
			return;
		}
		//取出数据库中id对应的密码对象
		if(!TextUtils.isEmpty(loginin_id)){
			cursor = db.query(true, DB_TABLE, new String[]{"name","UserID","UserPassword"},
					"UserID="+"\""+loginin_id+"\"", null, null, null, null, null);
		}
		
		if(cursor==null){
			Toast.makeText(mcontext, "结果集为空", 0).show();
			return;
		}
		//判断取出对象的密码和用户输入密码是否匹配
		if(cursor.getCount()==0){
			Toast.makeText(mcontext, "该用户名未注册！请重新输入...", 0).show();
			return;
		}else{
			if(cursor.moveToPosition(0)){
				if(cursor.getString(2).equals(loginin_passw)){
					Toast.makeText(mcontext, "亲爱的"+cursor.getString(0)+"，欢迎回来~！", 1).show();
					startActivity(intent1);
					finish();
					return;
				}else{
					Toast.makeText(mcontext, "密码输入错误，请重新输入！", 0).show();
					return;
				}
			}
		}
			
	}
		
}
	


	
