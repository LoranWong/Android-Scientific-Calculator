package com.bao.mycalculator;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;
import bsh.EvalError;
import bsh.Interpreter;
/**
 * 该activity为科学计算，设计了基本的计算功能，以及一个ViewFlipper让用户选择更加高级的功能
 */
public class CalculatorActivity extends Activity {
	//该布尔值判断前一个数是否输入了实数（含小数点），若不是实数，为方便计算，则改为实数。
	private boolean isDouble=false;
	//该布尔值判断输入框内是否有加减乘除符号，用于高级功能的计算，如输入框内有30，再按sin，得到结果为0.5，如果是30+，则会报错
	private boolean haveSymbol=false;
	//用来计算的字符串，要求所有数字都是Double类型
	private String editTextAllDoubleString="";
	private Double result=0.0;
	private EditText editText=null;
	private ViewFlipper flipper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		//实例化各个按钮以及edittext
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
		Button btndel=(Button)findViewById(R.id.btn_del);
		Button btnplus=(Button)findViewById(R.id.btn_plus);
		Button btntimes=(Button)findViewById(R.id.btn_times);
		Button btndivide=(Button)findViewById(R.id.btn_divide);
		Button btnminus=(Button)findViewById(R.id.btn_minus);
		Button btnequals=(Button)findViewById(R.id.btn_equals);
		Button btndot=(Button)findViewById(R.id.btn_dot);
		Button btnsin=(Button)findViewById(R.id.btn_sin);
		Button btncos=(Button)findViewById(R.id.btn_cos);
		Button btntan=(Button)findViewById(R.id.btn_tan);
		Button btnarcsin=(Button)findViewById(R.id.btn_arcsin);
		Button btnarccos=(Button)findViewById(R.id.btn_arccos);
		Button btnarctan=(Button)findViewById(R.id.btn_arctan);
		Button btnsqr=(Button)findViewById(R.id.btn_sqr);
		Button btnPI=(Button)findViewById(R.id.btn_PI);
		Button btnsqrt=(Button)findViewById(R.id.btn_sqrt);
		Button btnE=(Button)findViewById(R.id.btn_E);
		Button btnlg=(Button)findViewById(R.id.btn_lg);
		Button btnln=(Button)findViewById(R.id.btn_ln);
		editText=(EditText)findViewById(R.id.et);

		
		//设置监听器
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
		btndot.setOnClickListener(numberListener);
		btnplus.setOnClickListener(numberListener);
		btnminus.setOnClickListener(numberListener);
		btntimes.setOnClickListener(numberListener);
		btndivide.setOnClickListener(numberListener);
		btnequals.setOnClickListener(numberListener);
		btnclean.setOnClickListener(numberListener);
		btndel.setOnClickListener(numberListener);
		btnPI.setOnClickListener(numberListener);
		btnE.setOnClickListener(numberListener);
		
