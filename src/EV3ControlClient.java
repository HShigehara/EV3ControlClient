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
	Timer t1 = new Timer(); //�^�C�}�[t1�̃C���X�^���X�𐶐�
	Timer t2 = new Timer(); //�^�C�}�[t2�̃C���X�^���X�𐶐�

	t1.schedule(new TimeSchedulingClient("First"), 1000, 100); //schedule���\�b�h�𗘗p��
	t2.scheduleAtFixedRate(new TimeSchedulingClient("Second"), 1000, 100); //scheduleAtFixedRate���\�b�h�𗘗p��

	try{ //�ʏ펞
	    Thread.sleep(5000); //5000[ms]�����҂��ē���
	} catch(InterruptedException e){ //��O������
	    System.out.println("error : " + e); //�G���[��\��
	}

	// �^�X�N�̒�~
	t1.cancel();
	t2.cancel();
    }
}