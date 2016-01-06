/*
 * TimeSchedulingClient.java
 * クライアント(EV3)側の時間をスケジューリングするクラス．
 */
//インポート
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//クラスの定義
public class RunClient extends TimerTask{
	//変数
    private long timeStart;
    private String id;

    //メソッド
    //コンストラクタ
    RunClient(String arg){
    	timeStart = System.nanoTime();
    	id = arg;
    }

    //run()．処理の流れをここに書く
    public void run(){
    	long timeNow = System.nanoTime() - timeStart;
    	//System.out.println(id +  " : " + TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS));
    	
    	//ソケット通信
    	SocketClient socket_client = new SocketClient(); //インスタンスを生成
    	socket_client.ConnectClient(null); //ソケット接続
    }
}