		btnsin.setOnClickListener(advancedListener);
		btncos.setOnClickListener(advancedListener);
		btntan.setOnClickListener(advancedListener);
		btnarcsin.setOnClickListener(advancedListener);
		btnarccos.setOnClickListener(advancedListener);
		btnarctan.setOnClickListener(advancedListener);
		btnsqr.setOnClickListener(advancedListener);
		btnsqrt.setOnClickListener(advancedListener);
		btnlg.setOnClickListener(advancedListener);
		btnln.setOnClickListener(advancedListener);
		//以下代码实现viewflipper的翻页功能
		flipper = (ViewFlipper)findViewById(R.id.viewflipper_id);
	    Button button = (Button)findViewById(R.id.btn_viewfilpper);
	    //设置Flipper翻页的动态效果，这里给出进入和离开的两个效果,参考CSDN博文
        flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_in));
        flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.push_left_out));
	    button.setOnClickListener(new View.OnClickListener() {         
	        public void onClick(View arg0) {
	            //每次点击button，则ViewFlipper中的显示更换为下一个元素，如果已是最后的元素，从头开始
	            flipper.showNext();
	        }
	    });
	}
	/**
	 * 设置数字以及加减乘除按钮的监听器
	 */
	OnClickListener numberListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			//如果是数字或者符号则直接添加在字符串上
			case R.id.btn_0:
				input("0");
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
			case R.id.btn_plus:
				input("+");
				break;
			case R.id.btn_minus:
				input("-");
				break;
			case R.id.btn_times:
				input("*");
				break;
			case R.id.btn_divide:
				input("/");
				break;
			//如果是圆周率π
			case R.id.btn_PI:
				if(!isDouble) input("3.1415");
				isDouble=true;
				break;
			//如果是自然常数e
			case R.id.btn_E:
				if(!isDouble) input("2.7182");
				isDouble=true;
				break;
			//如果是等号则执行计算
			case R.id.btn_equals:
				if(editTextAllDoubleString!="") calculate();				
				break;
			//如果是小数点则添加小数点并改变isDouble的值
			case R.id.btn_dot:
				if(!isDouble){
					input(".");
					isDouble=true;
				}				
				break;
			//如果是清空按钮
			case R.id.btn_c:
				cleanEditText();
				break;
			//如果点击了回退按钮
			case R.id.btn_del:
				delEditText();
				break;
			default:
				break;
			}
			//输出信息方便调试
			Log.i("TAG", "haveSymbol---->"+haveSymbol);
			Log.i("TAG", "isDouble---->"+isDouble);
			Log.i("TAG", "editTextAllDoubleString---->"+editTextAllDoubleString);
		}
	};
	/**
	 * 输入了数字或计算符号的处理方法
	 * @param string 输入的数字或计算符号
	 */
	private void input(String string) {
		//如果输入了加减乘除号
		if(string=="+"||string=="-"||string=="*"||string=="/"){
			haveSymbol=true;
			//如果前一个数字不是实数
			if(!isDouble){
				//将前一个数字转换为实数,此处无须将isDouble置为true，因为此时已经正输入下一个数了。默认为false
				editTextAllDoubleString=editTextAllDoubleString+".0"+string;
				editText.setText(editTextAllDoubleString);
			}
			else{
				editTextAllDoubleString=editTextAllDoubleString+string;
				editText.setText(editTextAllDoubleString);
			}
			isDouble=false;
		}
		else{
			editTextAllDoubleString=editTextAllDoubleString+string;
			editText.setText(editTextAllDoubleString);
		}

	}
	/**
	 * 该函数用于回退输入框内的字符串
	 * 返回1表示回退符号键
	 * 返回2表示回退小数点
	 * 返回3表示为空，无需回退
	 * 返回4表示回退数字
	 */
	protected int delEditText() {
		//如果是运算符号
		if(editTextAllDoubleString.endsWith("+")||editTextAllDoubleString.endsWith("-")||editTextAllDoubleString.endsWith("*")||editTextAllDoubleString.endsWith("/"))
		{
			editTextAllDoubleString=editTextAllDoubleString.substring(0,editTextAllDoubleString.length()-1);
			if(editTextAllDoubleString.contains("+")||editTextAllDoubleString.contains("-")||editTextAllDoubleString.contains("*")||editTextAllDoubleString.contains("/")){
				haveSymbol=true;
			}else{
				haveSymbol=false;
			}
			//将结果显示出来
			editText.setText(editTextAllDoubleString);
			//前面一个数肯定是实数
			isDouble=true;
			return 1;

		}
		//如果是小数点
		else if(editTextAllDoubleString.endsWith(".")){
				editTextAllDoubleString=editTextAllDoubleString.substring(0,editTextAllDoubleString.length()-1);
				isDouble=false;
				//将结果显示出来
				editText.setText(editTextAllDoubleString);
				return 2;
			}
			else if(editTextAllDoubleString.length()==0){
				//do nothing
				Toast.makeText(CalculatorActivity.this, "都没有了，你还删什么...", Toast.LENGTH_SHORT).show();
				return 3;
				}
				//如果是普通数字
				else{
					editTextAllDoubleString=editTextAllDoubleString.substring(0,editTextAllDoubleString.length()-1);
					//将结果显示出来
					editText.setText(editTextAllDoubleString);
					return 4;
				}
				
	}
	/**
	 * 该函数用于清空输入框
	 */
	protected void cleanEditText() {
		editTextAllDoubleString="";
		haveSymbol=false;
		isDouble=false;
		editText.setText(editTextAllDoubleString);		
	}
	/**
	 * 计算字符串的值
	 */
	protected void calculate() {
		//如果前一个数字不是实数
		if(!isDouble){
			//将前一个数字转换为实数,此处无须将isDouble置为true，因为此时已经正输入下一个数了。默认为false
			editTextAllDoubleString=editTextAllDoubleString+".0";
			editText.setText(editTextAllDoubleString);
		}
		//此处计算采用了一个导入包，http://www.beanshell.org/bsh-core-2.0b4.jar 
		//类似javascript的eval方法，该方法可计算某个字符串,使用方法如下
		//生成一个翻译者
		Interpreter interpreter = new Interpreter();
		try {
			//解析并计算等式
			result = (Double) interpreter.eval(editTextAllDoubleString);
			setResultNumber(result);
		} catch (EvalError e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将计算结果显示到输入框
	 * @param result 要设置的字符串
	 */
	private void setResultNumber(Double result) {
		Log.i("TAG", "result---->"+result);
		//将Double类型的计算结果转换为字符串类型,保留四位小数
		DecimalFormat df=new DecimalFormat("#.####");
		editTextAllDoubleString=df.format(result);
		//如果结果不符合要求
		if(Double.isNaN(result)||result==Double.POSITIVE_INFINITY||result==Double.NEGATIVE_INFINITY){
			Toast.makeText(CalculatorActivity.this, "输入格式错误！结果无法显示！", Toast.LENGTH_SHORT).show();
			cleanEditText();
		}
		//如果结果可以显示
		else{
			editText.setText(editTextAllDoubleString);
			//此时判断输入框中的数是否含小数点，将isDouble的值重置
			haveSymbol=false;
			if(editTextAllDoubleString.contains(".")){
				isDouble=true;
			}else{
				isDouble=false;
			}
		}
		//打印计算结果,方便调试
		Log.i(getPackageName(), "editTextAllDoubleString--->"+editTextAllDoubleString);
		
	}

	/**
	 * 设置高级计算功能的按钮监听器
	 */
	OnClickListener advancedListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			//如果输入框内含有计算符号或为空则提示错误,并清空
			if(haveSymbol||editTextAllDoubleString==""){
				Toast.makeText(CalculatorActivity.this, "请先输入一个数字", Toast.LENGTH_SHORT).show();
				cleanEditText();
			}
			else{
				if(!isDouble){
					editTextAllDoubleString=editTextAllDoubleString+".0";
				}
				switch (v.getId()) {
				case R.id.btn_sin:
					result=Math.sin(Double.parseDouble(editTextAllDoubleString)*Math.PI/180);
					setResultNumber(result);
					break;
				case R.id.btn_cos:
					result=Math.cos(Double.parseDouble(editTextAllDoubleString)*Math.PI/180);
					setResultNumber(result);
					break;
				case R.id.btn_tan:
					result=Math.tan(Double.parseDouble(editTextAllDoubleString)*Math.PI/180);
					setResultNumber(result);
					break;
				case R.id.btn_arcsin:
					result=Math.asin(Double.parseDouble(editTextAllDoubleString))*180/Math.PI;
					setResultNumber(result);
					break;
				case R.id.btn_arccos:
					result=Math.acos(Double.parseDouble(editTextAllDoubleString))*180/Math.PI;
					setResultNumber(result);
					break;
				case R.id.btn_arctan:
					result=Math.tan(Double.parseDouble(editTextAllDoubleString))*180/Math.PI;
					setResultNumber(result);
					break;
				case R.id.btn_sqr:
					result=Math.pow(Double.parseDouble(editTextAllDoubleString), 2.0);
					setResultNumber(result);
					break;
				case R.id.btn_sqrt:
					result=Math.sqrt(Double.parseDouble(editTextAllDoubleString));
					setResultNumber(result);
					break;
				case R.id.btn_ln:
					result=Math.log(Double.parseDouble(editTextAllDoubleString));
					setResultNumber(result);
					break;
				case R.id.btn_lg:
					result=Math.log10(Double.parseDouble(editTextAllDoubleString));
					setResultNumber(result);
					break;

				default:
					break;
				}

			}
		}
	};
}
