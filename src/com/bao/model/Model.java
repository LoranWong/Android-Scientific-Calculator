package com.bao.model;



public interface Model {
	
	//该枚举类用于判断当前进制
	public enum Current {TWO(2),EIGHT(8),TEN(10),SIXTEEN(16);
	//成员变量
	public int value;
	//枚举类的构造方法
    private Current(int value) {
        this.value = value;
    }		
	};
	
	public class Dangwei{
		public String name;
		public double value;
		public int index;
		Dangwei(String name,double value,int index){
			this.name=name;
			this.value=value;
			this.index=index;
		}
	}
	
	public class Leixing{
		public Dangwei dangwei;
		public String name;
		public int index;
		public Leixing(String name,Dangwei dangwei,int index) {
			this.name=name;
			this.dangwei=dangwei;
			this.index=index;
		}
	}
	
	public static Dangwei QIANGKE=new Dangwei("千克", 1.0, 0);
	public static Dangwei KE=new Dangwei("克", 1000, 1);
	public static Dangwei BANG=new Dangwei("磅", 2.205, 2);
	public static Dangwei DUN=new Dangwei("吨", 0.001, 3);
	public static Dangwei JIN=new Dangwei("斤", 2.0, 4);
	public static Dangwei LIANG=new Dangwei("两", 20.0, 5);
	
	public static Dangwei MI2=new Dangwei("平方米", 1.0, 0);
	public static Dangwei QIANMI2=new Dangwei("平方千米", 0.000001, 1);
	public static Dangwei MU=new Dangwei("亩(中国)", 0.0015, 2);
	public static Dangwei GONGMU=new Dangwei("公亩", 0.01, 3);
	public static Dangwei YINGMU=new Dangwei("英亩", 0.000247, 4);
	public static Dangwei GONGQING=new Dangwei("公顷", 0.0001, 5);

	public static Dangwei MI=new Dangwei("米", 1.0, 0);
	public static Dangwei GONGLI=new Dangwei("公里", 0.001, 1);
	public static Dangwei CHI=new Dangwei("尺(中国)", 3.0, 2);
	public static Dangwei CUN=new Dangwei("寸(中国)", 30.0, 3);
	public static Dangwei LI=new Dangwei("里(中国)", 0.002, 4);
	public static Dangwei YINGCHI=new Dangwei("英尺", 3.28, 5);
	
	public static Dangwei CHN=new Dangwei("人民币", 1.0, 0);
	public static Dangwei USA=new Dangwei("美元", 0.1648, 1);
	public static Dangwei HK=new Dangwei("港元", 1.278, 2);
	public static Dangwei TW=new Dangwei("台币", 4.986, 3);
	public static Dangwei EUR=new Dangwei("欧元", 0.1204, 4);
	public static Dangwei UK=new Dangwei("英镑", 0.0985, 5);
	
	public static Leixing LENGTH=new Leixing("长度",MI,0);
	public static Leixing SQUARE=new Leixing("面积",MI2,1);
	public static Leixing MASS=new Leixing("质量",QIANGKE,2);
	public static Leixing MONEY=new Leixing("货币",CHN,3);
}
