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
		bean1.des="����Ա���ݶ����£���";
		bean1.location="��ݸ���³����㶫ҽѧԺ";
		arraylist.add(bean1);
		
		Beans bean2=new Beans();
		bean2.head=context.getResources().getDrawable(R.drawable.tra2);
		bean2.name="СV";
		bean2.des="������˾�������԰��Ȱ�������";
		bean2.location="�����е������������";
		arraylist.add(bean2);
		
		Beans bean3 = new Beans();
		bean3.tra_head=context.getResources().getDrawable(R.drawable.tra3);
		bean3.name="��С��_M��";
		bean3.des="�����һö���Ͻ�����~~";
		bean3.location="�人�к�ɽ���人����ѧԺ";
		arraylist.add(bean3);
		
		Beans bean4=new Beans();
		bean4.head=context.getResources().getDrawable(R.drawable.tra4);
		bean4.name="��С��ר��";
		bean4.des="���ķ��񣬴���װ�ƣ�����ɣ�";
		bean4.location="�����к�����������ͨ��ѧ";
		arraylist.add(bean4);
		
		
		Beans bean5=new Beans();
		bean5.head=context.getResources().getDrawable(R.drawable.tra5);
		bean5.name="�һ����Ĵ�";
		bean5.des="��Ҫֻ���°�����˼,���쵽����ʱ��,��Ը����һ���価��������";
		bean5.location="̩���У�����";
		arraylist.add(bean5);
		
		Beans bean6=new Beans();
		bean6.head=context.getResources().getDrawable(R.drawable.tra6);
		bean6.name="���̨";
		bean6.des="��������С���ӣ��й������̱꣬�����죬��׼����С����~";
		bean6.location="�����ж���԰";
		arraylist.add(bean6);
		
		return arraylist;
		
	}
	
	
}
