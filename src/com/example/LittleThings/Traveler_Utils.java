package com.example.LittleThings;

import java.util.ArrayList;

import android.content.Context;

import com.example.realtext.R;

public class Traveler_Utils {
	public static ArrayList<Beans> getTraveler(Context context){
		
		ArrayList<Beans> arraylist = new ArrayList<Beans>();
		
		Beans bean1=new Beans();
		bean1.head=context.getResources().getDrawable(R.drawable.tra1);
		bean1.name="yyxm";
		bean1.des="带你吃遍广州都不怕！！";
		bean1.location="东莞市新城区广东医学院";
		arraylist.add(bean1);
		
		Beans bean2=new Beans();
		bean2.head=context.getResources().getDrawable(R.drawable.tra2);
		bean2.name="小V";
		bean2.des="荆楚老司机，包吃包喝包开车！";
		bean2.location="荆州市的犄角旮旯里？？？";
		arraylist.add(bean2);
		
		Beans bean3 = new Beans();
		bean3.tra_head=context.getResources().getDrawable(R.drawable.tra3);
		bean3.name="笨小孩_M猴";
		bean3.des="资深导游一枚，赶紧带走~~";
		bean3.location="武汉市洪山区武汉体育学院";
		arraylist.add(bean3);
		
		Beans bean4=new Beans();
		bean4.head=context.getResources().getDrawable(R.drawable.tra4);
		bean4.name="笨小孩专属";
		bean4.des="贴心服务，带你装逼，带你飞！";
		bean4.location="北京市海淀区北京交通大学";
		arraylist.add(bean4);
		
		
		Beans bean5=new Beans();
		bean5.head=context.getResources().getDrawable(R.drawable.tra5);
		bean5.name="我怀恋的传";
		bean5.des="我要只留下爱和深思,秋天到来的时候,我愿意是一棵落尽繁花的树";
		bean5.location="泰安市？？？";
		arraylist.add(bean5);
		
		Beans bean6=new Beans();
		bean6.head=context.getResources().getDrawable(R.drawable.tra6);
		bean6.name="亦非台";
		bean6.des="重庆麻辣小王子，中国驰名商标，来重庆，认准麻辣小王子~";
		bean6.location="重庆市动物园";
		arraylist.add(bean6);
		
		return arraylist;
		
	}
	
	
}
