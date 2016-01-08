/* 
 * SocketClient.java
 * クライアントクラス．EV3から送信するPC側のサーバーへ通信する
 */
//インポート
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import lejos.utility.Delay;

//クラスの定義
public class SocketClient {
	//変数
	static Socket socket; //ソケット
	static int port = 10000; //ポート番号
	static DataOutputStream dos; //データ出力ストリーム
    static DataInputStream dis; //データ入力ストリーム
    static String PCIPAddress = "192.168.1.7"; //PCのIPアドレス
    static InetAddress addr; //IPアドレス
    static InputStream Is; //入力ストリーム
    static OutputStream Os; //出力ストリーム
    
    public double velocity;
   	public double yaw;
   	
   	//メソッド
    //コネクションをはるメソッド
    static void MakeConnection(String args[]){
    	try{
    		System.out.println("Starting Client...");
    		socket = new Socket(PCIPAddress, port);
    		Is = socket.getInputStream();
			dis = new DataInputStream(Is);
			Os = socket.getOutputStream();
			dos = new DataOutputStream(Os);
    	}catch(Exception e){
    		System.out.println("SC_Exception" + e);
    	}
    }
    
    //データを受信するメソッド
    public void ReceiveData(String arg[]){
    	try{
			//データの受信
    		velocity = dis.readDouble(); //速度を受信
			yaw = dis.readDouble(); //ヨー角を受信
			System.out.println("Velocity => " + velocity + " , Yaw => " + yaw);
		}catch(Exception e) {
			System.out.println("IOException: " + e);
			System.exit(-1);
		}
	}
}