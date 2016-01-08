/*
 * TimeSchedulingClient.java
 * �N���C�A���g(EV3)���̎��Ԃ��X�P�W���[�����O����N���X�D
 */
//�C���|�[�g
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

//�N���X�̒�`
public class RunClient extends TimerTask{
	//�ϐ�
    private long timeStart; //�^�C�}�[�p�̕ϐ�

    //���\�b�h
    //�R���X�g���N�^
    RunClient(){
    	timeStart = System.nanoTime();
    }

    //run()�D�����̗���������ɏ���
    public void run(){
    	long timeNow = System.nanoTime() - timeStart;
    	System.out.println("Time : " + TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS));
    	
    	SocketClient sc = new SocketClient(); //�C���X�^���X�𐶐�
    	RunControlEV3 rcev3 = new RunControlEV3(); //�C���X�^���X�𐶐�
    	sc.ReceiveData(null); //�f�[�^����M
    	rcev3.CalculateInputValue(sc.velocity, sc.yaw); //EV3�̑��s����N���X�ɑ��x�ƃ��[�p��n��
    	rcev3.ControlEV3(null); //EV3�𑖍s������
    	//System.out.println(TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS) + " " + rcev3.ua_current + " " + rcev3.ub_current + " " + rcev3.ua_real + " " + rcev3.ub_real); //�m�F�p�ɕ\��
    	
    	//�t�@�C���ɏ�������
   		//File file = new File("u.dat");
    	//try{
    	//	FileWriter filewriter = new FileWriter(file, true); //�ǋL���[�h
    	//	filewriter.write(TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS) + " " + rcev3.ul + " " + rcev3.ur + " " + rcev3.ul_real + " " + rcev3.ur_real + "\n"); //�m�F�p�ɕ\��
    	//	filewriter.close();
    	//} catch(IOException e){
    	//	System.out.println("error : " + e);
    	//}
    }
}