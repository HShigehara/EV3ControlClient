/*
 * EV3ControlClient.java
 * EV3を制御するクライアント(EV3)側のメインクラス．
 */
//インポート
import java.util.Timer;

//クラスの定義
public class EV3ControlClient{
	//メインメソッド
    public static void main(String[] args){
    	Timer timer = new Timer(); //タイマーt2のインスタンスを生成

    	//連続処理を行う前にコネクションをはる
    	SocketClient sc = new SocketClient();
    	sc.MakeConnection(null);
    	
    	//(~, 開始, 増分)
    	timer.scheduleAtFixedRate(new RunClient("Time Scheduling Client"), 1000, 5000); //scheduleAtFixedRateメソッドを利用時

    	try{ //通常時
    		Thread.sleep(5000); //5000[ms]だけ待って動作を開始
    	} catch(InterruptedException e){ //例外発生時
    		System.out.println("error : " + e); //エラーを表示
    	}
    	
    	
    	// タスクの停止
    	//timer.cancel();
    }
}