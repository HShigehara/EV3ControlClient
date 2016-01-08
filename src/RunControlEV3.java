/*
 * RunControlEV3.java
 * EV3の走行を制御するクラス
 */
import lejos.hardware.motor.Motor; //モーターの設定
//import lejos.hardware.port.SensorPort; //センサーポートの設定
//import lejos.hardware.sensor.EV3UltrasonicSensor; //超音波センサーの設定
//import lejos.utility.Delay;

//import java.io.*;
import java.lang.Math;

//クラスの定義
//Aが左のモーターl，Bが右のモーターr
public class RunControlEV3 {
	//変数
	public int ul; //現在のモーターA(左)の角速度
	public int ur; //現在のモーターB(右)の角速度
	public int ul_real; //モーターA(左)の実際の角速度
	public int ur_real; //モーターB(右)の実際の角速度
	
	//入力値を計算するための変数
	double r; //タイヤの半径
	int vref_l; //速度の目標値(左)
	int vref_r; //速度の目標値(右)
	double yawref; //ヨー角の目標値
	double kp_l, ki_l, kd_l, kyaw_l; //各ゲイン(左モーター)
	double kp_r, ki_r, kd_r, kyaw_r; //各ゲイン(右モーター)
	double verr_l; //速度の誤差(左モーター)
	double verr_r; //速度の誤差(右モーター)
	double verr_l_pre; //前フレームの速度の誤差(左モーター)
	double verr_r_pre; //前フレームの速度の誤差(右モーター)
	double yaw_err; //ヨー角の誤差
	double verr_sum_l; //verr_lの総和
	double verr_sum_r; //verr_rの総和
	double verr_diff_l; //verr_lの差
	double verr_diff_r; //verr_rの差
		
	//メソッド
	//コンストラクタ
	RunControlEV3(){
		//インスタンス生成時の初期値を設定
		r = 55.0; //タイヤの半径を設定
		
		vref_l = 96; //左タイヤの目標値[mm/sec]
		vref_r = 96; //右タイヤの目標値[mm/sec]
		
		//1回目の入力．目標速度vref_lから目標角速度uは，タイヤの半径をrとすると，
		//u = vref*360/2πrで求められる．
		ul = (int)((vref_l*360.0) / (2.0*Math.PI*r)); //1回目の入力
		ur = (int)((vref_r*360.0) / (2.0*Math.PI*r)); //1回目の入力
		
		yawref = 0.0; //ヨー角の初期値
		
		kp_l = 1.0; ki_l = 1.0; kd_l = 1.0; kyaw_l = 1.0; //左モーターのゲインの初期値
		kp_r = 1.0; ki_r = 1.0; kd_r = 1.0; kyaw_r = 1.0; //右モーターのゲインの初期値
		
		verr_sum_l = 0.0; //verr_lの総和の初期値
		verr_sum_r = 0.0; //verr_rの総和の初期値
		verr_diff_l = 0.0; //verr_lの差の初期値
		verr_diff_r = 0.0; //verr_rの差の初期値
	}
	
	//左右モーターの入力を計算するメソッド(要修正)
	public void CalculateInputValue(double velocity, double yaw){
		verr_l = vref_l - velocity; //現在の速度と入力で与えている速度の誤差を計算(左)
		verr_r = vref_r - velocity; //現在の速度と入力で与えている速度の誤差を計算(右)
		//System.out.println("verr_l = " + verr_l);
		
		verr_sum_l += verr_l; //左モーターの速度の誤差を足していく
		verr_sum_r += verr_r; //右モーターの速度の誤差を足していく
		
		verr_l_pre = verr_l_pre - verr_l; //左モーターの速度の誤差の差を計算
		verr_r_pre = verr_r_pre - verr_r; //右モーターの速度の誤差の差を計算
		
		yaw_err = yawref - yaw;
		
		//最終的に与える入力値
		ul = (int)(kp_l*verr_l + ki_l*verr_sum_l + kd_l*verr_l_pre - kyaw_l*yaw_err); //モーターA(左)
		ur = (int)(kp_r*verr_r + ki_r*verr_sum_l + kd_r*verr_r_pre + kyaw_r*yaw_err); //モーターB(右)
		System.out.println("ul => " + ul + " ur => " + ur);
	}
	
	//実際に入力を与えEV3を走行させるメソッド
	public void ControlEV3(String args[]) {
		//確認用．EV3のディスプレイに表示される
		//System.out.println("=================");
		//System.out.println("v = " + velocity); //確認用
 		//System.out.println("yaw = " + yaw); //確認用
		//System.out.println("=================");
		
   		Motor.A.setSpeed(ul); //計算によって求めた入力値をモーターAに与える
        Motor.B.setSpeed(ur); //計算によって求めた入力値をモーターBに与える
        Motor.A.forward(); //モーターAを動かす
        Motor.B.forward(); //モーターBを動かす
		
        //モーターの角速度を取得する
        ul_real = Motor.A.getRotationSpeed(); //実際に動いているモーターAの値を取得する
        ur_real = Motor.B.getRotationSpeed(); //実際に動いているモーターBの値を取得する
        //System.out.println("getA " + a_motorspeed); //角煮に尿
        //System.out.println("getB " + b_motorspeed); //確認用
        
		//Motor.B.stop(); //モーターを停止
        //Motor.A.stop(); //モーターを停止
	}
}
