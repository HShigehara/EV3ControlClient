/* 
 * SocketClient.java
 * クライアントクラス．EV3から送信するPC側のサーバーへ通信する
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
	static Socket socket;//ソケット
	static int port=10000;
	static DataOutputStream dos;
    static DataInputStream dis;
    static String PCIPAddress="192.168.1.7";//
    static InetAddress addr;
    static InputStream Is;
    static OutputStream Os;
	//private static final int TimeoutTime = 10;

    public static void main(String arg[]){
    	try{
			socket = new Socket( PCIPAddress ,  port); //接続
			Is = socket.getInputStream();
			dis = new DataInputStream(Is);
			Os = socket.getOutputStream();
			dos = new DataOutputStream(Os);
			System.out.println("socket");
		}catch(Exception e) {
			System.out.println("SC_Exception: " + e);
		}
	}
}