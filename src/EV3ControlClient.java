/*!
 * @file EV3ControlClient.java
 * @brief EV3�𐧌䂷��N���C�A���g(EV3)���̃��C���N���X�D
 * @date 2016.01.05
 * @author H.Shigehara
 */
//�C���|�[�g
import java.util.Timer;

//�N���X�̒�`
/*!
 * @class EV3ControlClient
 * @brief �N���C�A���g���̃��C���̏����N���X
 */
public class EV3ControlClient{
	/*!
	 * @brief ���C�����\�b�h
	 */
    public static void main(String[] args){
    	Timer timer = new Timer(); //�^�C�}�[timer�̃C���X�^���X�𐶐�

    	//�A���������s���O�ɃR�l�N�V�������͂�
    	SocketClient.MakeConnection(); 
    	
    	//1��ڂ̏����̃t���O��ݒ�
    	RunControlEV3.flag_once = false; //1��ڂȂ̂Ńt���O��false�ɂ��Ă���
    	
    	//(~, �J�n, ����)
    	timer.scheduleAtFixedRate(new RunClient(), 1000, 100); //scheduleAtFixedRate���\�b�h�𗘗p��

    	try{ //�ʏ펞
    		Thread.sleep(60000); //[ms]�����҂�
    	} catch(InterruptedException e){ //��O������
    		System.out.println("error : " + e); //�G���[��\��
    	}
    	timer.cancel(); //60�b��ɏI��
    }
}