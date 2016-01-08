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
    	Timer timer = new Timer(); //タイマーtimerのインスタンスを生成

    	//連続処理を行う前にコネクションをはる
    	SocketClient sc = new SocketClient();
    	sc.MakeConnection(null);
    	    	
    	//(~, 開始, 増分)
    	timer.scheduleAtFixedRate(new RunClient(), 1000, 100); //scheduleAtFixedRateメソッドを利用時

    	try{ //通常時
    		Thread.sleep(60000); //[ms]だけ待つ
    	} catch(InterruptedException e){ //例外発生時
    		System.out.println("error : " + e); //エラーを表示
    	}
    	timer.cancel(); //60秒後に終了
    }
}