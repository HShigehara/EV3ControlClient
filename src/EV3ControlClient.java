/*
 * EV3ControlClient.java
 * EV3�𐧌䂷��N���C�A���g(EV3)���̃��C���N���X�D
 */
//�C���|�[�g
import java.util.Timer;

//�N���X�̒�`
public class EV3ControlClient{
	//���C�����\�b�h
    public static void main(String[] args){
    	Timer timer = new Timer(); //�^�C�}�[t2�̃C���X�^���X�𐶐�

    	//�A���������s���O�ɃR�l�N�V�������͂�
    	SocketClient sc = new SocketClient();
    	sc.MakeConnection(null);
    	
    	//(~, �J�n, ����)
    	timer.scheduleAtFixedRate(new RunClient("Time Scheduling Client"), 1000, 5000); //scheduleAtFixedRate���\�b�h�𗘗p��

    	try{ //�ʏ펞
    		Thread.sleep(5000); //5000[ms]�����҂��ē�����J�n
    	} catch(InterruptedException e){ //��O������
    		System.out.println("error : " + e); //�G���[��\��
    	}
    	
    	
    	// �^�X�N�̒�~
    	//timer.cancel();
    }
}