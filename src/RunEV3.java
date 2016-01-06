import lejos.hardware.motor.Motor; //モーターの設定
import lejos.hardware.port.SensorPort; //センサーポートの設定
import lejos.hardware.sensor.EV3UltrasonicSensor; //超音波センサーの設定
import lejos.utility.Delay;

import java.io.*;

public class RunEV3 {
	public static void ControlEV3(double velocity, double yaw) { //main()
		
		System.out.println(velocity + " " + yaw);
		
		//long timeMillisStart = System.currentTimeMillis(); //時間を[ms]で取得
		//float[] ut_value = new float[1]; //距離データ保存用
		//EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S3); //超音波センサーのインスタンスをポート3で生成
		//float distance = 10; //デフォルトの距離(近づく場合は適当に大きくしておく)
		
		//Delay.msDelay(10000); //処理を遅らせる[ms]
		
		//while(distance > 0.4){ //距離が0.4[m]より大きい間は実行する
   		Motor.B.setSpeed((int)velocity * 50); //Default = 200
        Motor.A.setSpeed((int)velocity * 50); //Default = 200
        Motor.B.forward(); //モーターを動かす
        Motor.A.forward(); //モーターを動かす
   		//us1.getDistanceMode().fetchSample(ut_value, 0); //距離を取得
   		//distance = ut_value[0]; //距離を格納
   		//}
		
		//Motor.B.stop(); //モーターを停止
        //Motor.A.stop(); //モーターを停止
	}
}
