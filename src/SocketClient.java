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

//クラスの定義
public class SocketClient {
	//変数
	static Socket socket; //ソケット
	static int port=10000; //ポート番号
	static DataOutputStream dos; //データ出力ストリーム
    static DataInputStream dis; //データ入力ストリーム
    static String PCIPAddress="192.168.1.7"; //PCのIPアドレス
    static InetAddress addr; //IPアドレス
    static InputStream Is; //入力ストリーム
    static OutputStream Os; //出力ストリーム

    //メソッド
    public void ConnectClient(String arg[]){
    	try{
    		double velocity;
    		double yaw;
    		
			socket = new Socket( PCIPAddress ,  port); //接続
			Is = socket.getInputStream();
			dis = new DataInputStream(Is);
			Os = socket.getOutputStream();
			dos = new DataOutputStream(Os);
			
			//データの受信
			velocity = dis.readDouble(); //速度を受信
			yaw = dis.readDouble(); //ヨー角を受信
			//dis.close();
			System.out.println("Velocity => " + velocity + " , Yaw => " + yaw);
			
		}catch(Exception e) {
			System.out.println("SC_Exception: " + e);
		}
	}
}