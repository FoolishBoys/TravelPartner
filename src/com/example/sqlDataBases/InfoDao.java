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
		//2016/4/13创建打开一个数据库
		helper = new OpenHelper(context, DB_FILE, null, 1);
		
	}
	
	public boolean Dinsert(Userinfo userinfo){
		//通过帮助对象获得一个数据库操作对象
		SQLiteDatabase idb = helper.getReadableDatabase();
		
		//values：要添加的字段
		//ContentValues()内部封装了一个map用来存放要添加的字段信息
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
	
	/**2016/4/14 01:20 查询
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
			//判断cursor有没有到结果集的结尾，没有到就一直循环
			while(cursor.moveToNext()){
				//通过列的索引获取一行的数据
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
