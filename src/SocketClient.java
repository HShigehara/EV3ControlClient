/* 
 * SocketClient.java
 * �N���C�A���g�N���X�DEV3���瑗�M����PC���̃T�[�o�[�֒ʐM����
 */
//�C���|�[�g
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

//�N���X�̒�`
public class SocketClient {
	//�ϐ�
	static Socket socket; //�\�P�b�g
	static int port=10000; //�|�[�g�ԍ�
	static DataOutputStream dos; //�f�[�^�o�̓X�g���[��
    static DataInputStream dis; //�f�[�^���̓X�g���[��
    static String PCIPAddress="192.168.1.7"; //PC��IP�A�h���X
    static InetAddress addr; //IP�A�h���X
    static InputStream Is; //���̓X�g���[��
    static OutputStream Os; //�o�̓X�g���[��
	//private static final int TimeoutTime = 10;

    //���\�b�h
    public void ConnectClient(String arg[]){
    	try{
    		double velocity;
    		double yaw;
    		
			socket = new Socket( PCIPAddress ,  port); //�ڑ�
			Is = socket.getInputStream();
			dis = new DataInputStream(Is);
			Os = socket.getOutputStream();
			dos = new DataOutputStream(Os);
			System.out.println("socket");
			
			velocity = dis.readDouble();
			//yaw = dis.readDouble();
			System.out.println("Velocity => " + velocity/* + " , Yaw => " + yaw*/);
		}catch(Exception e) {
			System.out.println("SC_Exception: " + e);
		}
	}
}