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
	 * 点击注册到数据库
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
	
	/**2016/4/14 00:23点击注册方法
	 * 
	 * 获取字符串
	 * 创建一个Userinfo类
	 * 判断信息是否为空
	 * 判断勾选条款是否为空
	 * 
	 * !!!判断是否注册过此号码
	 * 
	 * 将信息赋值，并存储
	 * 每次成功注册，id++
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
			Toast.makeText(mcontext, "注册信息不能为空！！", 0).show();
			return;
		}
		if(ckBox.isChecked()){
			InfoDao infoDao = new InfoDao(mcontext);
			
			userinfo.Name=signup_userName;
			userinfo.UserID=signup_userID;
			userinfo.UserPassword=signup_userPassw;
			/**2016/4/27
			 * 注册是否重名检测！
			 */
			cursor = db.query(true, DB_TABLE, new String[]{"_id","name","UserID"},
					"name="+"\""+signup_userName+"\"", null, null, null, null, null);
			cursor2 = db.query(true, DB_TABLE, new String[]{"_id","name","UserID"},
					"UserID="+"\""+signup_userID+"\"", null, null, null, null, null);
			
			if(cursor.getCount()!=0){
				Toast.makeText(mcontext, "该昵称已被注册，请重新输入...", 0).show();
				return;
			}else if(cursor2.getCount()!=0){
				Toast.makeText(mcontext, "该账号已被注册，请重新输入...", 0).show();
				return;
			}else{
				/**2016/4/27	注册优化
				 * Question：
				 * 针对触发器每次程序开始时，默认变回1，使得数据库存数据要注册失败（“_id”）次，才会存进数据
				 * 
				 * Solution：
				 * 将表倒叙排列，用limit取出头一行（使用desc 倒叙，asc顺序）
				 * 让触发器在注册之前达到_id的最末尾
				 * 即获取此时数据库_id最大值+1，赋予给触发器i
				 */
				cursor3=db.query(true, DB_TABLE, new String[]{"_id"}, null,
								null, null, null, "_id desc","1");
					if(cursor3.moveToPosition(0)){
						i=Integer.parseInt(cursor3.getString(0))+1;
						
						userinfo.id=i;
						
						boolean result = infoDao.Dinsert(userinfo);
						i++;
						if(result){
							Toast.makeText(mcontext, "注册成功", 0).show();
							finish();
						}else{
							Toast.makeText(mcontext, "注册失败", 0).show();
						}
					}
					
				
			}
		}else{
			Toast.makeText(mcontext,"无法注册，请勾选同意条款！", 0).show();
			return;
	}
		
		
		
		
	}
	
}
