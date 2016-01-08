/*
 * TimeSchedulingClient.java
 * クライアント(EV3)側の時間をスケジューリングするクラス．
 */
//インポート
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//クラスの定義
public class RunClient extends TimerTask{
	//変数
    private long timeStart; //タイマー用の変数

    //メソッド
    //コンストラクタ
    RunClient(){
    	timeStart = System.nanoTime();
    }

    //run()．処理の流れをここに書く
    public void run(){
    	long timeNow = System.nanoTime() - timeStart;
    	System.out.println("Time : " + TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS));
    	
    	SocketClient sc = new SocketClient(); //インスタンスを生成
    	RunControlEV3 rcev3 = new RunControlEV3(); //インスタンスを生成
    	sc.ReceiveData(null); //データを受信
    	rcev3.CalculateInputValue(sc.velocity, sc.yaw); //EV3の走行制御クラスに速度とヨー角を渡す
    	rcev3.ControlEV3(null); //EV3を走行させる
    	//System.out.println(TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS) + " " + rcev3.ua_current + " " + rcev3.ub_current + " " + rcev3.ua_real + " " + rcev3.ub_real); //確認用に表示
    	
    	//ファイルに書き込む
   		//File file = new File("u.dat");
    	//try{
    	//	FileWriter filewriter = new FileWriter(file, true); //追記モード
    	//	filewriter.write(TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS) + " " + rcev3.ul + " " + rcev3.ur + " " + rcev3.ul_real + " " + rcev3.ur_real + "\n"); //確認用に表示
    	//	filewriter.close();
    	//} catch(IOException e){
    	//	System.out.println("error : " + e);
    	//}
    }
}