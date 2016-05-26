package com.example._Travel;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.LittleThings.Beans;
import com.example.LittleThings.Traveler_Utils;
import com.example.LittleThings.Utils;
import com.example.realtext.R;

public class Tra_traveler extends Fragment{
	
	private Context mContext;
	private ListView lv_traveler;
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			 ViewGroup container,  Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tra_traveler_list,null);
		mContext=getContext();
		
		lv_traveler=(ListView) view.findViewById(R.id.lv_tra_traveler);
		
		ArrayList<Beans> travelers =Traveler_Utils.getTraveler(mContext);
		
		lv_traveler.setAdapter(new TravelerAdapter(travelers));
		
		
		return view;
	}
	
	class TravelerAdapter extends BaseAdapter {

		private ArrayList<Beans> traveler;
		private ImageView img_head;
		private TextView tv_name;
		private TextView tv_des;
		private ImageButton imgbt_location;
		private ImageButton imgbt_dialog;
		//创建一个构造方法，将要显示的新闻数据传进来
		public TravelerAdapter(ArrayList<Beans> traveler_temp){
			this.traveler=traveler_temp;
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return traveler.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return traveler.get(position);
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
				v=View.inflate(mContext, R.layout.lvitem_traveler, null);
			}
			img_head = (ImageView) v.findViewById(R.id.traitem_img_head);
			tv_name = (TextView) v.findViewById(R.id.traitem_tv_name);
			tv_des = (TextView) v.findViewById(R.id.traitem_tv_des);
			imgbt_location = (ImageButton) v.findViewById(R.id.traitem_imgbt_location);
			imgbt_dialog=(ImageButton) v.findViewById(R.id.traitem_img_dialog);
			
			Beans beans = traveler.get(position);
			
			img_head.setImageDrawable(beans.tra_head);
			tv_name.setText(beans.tra_name);
			tv_des.setText("		"+beans.tra_des);
			imgbt_dialog.setImageDrawable(getResources().getDrawable(R.drawable.tra_dialog));
			
			
			return v;
		}
	
	
	}
}
