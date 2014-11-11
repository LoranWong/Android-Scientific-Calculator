package com.bao.mycalculator;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

import com.bao.model.Model;
import com.bao.model.Model.Dangwei;
import com.bao.model.Model.Leixing;

public class UnitDeformationActivity extends Activity {
	private Leixing currentLeixing;
	private Dangwei currentDangwei;
	private EditText editText;
	private TextView tv;
	private String editTextString="";
	private Dangwei target;
	private SlidingDrawer drawer;
	private ImageView iv;
	
	private List<Dangwei> lengthdangweis=new ArrayList<Model.Dangwei>();
	private List<Dangwei> squaredangweis=new ArrayList<Model.Dangwei>();
	private List<Dangwei> massdangweis=new ArrayList<Model.Dangwei>();
	private List<Dangwei> moneydangweis=new ArrayList<Model.Dangwei>();
	
	private List<List<Dangwei>> leixings=new ArrayList<List<Dangwei>>();
	
	private Button btndangwei0;
	private Button btndangwei1;
	private Button btndangwei2;
	private Button btndangwei3;
	private Button btndangwei4;
	private Button btndangwei5;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_unitdenformation);
		leixings.add(lengthdangweis);
		leixings.add(squaredangweis);
		leixings.add(massdangweis);
		leixings.add(moneydangweis);
		
		lengthdangweis.add(Model.MI);
		lengthdangweis.add(Model.GONGLI);
		lengthdangweis.add(Model.CHI);
		lengthdangweis.add(Model.CUN);
		lengthdangweis.add(Model.LI);
		lengthdangweis.add(Model.YINGCHI);
		squaredangweis.add(Model.MI2);
		squaredangweis.add(Model.QIANMI2);
		squaredangweis.add(Model.MU);
		squaredangweis.add(Model.GONGMU);
		squaredangweis.add(Model.YINGMU);
		squaredangweis.add(Model.GONGQING);
		massdangweis.add(Model.QIANGKE);
		massdangweis.add(Model.KE);
		massdangweis.add(Model.BANG);
		massdangweis.add(Model.DUN);
		massdangweis.add(Model.JIN);
		massdangweis.add(Model.LIANG);
		moneydangweis.add(Model.CHN);
		moneydangweis.add(Model.USA);
		moneydangweis.add(Model.HK);
		moneydangweis.add(Model.TW);
		moneydangweis.add(Model.EUR);
		moneydangweis.add(Model.UK);
		
		//实例化Drawer对象，设置箭头朝向
		Button btnlength=(Button)findViewById(R.id.btn_to_LENGTH);
		Button btnmass=(Button)findViewById(R.id.btn_to_MASS);
		Button btnsquare=(Button)findViewById(R.id.btn_to_SQUARE);
		Button btnmoney=(Button)findViewById(R.id.btn_to_MONEY);
		
		//实例化各个按钮以及edittext,textview
		Button btn0=(Button)findViewById(R.id.btn_0);
		Button btn1=(Button)findViewById(R.id.btn_1);
		Button btn2=(Button)findViewById(R.id.btn_2);
		Button btn3=(Button)findViewById(R.id.btn_3);
		Button btn4=(Button)findViewById(R.id.btn_4);
		Button btn5=(Button)findViewById(R.id.btn_5);
		Button btn6=(Button)findViewById(R.id.btn_6);
		Button btn7=(Button)findViewById(R.id.btn_7);
		Button btn8=(Button)findViewById(R.id.btn_8);
		Button btn9=(Button)findViewById(R.id.btn_9);
		Button btnclean=(Button)findViewById(R.id.btn_c);
		
		btndangwei0=(Button)findViewById(R.id.btn_dangwei0);
		btndangwei1=(Button)findViewById(R.id.btn_dangwei1);
		btndangwei2=(Button)findViewById(R.id.btn_dangwei2);
		btndangwei3=(Button)findViewById(R.id.btn_dangwei3);
		btndangwei4=(Button)findViewById(R.id.btn_dangwei4);
		btndangwei5=(Button)findViewById(R.id.btn_dangwei5);		
		editText=(EditText)findViewById(R.id.et);
		tv=(TextView)findViewById(R.id.tv);
		drawer=(SlidingDrawer)findViewById(R.id.drawer);
		iv=(ImageView)findViewById(R.id.slidingdrawer_handle);
		

		btn0.setOnClickListener(numberListener);
		btn1.setOnClickListener(numberListener);
		btn2.setOnClickListener(numberListener);
		btn3.setOnClickListener(numberListener);
		btn4.setOnClickListener(numberListener);
		btn5.setOnClickListener(numberListener);
		btn6.setOnClickListener(numberListener);
		btn7.setOnClickListener(numberListener);
		btn8.setOnClickListener(numberListener);
		btn9.setOnClickListener(numberListener);
		btnclean.setOnClickListener(numberListener);
		btndangwei0.setOnClickListener(numberListener);
		btndangwei1.setOnClickListener(numberListener);
		btndangwei2.setOnClickListener(numberListener);
		btndangwei3.setOnClickListener(numberListener);
		btndangwei4.setOnClickListener(numberListener);
		btndangwei5.setOnClickListener(numberListener);
		
		btnlength.setOnClickListener(changeListener);
		btnmass.setOnClickListener(changeListener);
		btnmoney.setOnClickListener(changeListener);
		btnsquare.setOnClickListener(changeListener);
		
		//开始默认打开抽屉,设置抽屉透明度,打开时透明度0，关闭时透明度80
		drawer.animateOpen();
		drawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {			
			@Override
			public void onDrawerOpened() {
				iv.setAlpha(255);
			}
		});
		drawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {			
			@Override
			public void onDrawerClosed() {
				iv.setAlpha(30);
			}
		});
	}
	/**
	 * 改变当前单位类型的监听器
	 */
	OnClickListener changeListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_to_LENGTH:
				change(Model.LENGTH);
				break;
			case R.id.btn_to_MASS:
				change(Model.MASS);
				break;
			case R.id.btn_to_SQUARE:
				change(Model.SQUARE);
				break;
			case R.id.btn_to_MONEY:
				change(Model.MONEY);
				break;
			default:
				break;
			}
		}
	};
	/**
	 * 设置按钮的监听器
	 */
	OnClickListener numberListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_0:
				//首位不为0
				if(editTextString!="") input("0");
				break;
			case R.id.btn_1:
				input("1");
				break;
			case R.id.btn_2:
				input("2");
				break;
			case R.id.btn_3:
				input("3");
				break;
			case R.id.btn_4:
				input("4");
				break;
			case R.id.btn_5:
				input("5");
				break;
			case R.id.btn_6:
				input("6");
				break;
			case R.id.btn_7:
				input("7");
				break;
			case R.id.btn_8:
				input("8");
				break;
			case R.id.btn_9:
				input("9");
				break;
			//如果是清空按钮
			case R.id.btn_c:
				clean();
				break;
			case R.id.btn_dangwei0:
				target=leixings.get(currentLeixing.index).get(0);
				conversion(target);
				break;
			case R.id.btn_dangwei1:
				target=leixings.get(currentLeixing.index).get(1);
				conversion(target);
				break;
			case R.id.btn_dangwei2:
				target=leixings.get(currentLeixing.index).get(2);
				conversion(target);
				break;
			case R.id.btn_dangwei3:
				target=leixings.get(currentLeixing.index).get(3);
				conversion(target);
				break;
			case R.id.btn_dangwei4:
				target=leixings.get(currentLeixing.index).get(4);
				conversion(target);
				break;
			case R.id.btn_dangwei5:
				target=leixings.get(currentLeixing.index).get(5);
				conversion(target);
				break;
			default:
				break;
			}
		}
	};
