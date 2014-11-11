package com.bao.mycalculator;

import com.bao.model.Model.Current;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 该activity为进制转换
 */
public class NumberConversionActivity extends Activity {
	//用来显示的字符串
	private String editTextString="";
	private EditText editText=null;
	private TextView tv=null;
	//当前的进制，利用了com.bao.model.Model.Current定义好的枚举类
	private Current currentSystem=Current.TEN;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numberconversion);
		//实例化各个按钮以及edittext,textview
		Button btnA=(Button)findViewById(R.id.btn_A);
		Button btnB=(Button)findViewById(R.id.btn_B);
		Button btnC=(Button)findViewById(R.id.btn_C);
		Button btnD=(Button)findViewById(R.id.btn_D);
		Button btnE=(Button)findViewById(R.id.btn_E);
		Button btnF=(Button)findViewById(R.id.btn_F);
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
		Button btntwo=(Button)findViewById(R.id.btn_two);
		Button btneight=(Button)findViewById(R.id.btn_eight);
		Button btnten=(Button)findViewById(R.id.btn_ten);
		Button btnsixteen=(Button)findViewById(R.id.btn_sixteen);
		editText=(EditText)findViewById(R.id.et);
		tv=(TextView)findViewById(R.id.tv);
		
		//为各个按钮设置监听器
		//设置监听器
		btnA.setOnClickListener(numberListener);
		btnB.setOnClickListener(numberListener);
		btnC.setOnClickListener(numberListener);
		btnD.setOnClickListener(numberListener);
		btnE.setOnClickListener(numberListener);
		btnF.setOnClickListener(numberListener);
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
		btneight.setOnClickListener(numberListener);
		btnten.setOnClickListener(numberListener);
		btntwo.setOnClickListener(numberListener);
		btnsixteen.setOnClickListener(numberListener);	
	}
	/**
	 * 设置所有按钮的监听器
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
				if(currentSystem!=Current.TWO) input("2");
				break;
			case R.id.btn_3:
				if(currentSystem!=Current.TWO) input("3");
				break;
			case R.id.btn_4:
				if(currentSystem!=Current.TWO) input("4");
				break;
			case R.id.btn_5:
				if(currentSystem!=Current.TWO) input("5");
				break;
			case R.id.btn_6:
				if(currentSystem!=Current.TWO) input("6");
				break;
			case R.id.btn_7:
				if(currentSystem!=Current.TWO) input("7");
				break;
			case R.id.btn_8:
				if(currentSystem!=Current.TWO&&currentSystem!=Current.EIGHT) input("8");
				break;
			case R.id.btn_9:
				if(currentSystem!=Current.TWO&&currentSystem!=Current.EIGHT) input("9");
				break;
			case R.id.btn_A:
				if(currentSystem==Current.SIXTEEN) input("A");
				break;
			case R.id.btn_B:
				if(currentSystem==Current.SIXTEEN) input("B");
				break;
			case R.id.btn_C:
				if(currentSystem==Current.SIXTEEN) input("C");
				break;
			case R.id.btn_D:
				if(currentSystem==Current.SIXTEEN) input("D");
				break;
			case R.id.btn_E:
				if(currentSystem==Current.SIXTEEN) input("E");
				break;
			case R.id.btn_F:
				if(currentSystem==Current.SIXTEEN) input("F");
				break;
			//如果是清空按钮
			case R.id.btn_c:
				editTextString="";
				editText.setText(editTextString);
				break;
			//如果是进制转换的按钮
			case R.id.btn_two:
				conversion(Current.TWO);
				break;
			case R.id.btn_eight:
				conversion(Current.EIGHT);
				break;
			case R.id.btn_ten:
				conversion(Current.TEN);
				break;
			case R.id.btn_sixteen:
				conversion(Current.SIXTEEN);
				break;
			default:
				break;
			}
			if(
			((currentSystem==Current.SIXTEEN)&&editTextString.length()>6)||
			((currentSystem==Current.TEN)&&editTextString.length()>7)||
			((currentSystem==Current.EIGHT)&&editTextString.length()>8)||
			((currentSystem==Current.TWO)&&editTextString.length()>24)
			){
				Toast.makeText(NumberConversionActivity.this, "数字别弄太大了...", Toast.LENGTH_SHORT).show();
				editTextString="";
				editText.setText(editTextString);
			}
			//输出信息方便调试
			Log.i("TAG", "editTextString---->"+editTextString);
			Log.i("TAG", "currentSystem---->"+currentSystem);
		}
	};
/**
 * 输入数字，更新显示
 * @param string 输入的数字
 */
	protected void input(String string) {
		editTextString=editTextString+string;
		editText.setText(editTextString);
	}
/**
 * 转换进制  如果转换成功返回1  无需转换返回0
 * @param string 目标进制
 */
	protected int conversion(Current target) {
		Log.i("TAG", currentSystem+"转换成"+target);
		if(target==currentSystem) return 0;
		//如果当前输入框不为空，为空时跳过以下代码，直接更新当前进制
		if(editTextString!=""){
			//根据当前进制和目标进制分类
			//如果当前是十进制，直接调用toBinaryString toOctalString toHexString等方法
			if(currentSystem==Current.TEN){
				switch (target) {
				case TWO:
					editTextString= Integer.toBinaryString(Integer.parseInt(editTextString));
					editText.setText(editTextString);
					break;
				case EIGHT:
					editTextString=Integer.toOctalString(Integer.parseInt(editTextString));
					editText.setText(editTextString);
					break;
				case SIXTEEN:
					editTextString=Integer.toHexString(Integer.parseInt(editTextString));
					editText.setText(editTextString);
					break;
				default:
					break;
				}
			}
			//如果当前不是十进制，根据当前进制用parseInt转换成十进制后再转换成目标进制
			else{
				switch (target) {
				case TWO:
					editTextString= Integer.toBinaryString(Integer.parseInt(editTextString,currentSystem.value));
					editText.setText(editTextString);
					break;
				case EIGHT:
					editTextString=Integer.toOctalString(Integer.parseInt(editTextString, currentSystem.value));
					editText.setText(editTextString);
					break;
				case TEN:
					editTextString=Integer.valueOf(editTextString,currentSystem.value).toString();
					editText.setText(editTextString);
					break;
				case SIXTEEN:
					editTextString=Integer.toHexString(Integer.parseInt(editTextString, currentSystem.value));
					editText.setText(editTextString);
					break;
				default:
					break;
				}
			}
		}
		//更新当前进制
		currentSystem=target;
		switch (target) {
		case TWO:
			tv.setText("二进制");
			break;
		case EIGHT:
			tv.setText("八进制");
			break;
		case TEN:
			tv.setText("十进制");
			break;
		case SIXTEEN:
			tv.setText("十六进制");
			break;
		default:
			break;
		}
		return 1;
	} //转换函数结束
}

