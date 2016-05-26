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
	/**2016/4/11 ��½����
	 * coding by foolishboy
	 * 
	 * 1����ȡ�û�ID
	 * 2��ͨ���û�ID��ȡ���ݿ��е��û�����
	 * 3������ƥ��
	 * 4���ɹ����룬���ɹ�����
	 * 
	 */
	public void init() {
		// TODO Auto-generated method stub
		
		/**2016/4/27
		 * ϰ�߾������� 
		 * 1����Ȼ�뷽�㣬��Ҫע����ͷ��initһ�� Context�������Ķ���
		 * 		��**Ȼ�� mcontext=this;��������
		 * 2����Ȼ�������ʹ��ClassName.this
		 * 		����getApplicationContext();
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
	 * ��Ҫbug�������֣�
	 * cursor{name,UserID,UserPassword}
	 * 
	 * ����cursor.getString(index)�еĽǱ���дΪ3���´���
	 * ������Ϣ��NullpointerException��...couldn't read row 0��col 3... ����ָ�룩
	 * 
	 * ��ʱ1���
	 */
	public void login() {
		// TODO Auto-generated method stub
		Intent intent1 = new Intent(person_login.this,MainActivity.class);
		InfoDao dao = new InfoDao(mcontext);
		Cursor cursor = null;
		
		String loginin_id=ps_ID.getText().toString().trim();
		String loginin_passw=ps_Passw.getText().toString().trim();
		
		if(TextUtils.isEmpty(loginin_id)||TextUtils.isEmpty(loginin_passw)){
			Toast.makeText(mcontext, "�û������벻��Ϊ��!!", 0).show();
			return;
		}
		//ȡ�����ݿ���id��Ӧ���������
		if(!TextUtils.isEmpty(loginin_id)){
			cursor = db.query(true, DB_TABLE, new String[]{"name","UserID","UserPassword"},
					"UserID="+"\""+loginin_id+"\"", null, null, null, null, null);
		}
		
		if(cursor==null){
			Toast.makeText(mcontext, "�����Ϊ��", 0).show();
			return;
		}
		//�ж�ȡ�������������û����������Ƿ�ƥ��
		if(cursor.getCount()==0){
			Toast.makeText(mcontext, "���û���δע�ᣡ����������...", 0).show();
			return;
		}else{
			if(cursor.moveToPosition(0)){
				if(cursor.getString(2).equals(loginin_passw)){
					Toast.makeText(mcontext, "�װ���"+cursor.getString(0)+"����ӭ����~��", 1).show();
					startActivity(intent1);
					finish();
					return;
				}else{
					Toast.makeText(mcontext, "��������������������룡", 0).show();
					return;
				}
			}
		}
			
	}
		
}
	


	
