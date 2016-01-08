/*
 * RunControlEV3.java
 * EV3の走行を制御するクラス
 */
import lejos.hardware.motor.Motor; //モーターの設定
//import lejos.hardware.port.SensorPort; //センサーポートの設定
//import lejos.hardware.sensor.EV3UltrasonicSensor; //超音波センサーの設定

//import java.io.*;
import java.lang.Math;

//クラスの定義
//Aが左のモーターl，Bが右のモーターr
public class RunControlEV3 {
	//変数
	public static int ul; //現在のモーターA(左)の角速度
	public static int ur; //現在のモーターB(右)の角速度
	public static int ul_real; //モーターA(左)の実際の角速度
	public static int ur_real; //モーターB(右)の実際の角速度
	
	//入力値を計算するための変数
	public static boolean flag_once; //1回目の処理をチェックするためのフラグ
	double r; //タイヤの半径
	int vref_l; //速度の目標値(左)
	int vref_r; //速度の目標値(右)
	double yawref; //ヨー角の目標値
	int ulref;
	int urref;
	double kp_l, ki_l, kd_l, kyaw_l; //各ゲイン(左モーター)
	double kp_r, ki_r, kd_r, kyaw_r; //各ゲイン(右モーター)
	static double verr_l; //速度の誤差(左モーター)
	static double verr_r; //速度の誤差(右モーター)
	static double verr_l_pre; //前フレームの速度の誤差(左モーター)
	static double verr_r_pre; //前フレームの速度の誤差(右モーター)
	static double yaw_err; //ヨー角の誤差
	static double verr_l_sum; //verr_lの総和
	static double verr_r_sum; //verr_rの総和
	static double verr_l_diff; //verr_lの差
	static double verr_r_diff; //verr_rの差
		
	static float distance = 1;
	
	//メソッド
	//コンストラクタ
	RunControlEV3(){
		//インスタンス生成時の初期値を設定
		r = 55.0; //タイヤの半径を設定
		
		vref_l = 80; //左タイヤの目標値[mm/sec]
		vref_r = 80; //右タイヤの目標値[mm/sec]
		
		yawref = 0.0; //ヨー角の初期値
		
		kp_l = 0.1; ki_l = 0.1; kd_l = 0.1; kyaw_l = 1; //左モーターのゲインの初期値
		kp_r = 0.1; ki_r = 0.1; kd_r = 0.1; kyaw_r = 1; //右モーターのゲインの初期値
	}
	
	//左右モーターの入力を計算するメソッド(要修正)
	public void CalculateInputValue(double velocity, double yaw){
		if(flag_once == false)
		{
			//1回目の入力．目標速度vref_lから目標角速度uは，タイヤの半径をrとすると，
			//u = vref*360/2πrで求められる．
			//System.out.println("==================================================");
			verr_l_sum = 0.0; //verr_lの総和の初期値
			verr_r_sum = 0.0; //verr_rの総和の初期値
			verr_l_pre = 0.0;
			verr_r_pre = 0.0;
			verr_l_diff = 0.0; //verr_lの差の初期値
			verr_r_diff = 0.0; //verr_rの差の初期値
			ul = (int)((velocity*360.0) / (2.0*Math.PI*r)); //1回目の入力
			ur = (int)((velocity*360.0) / (2.0*Math.PI*r)); //1回目の入力
			System.out.println("ul => " + ul + " ur => " + ur);
			flag_once = true;
		}else{
			verr_l = vref_l - velocity; //現在の速度と入力で与えている速度の誤差を計算(左)
			verr_r = vref_r - velocity; //現在の速度と入力で与えている速度の誤差を計算(右)
			
			verr_l_sum += verr_l; //左モーターの速度の誤差を足していく
			verr_r_sum += verr_r; //右モーターの速度の誤差を足していく
		
			verr_l_diff = verr_l - verr_l_pre;
			verr_r_diff = verr_r - verr_r_pre;
			//System.out.println("verr_diff " + verr_l_diff);
			verr_l_pre = verr_l;
			verr_r_pre = verr_r;
			
			yaw_err = yawref - yaw; //ヨー角の誤差
		
			//最終的に与える入力値
			ul = (int)(kp_l*verr_l + ki_l*verr_l_sum + kd_l*verr_l_diff - kyaw_l*yaw_err); //モーターA(左)
			ur = (int)(kp_r*verr_r + ki_r*verr_r_sum + kd_r*verr_r_diff + kyaw_r*yaw_err); //モーターB(右)
			System.out.println("ul => " + ul + " ur => " + ur);
		}
	}
	
	//実際に入力を与えEV3を走行させるメソッド
	public void ControlEV3(String args[]) {
   		Motor.A.setSpeed(ul); //計算によって求めた入力値をモーターAに与える
        Motor.B.setSpeed(ur); //計算によって求めた入力値をモーターBに与える
        Motor.A.forward(); //モーターAを動かす
        Motor.B.forward(); //モーターBを動かす

        //モーターの角速度を取得する
        ul_real = Motor.A.getRotationSpeed(); //実際に動いているモーターAの値を取得する
        ur_real = Motor.B.getRotationSpeed(); //実際に動いているモーターBの値を取得する
        //System.out.println("getA " + a_motorspeed); //角煮に尿
        //System.out.println("getB " + b_motorspeed); //確認用
	}
}
