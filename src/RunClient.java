/*
 * TimeSchedulingClient.java
 * �N���C�A���g(EV3)���̎��Ԃ��X�P�W���[�����O����N���X�D
 */
//�C���|�[�g
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//�N���X�̒�`
public class RunClient extends TimerTask{
	//�ϐ�
    private long timeStart;
    private String id;

   	static SocketClient sc = new SocketClient(); //�C���X�^���X�𐶐�
   	
    //���\�b�h
    //�R���X�g���N�^
    RunClient(String arg){
    	timeStart = System.nanoTime();
    	id = arg;
    }

    //run()�D�����̗���������ɏ���
    public void run(){
    	long timeNow = System.nanoTime() - timeStart;
    	System.out.println(id +  " : " + TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS));
    	
    	//�\�P�b�g�ʐM
    	sc.ConnectClient(null); //�\�P�b�g�ڑ�
    }
}