/**
 * 输入数字，更新显示
 * @param string 输入的数字
 */
	protected void input(String string) {
		editTextString=editTextString+string;
		editText.setText(editTextString);
		if(editTextString.length()>10){
			Toast.makeText(UnitDeformationActivity.this, "数字别弄太大了...", Toast.LENGTH_SHORT).show();
			clean();
		}
	}
/**
 * 改变当前的单位类型，初始化按钮上的文本	
 */
	protected void change(Leixing leixing) {
		currentLeixing=leixing;
		currentDangwei=currentLeixing.dangwei;
		btndangwei0.setText(leixings.get(currentLeixing.index).get(0).name);
		btndangwei1.setText(leixings.get(currentLeixing.index).get(1).name);
		btndangwei2.setText(leixings.get(currentLeixing.index).get(2).name);
		btndangwei3.setText(leixings.get(currentLeixing.index).get(3).name);
		btndangwei4.setText(leixings.get(currentLeixing.index).get(4).name);
		btndangwei5.setText(leixings.get(currentLeixing.index).get(5).name);
		tv.setText(currentLeixing.name+":"+currentDangwei.name);
		//关闭抽屉
		drawer.animateClose();		
	}
/**
 * 清空显示
 */
	protected void clean() {
		editTextString="";
		editText.setText(editTextString);
	}
/**
 * 转换单位  如果转换成功返回1  无需转换返回0
 * @param target 目标单位
 */
	protected int conversion(Dangwei target) {
		if(currentDangwei==target) return 0;
		//如果当前输入框不为空，为空时跳过以下代码，直接更新当前单位
		if(editTextString!=""){
			//根据等比关系来计算
			editTextString=String.valueOf(Double.parseDouble(editTextString)*target.value/currentDangwei.value);
			editText.setText(editTextString);
		}
		//更新当前单位
		currentDangwei=target;
		tv.setText(currentLeixing.name+":"+currentDangwei.name);
		return 1;
	} //转换函数结束
}
