package com.example._Travel;


import java.util.ArrayList;
import java.util.Random;

import com.example.LittleThings.Beans;
import com.example.LittleThings.Utils;
import com.example.realtext.PersonView;
import com.example.realtext.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Tra_guide extends Fragment implements OnItemClickListener{
	
	private Context mContext;
	private ListView lv_guider;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tra_guide, null);
		mContext =getContext();
		
		lv_guider=(ListView) view.findViewById(R.id.lv_tra_guide);
		ArrayList<Beans> guiders = Utils.getGuiders(mContext);
		
		lv_guider.setAdapter(new GuidersAdapter(guiders));
		//
		lv_guider.setOnItemClickListener(this);
		
		
		return view;
	}

	class GuidersAdapter extends BaseAdapter {

		private ArrayList<Beans> guiders;
		private ImageView img_head;
		private TextView tv_name;
		private TextView tv_des;
		private ImageView img_like;
		private TextView tv_likenum;
		private ImageButton imgbt_location;
		//创建一个构造方法，将要显示的新闻数据传进来
		public GuidersAdapter(ArrayList<Beans> guide_temp){
			this.guiders=guide_temp;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return guiders.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return guiders.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View v=null;
			//复用
			if(convertView!=null){
				v=convertView;
			}else{
				v=View.inflate(mContext, R.layout.lvitem_guider, null);
			}
			img_head = (ImageView) v.findViewById(R.id.item_img_head);
			tv_name = (TextView) v.findViewById(R.id.item_tv_name);
			tv_des = (TextView) v.findViewById(R.id.item_tv_des);
			img_like=(ImageView) v.findViewById(R.id.item_img_like);
			tv_likenum = (TextView) v.findViewById(R.id.item_tv_likenum);
			imgbt_location = (ImageButton) v.findViewById(R.id.item_imgbt_location);
			
			Beans beans = guiders.get(position);
			
			img_head.setImageDrawable(beans.head);
			tv_name.setText(beans.name);
			tv_des.setText("		"+beans.des);
			tv_likenum.setText(beans.likenum);
			tv_likenum.setTextColor(Color.BLACK);
			img_like.setImageDrawable(getResources().getDrawable(R.drawable.love_unchange));
			
			
			
			
			return v;
		}
		


	}

	/**
	 *  AdapterView<?> parent:
	 *  View view:
	 *  int position:
	 *  long id:
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
/*		Intent intent=null;
		switch (arg2) {
		case 0:
			intent=new Intent(mContext,PersonView.class);
			startActivity(intent);
			break;

		default:
			break;
		}*/
		
		
	}
	
	
}
