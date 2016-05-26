package com.example.realtext;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.method.HideReturnsTransformationMethod;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.FragView.Frag_Map;
import com.example.FragView.Frag_Push;
import com.example.FragView.Frag_Story;
import com.example.FragView.Frag_Travel;
import com.example.LittleThings.MyFragmentPageAdapter;
import com.example.LittleThings.NoScrollViewPager;
import com.example._Travel.Tra_traveler;

public class MainActivity extends FragmentActivity {

	private Context mcontext;
	
	private AutoCompleteTextView mACView;
	private NoScrollViewPager mPager;
	private Button mZx_BT;
	private Button mPson_BT;
	private RadioGroup mRG;
	private RadioButton mMapRB;
	private RadioButton mTravelRB;
	private RadioButton mPushRB;
	private RadioButton mStoryRB;
	
	private ArrayList<Fragment> al_fragment;
	private Fragment frag_map,
	 				 frag_travel,
	 				 frag_push,
	 				 frag_story;
	
	
	private static Boolean isExit=false;
	static final int Tab_num = 4;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mcontext=this;
		initMainView();
	}
	
	 void initMainView(){
		
		mACView=(AutoCompleteTextView) findViewById(R.id.main_TopSearch);
		mPager=(com.example.LittleThings.NoScrollViewPager)findViewById(R.id.main_Frag_Body);
		mZx_BT=(Button) findViewById(R.id.main_TopButton_Zxing);
		mPson_BT=(Button) findViewById(R.id.main_TopButton_Person);
		
		mRG=(RadioGroup) findViewById(R.id.main_BottomGroup);
		mMapRB=(RadioButton) findViewById(R.id.main_L_Bottom);
		mTravelRB=(RadioButton) findViewById(R.id.main_LM_Button);
		mPushRB=(RadioButton) findViewById(R.id.main_RM_Buttom);
		mStoryRB=(RadioButton) findViewById(R.id.main_R_Buttom);
		
		mMapRB.setOnClickListener(new MyMainOnClickListener(0));
		mTravelRB.setOnClickListener(new MyMainOnClickListener(1));
		mPushRB.setOnClickListener(new MyMainOnClickListener(2));
		mStoryRB.setOnClickListener(new MyMainOnClickListener(3));
		
		al_fragment=new ArrayList<Fragment>();
		
		frag_map=new Frag_Map();
		frag_travel=new Frag_Travel();
		frag_push=new Frag_Push();
		frag_story=new Frag_Story();
		
		al_fragment.add(frag_map);
		al_fragment.add(frag_travel);
		al_fragment.add(frag_push);
		al_fragment.add(frag_story);
		
		mPager.setAdapter(new MyFragmentPageAdapter(getSupportFragmentManager(),al_fragment));
		mPager.setCurrentItem(0,true);
		
		mZx_BT.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivity(intent2);
			}
		});
		
		
		
		mPson_BT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent3 = new Intent(mcontext,PersonView.class);
				startActivity(intent3);
			}
		});
		
	}

	public class MyMainOnClickListener implements OnClickListener{
		private int index=0;
		public MyMainOnClickListener(int i){
			index=i;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(index,true);
		}
		
	}
	 
	@Override
	protected void onStart(){
		super.onStart();
		mRG.check(R.id.main_L_Bottom);
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	//实现双击返回键退出
	private void Exit_2Click(){
		if(isExit==false){
			isExit=true;	//准备退出
			Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
			Timer tExit=new Timer();	//开始计时
			tExit.schedule(new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					isExit=false;	//延迟后取消退出
				}
			}, 2000);//延迟2秒	
			/*schedule方法，在n秒后执行动作，
			或在什么时候，
			在时间段执行*/
		}
		else{
			finish();
			System.exit(0);
		}
	}
	
	public boolean onKeyDown(int keyCode,KeyEvent event){
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Exit_2Click();
		}
		return false;
	}
	
	
}

/*mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
	
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		//先隐藏所有fragment
		hideFragments(transaction);
		switch(checkedId){
			case R.id.main_L_Bottom:
				if(frag_map==null){
					frag_map=new Frag_Map();
					transaction.add(R.id.main_Frag_Body, frag_map);
				}
				transaction.show(frag_map);
				break;
			case R.id.main_LM_Button:
				if(frag_travel==null){
					frag_travel=new Frag_Travel();
					transaction.add(R.id.main_Frag_Body, frag_travel);
				}
				transaction.show(frag_travel);
				break;
			case R.id.main_RM_Buttom:
				if(frag_push==null){
					frag_push=new Frag_Push();
					transaction.add(R.id.main_Frag_Body, frag_push);
				}
				transaction.show(frag_push);
				break;
			case R.id.main_R_Buttom:
				if(frag_story==null){
					frag_story=new Frag_Story();
					transaction.add(R.id.main_Frag_Body, frag_story);
				}
				transaction.show(frag_story);
				break;
		}
		transaction.commitAllowingStateLoss();
	}

	private void hideFragments(FragmentTransaction transaction) {
		// TODO Auto-generated method stub
		transaction.hide(frag_map);
		transaction.hide(frag_travel);
		transaction.hide(frag_push);
		transaction.hide(frag_story);
	}
});
*/