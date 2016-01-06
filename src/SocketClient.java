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

    
	//���\�b�h
    //�R�l�N�V�������͂郁�\�b�h
    public void MakeConnection(String args[]){
    	try{
    		socket = new Socket(PCIPAddress, port);
    		Is = socket.getInputStream();
			dis = new DataInputStream(Is);
			Os = socket.getOutputStream();
			dos = new DataOutputStream(Os);
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    
    //�f�[�^����M���郁�\�b�h
    public void ReceiveData(String arg[]){
   		double velocity;
   		double yaw;
   		RunEV3 rev3 = new RunEV3();
    	try{
			//�f�[�^�̎�M
			//Is = socket.getInputStream();
			//dis = new DataInputStream(Is);
    		velocity = dis.readDouble(); //���x����M
			yaw = dis.readDouble(); //���[�p����M
			//dis.close();
			System.out.println("Velocity => " + velocity + " , Yaw => " + yaw);
			
			//EV3�̑��s�v���O�����Ɉڍs
			rev3.ControlEV3(velocity, yaw);
		}catch(Exception e) {
			System.out.println("SC_Exception: " + e);
		}
	}
}