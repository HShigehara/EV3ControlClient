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
	Timer t1 = new Timer(); //タイマーt1のインスタンスを生成
	Timer t2 = new Timer(); //タイマーt2のインスタンスを生成

	t1.schedule(new TimeSchedulingClient("First"), 1000, 100); //scheduleメソッドを利用時
	t2.scheduleAtFixedRate(new TimeSchedulingClient("Second"), 1000, 100); //scheduleAtFixedRateメソッドを利用時

	try{ //通常時
	    Thread.sleep(5000); //5000[ms]だけ待って動作
	} catch(InterruptedException e){ //例外発生時
	    System.out.println("error : " + e); //エラーを表示
	}

	// タスクの停止
	t1.cancel();
	t2.cancel();
    }
}