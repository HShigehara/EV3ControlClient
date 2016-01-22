/*!
 * @file EV3ControlClient.java
 * @brief EV3を制御するクライアント(EV3)側のメインクラス．
 * @date 2016.01.05
 * @author H.Shigehara
 */
//インポート
import java.util.Timer;

//クラスの定義
/*!
 * @class EV3ControlClient
 * @brief クライアント側のメインの処理クラス
 */
public class EV3ControlClient{
	/*!
	 * @brief メインメソッド
	 */
    public static void main(String[] args){
    	Timer timer = new Timer(); //タイマーtimerのインスタンスを生成

    	//連続処理を行う前にコネクションをはる
    	SocketClient.MakeConnection(); 
    	
    	//1回目の処理のフラグを設定
    	RunControlEV3.flag_once = false; //1回目なのでフラグをfalseにしておく
    	
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