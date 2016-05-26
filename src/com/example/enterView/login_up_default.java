package com.example.enterView;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.realtext.R;
import com.example.sqlDataBases.InfoDao;
import com.example.sqlDataBases.OpenHelper;
import com.example.sqlDataBases.bean.Userinfo;

public class login_up_default extends Activity implements OnClickListener{
	
	private Context mcontext;
	private ImageButton img_headreturn;
	private EditText userName;
	private EditText userID;
	private EditText userPassw;
	private CheckBox ckBox;
	private Button bt_finish;
	
	private static final String DB_FILE = "UserInfo.db",
	 		DB_TABLE = "UserInfo";
	private SQLiteDatabase db;
	
	
	private int i=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.login_up);
		mcontext=this;
		
		
		init();
	}

	public void init() {
		// TODO Auto-generated method stub
		
		userName = (EditText) findViewById(R.id.loginup_input_userName);
		userID=(EditText) findViewById(R.id.loginup_input_userID);
		userPassw=(EditText) findViewById(R.id.loginup_input_userPassw);
		img_headreturn=(ImageButton) findViewById(R.id.signUp_headreturn);
		ckBox=(CheckBox) findViewById(R.id.signUp_ck);
		bt_finish=(Button) findViewById(R.id.signUp_finish);
		
		OpenHelper openHelper = new OpenHelper(getApplicationContext(), DB_FILE, null, 1);
		db=openHelper.getReadableDatabase();
		
		
		bt_finish.setOnClickListener(this);
		img_headreturn.setOnClickListener(this);
		
	}
	
	
	
	/**2016/4/12 19:00
	 * ���ע�ᵽ���ݿ�
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Userinfo userinfo = new Userinfo();
		switch(v.getId()){
			case R.id.signUp_finish:
				ClickToSignup(userinfo);
				break;
			case R.id.signUp_headreturn:
				finish();
				break;
		}
		
		
	}
	
	/**2016/4/14 00:23���ע�᷽��
	 * 
	 * ��ȡ�ַ���
	 * ����һ��Userinfo��
	 * �ж���Ϣ�Ƿ�Ϊ��
	 * �жϹ�ѡ�����Ƿ�Ϊ��
	 * 
	 * !!!�ж��Ƿ�ע����˺���
	 * 
	 * ����Ϣ��ֵ�����洢
	 * ÿ�γɹ�ע�ᣬid++
	 * 
	 * 
	 */
	public void ClickToSignup(Userinfo userinfo){
		String signup_userName=userName.getText().toString().trim();
		String signup_userID=userID.getText().toString().trim();
		String signup_userPassw = userPassw.getText().toString().trim();
		
		Cursor cursor=null
				,cursor2=null
				,cursor3=null;
		
		if(TextUtils.isEmpty(signup_userName)||TextUtils.isEmpty(signup_userID)||TextUtils.isEmpty(signup_userPassw)){
			Toast.makeText(mcontext, "ע����Ϣ����Ϊ�գ���", 0).show();
			return;
		}
		if(ckBox.isChecked()){
			InfoDao infoDao = new InfoDao(mcontext);
			
			userinfo.Name=signup_userName;
			userinfo.UserID=signup_userID;
			userinfo.UserPassword=signup_userPassw;
			/**2016/4/27
			 * ע���Ƿ�������⣡
			 */
			cursor = db.query(true, DB_TABLE, new String[]{"_id","name","UserID"},
					"name="+"\""+signup_userName+"\"", null, null, null, null, null);
			cursor2 = db.query(true, DB_TABLE, new String[]{"_id","name","UserID"},
					"UserID="+"\""+signup_userID+"\"", null, null, null, null, null);
			
			if(cursor.getCount()!=0){
				Toast.makeText(mcontext, "���ǳ��ѱ�ע�ᣬ����������...", 0).show();
				return;
			}else if(cursor2.getCount()!=0){
				Toast.makeText(mcontext, "���˺��ѱ�ע�ᣬ����������...", 0).show();
				return;
			}else{
				/**2016/4/27	ע���Ż�
				 * Question��
				 * ��Դ�����ÿ�γ���ʼʱ��Ĭ�ϱ��1��ʹ�����ݿ������Ҫע��ʧ�ܣ���_id�����Σ��Ż�������
				 * 
				 * Solution��
				 * ���������У���limitȡ��ͷһ�У�ʹ��desc ����asc˳��
				 * �ô�������ע��֮ǰ�ﵽ_id����ĩβ
				 * ����ȡ��ʱ���ݿ�_id���ֵ+1�������������i
				 */
				cursor3=db.query(true, DB_TABLE, new String[]{"_id"}, null,
								null, null, null, "_id desc","1");
					if(cursor3.moveToPosition(0)){
						i=Integer.parseInt(cursor3.getString(0))+1;
						
						userinfo.id=i;
						
						boolean result = infoDao.Dinsert(userinfo);
						i++;
						if(result){
							Toast.makeText(mcontext, "ע��ɹ�", 0).show();
							finish();
						}else{
							Toast.makeText(mcontext, "ע��ʧ��", 0).show();
						}
					}
					
				
			}
		}else{
			Toast.makeText(mcontext,"�޷�ע�ᣬ�빴ѡͬ�����", 0).show();
			return;
	}
		
		
		
		
	}
	
}
