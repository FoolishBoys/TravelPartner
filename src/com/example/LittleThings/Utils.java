package com.example.LittleThings;

import java.util.ArrayList;

import com.example.realtext.R;

import android.content.Context;

public class Utils {
	public static ArrayList<Beans> getGuiders(Context context){
		ArrayList<Beans> arraylist = new ArrayList<Beans>();
		
		Beans bean=new Beans();
		bean.head=context.getResources().getDrawable(R.drawable.u1);
		bean.name="��С��_M��";
		bean.des="�����һö���Ͻ�����~~";
		bean.likenum="55";
		bean.location="�人�к�ɽ���人����ѧԺ";
		bean.likeindex=0;
		arraylist.add(bean);
		
		Beans bean2=new Beans();
		bean2.head=context.getResources().getDrawable(R.drawable.u2);
		bean2.name="��С��ר��";
		bean2.des="���ķ��񣬴���װ�ƣ�����ɣ�";
		bean2.likenum="22";
		bean2.location="�����к�����������ͨ��ѧ";
		bean2.likeindex=0;
		arraylist.add(bean2);
		
		Beans bean3=new Beans();
		bean3.head=context.getResources().getDrawable(R.drawable.u3);
		bean3.name="yyxm";
		bean3.des="����Ա���ݶ����£���";
		bean3.likenum="999";
		bean3.location="��ݸ���³����㶫ҽѧԺ";
		bean3.likeindex=0;
		arraylist.add(bean3);
		
		Beans bean4=new Beans();
		bean4.head=context.getResources().getDrawable(R.drawable.u4);
		bean4.name="СV";
		bean4.des="������˾�������԰��Ȱ�������";
		bean4.likenum="666";
		bean4.location="�����е������������";
		bean4.likeindex=0;
		arraylist.add(bean4);
		
		Beans bean5=new Beans();
		bean5.head=context.getResources().getDrawable(R.drawable.u5);
		bean5.name="�һ����Ĵ�";
		bean5.des="��Ҫֻ���°�����˼,���쵽����ʱ��,��Ը����һ���価��������";
		bean5.likenum="111";
		bean5.location="̩���У�����";
		bean5.likeindex=0;
		arraylist.add(bean5);
		
		Beans bean6=new Beans();
		bean6.head=context.getResources().getDrawable(R.drawable.u6);
		bean6.name="���̨";
		bean6.des="��������С���ӣ��й������̱꣬�����죬��׼����С����~";
		bean6.likenum="2";
		bean6.location="�����ж���԰";
		bean6.likeindex=0;
		arraylist.add(bean6);
		
		
		
		return arraylist;
		
		
		
	}
	
	
	
}
