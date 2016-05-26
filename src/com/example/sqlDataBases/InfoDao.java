package com.example.sqlDataBases;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.example.sqlDataBases.bean.Userinfo;

public class InfoDao {

	private OpenHelper helper;
	private static final String DB_FILE = "UserInfo.db",
						 		DB_TABLE = "UserInfo";
	
	public InfoDao(Context context){
		//2016/4/13������һ�����ݿ�
		helper = new OpenHelper(context, DB_FILE, null, 1);
		
	}
	
	public boolean Dinsert(Userinfo userinfo){
		//ͨ������������һ�����ݿ��������
		SQLiteDatabase idb = helper.getReadableDatabase();
		
		//values��Ҫ��ӵ��ֶ�
		//ContentValues()�ڲ���װ��һ��map�������Ҫ��ӵ��ֶ���Ϣ
		ContentValues values=new ContentValues();
		values.put("_id",userinfo.id);
		values.put("name", userinfo.Name);
		values.put("UserID", userinfo.UserID);
		values.put("UserPassword", userinfo.UserPassword);
		long result = idb.insert(DB_TABLE, null, values);
		idb.close();
		
		if(result==-1){
			return false;
		}else{
			return true;
		}

	}
	
	public long Ddelete(){
		return 0;
		
	}
	
	public long Dupdate(){
		return 0;
	}
	
	/**2016/4/14 01:20 ��ѯ
	 * 
	 * 
	 */
	/*public boolean Dquery(){
		
		ArrayList<Userinfo> lists=new ArrayList<Userinfo>();
		
		SQLiteDatabase qdb = helper.getReadableDatabase();
		
		Cursor cursor=null;
		
		if(TextUtils.isEmpty(str)){
		
		}
		
		
				qdb.query(DB_TABLE, null, "UserID = ?", 
				new String[]{queryUserID},null, null, "UserID asc");
		
		if(cursor!=null&&cursor.getCount()>0){
			//�ж�cursor��û�е�������Ľ�β��û�е���һֱѭ��
			while(cursor.moveToNext()){
				//ͨ���е�������ȡһ�е�����
				int id=cursor.getInt(0);
				String name=cursor.getString(1);
				String userid=cursor.getString(2);
				String userpassw=cursor.getString(3);
				java.lang.System.out.println(id+":"+name+":"+userid+":"+userpassw);
				Userinfo userinfo=new Userinfo();
				userinfo.id=id;
				userinfo.Name=name;
				userinfo.UserID=userid;
				userinfo.UserPassword=userpassw;
				
				lists.add(userinfo);
			}
			
			
		}
		
		return true;
	}
	*/

}
