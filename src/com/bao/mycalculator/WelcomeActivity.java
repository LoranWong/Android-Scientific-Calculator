
package com.bao.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
/**
 * 该activity为欢迎界面，设计了四个button让用户选择具体的功能
 */
public class WelcomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Button btntocalculator=(Button)findViewById(R.id.btn_to_calculator);
		Button btntounitdeformation=(Button)findViewById(R.id.btn_to_unitdeformation);
		Button btntonumberconversion=(Button)findViewById(R.id.btn_to_numberconversion);
		Button btntodatecalctlator=(Button)findViewById(R.id.btn_to_datecalctlator);
		btntocalculator.setOnClickListener(listener);
		btntodatecalctlator.setOnClickListener(listener);
		btntonumberconversion.setOnClickListener(listener);
		btntounitdeformation.setOnClickListener(listener);
	}
	
	OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_to_calculator:
				Intent intent1=new Intent(WelcomeActivity.this,CalculatorActivity.class);
				startActivity(intent1);
				break;
			case R.id.btn_to_unitdeformation:
				Intent intent2=new Intent(WelcomeActivity.this,UnitDeformationActivity.class);
				startActivity(intent2);				
				break;
			case R.id.btn_to_numberconversion:
				Intent intent3=new Intent(WelcomeActivity.this,NumberConversionActivity.class);
				startActivity(intent3);
				break;
			case R.id.btn_to_datecalctlator:
				Intent intent4=new Intent(WelcomeActivity.this,DateCalculatorActivity.class);
				startActivity(intent4);
				break;
			default:
				break;
			}
		}
	};

}
