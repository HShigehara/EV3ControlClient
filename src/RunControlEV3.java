/*
 * RunControlEV3.java
 * EV3�̑��s�𐧌䂷��N���X
 */
import lejos.hardware.motor.Motor; //���[�^�[�̐ݒ�
//import lejos.hardware.port.SensorPort; //�Z���T�[�|�[�g�̐ݒ�
//import lejos.hardware.sensor.EV3UltrasonicSensor; //�����g�Z���T�[�̐ݒ�

//import java.io.*;
import java.lang.Math;

//�N���X�̒�`
//A�����̃��[�^�[l�CB���E�̃��[�^�[r
public class RunControlEV3 {
	//�ϐ�
	public static int ul; //���݂̃��[�^�[A(��)�̊p���x
	public static int ur; //���݂̃��[�^�[B(�E)�̊p���x
	public static int ul_real; //���[�^�[A(��)�̎��ۂ̊p���x
	public static int ur_real; //���[�^�[B(�E)�̎��ۂ̊p���x
	
	//���͒l���v�Z���邽�߂̕ϐ�
	public static boolean flag_once; //1��ڂ̏������`�F�b�N���邽�߂̃t���O
	double r; //�^�C���̔��a
	int vref_l; //���x�̖ڕW�l(��)
	int vref_r; //���x�̖ڕW�l(�E)
	double yawref; //���[�p�̖ڕW�l
	int ulref;
	int urref;
	double kp_l, ki_l, kd_l, kyaw_l; //�e�Q�C��(�����[�^�[)
	double kp_r, ki_r, kd_r, kyaw_r; //�e�Q�C��(�E���[�^�[)
	static double verr_l; //���x�̌덷(�����[�^�[)
	static double verr_r; //���x�̌덷(�E���[�^�[)
	static double verr_l_pre; //�O�t���[���̑��x�̌덷(�����[�^�[)
	static double verr_r_pre; //�O�t���[���̑��x�̌덷(�E���[�^�[)
	static double yaw_err; //���[�p�̌덷
	static double verr_l_sum; //verr_l�̑��a
	static double verr_r_sum; //verr_r�̑��a
	static double verr_l_diff; //verr_l�̍�
	static double verr_r_diff; //verr_r�̍�
		
	static float distance = 1;
	
	//���\�b�h
	//�R���X�g���N�^
	RunControlEV3(){
		//�C���X�^���X�������̏����l��ݒ�
		r = 55.0; //�^�C���̔��a��ݒ�
		
		vref_l = 80; //���^�C���̖ڕW�l[mm/sec]
		vref_r = 80; //�E�^�C���̖ڕW�l[mm/sec]
		
		yawref = 0.0; //���[�p�̏����l
		
		kp_l = 0.1; ki_l = 0.1; kd_l = 0.1; kyaw_l = 1; //�����[�^�[�̃Q�C���̏����l
		kp_r = 0.1; ki_r = 0.1; kd_r = 0.1; kyaw_r = 1; //�E���[�^�[�̃Q�C���̏����l
	}
	
	//���E���[�^�[�̓��͂��v�Z���郁�\�b�h(�v�C��)
	public void CalculateInputValue(double velocity, double yaw){
		if(flag_once == false)
		{
			//1��ڂ̓��́D�ڕW���xvref_l����ڕW�p���xu�́C�^�C���̔��a��r�Ƃ���ƁC
			//u = vref*360/2��r�ŋ��߂���D
			//System.out.println("==================================================");
			verr_l_sum = 0.0; //verr_l�̑��a�̏����l
			verr_r_sum = 0.0; //verr_r�̑��a�̏����l
			verr_l_pre = 0.0;
			verr_r_pre = 0.0;
			verr_l_diff = 0.0; //verr_l�̍��̏����l
			verr_r_diff = 0.0; //verr_r�̍��̏����l
			ul = (int)((velocity*360.0) / (2.0*Math.PI*r)); //1��ڂ̓���
			ur = (int)((velocity*360.0) / (2.0*Math.PI*r)); //1��ڂ̓���
			System.out.println("ul => " + ul + " ur => " + ur);
			flag_once = true;
		}else{
			verr_l = vref_l - velocity; //���݂̑��x�Ɠ��͂ŗ^���Ă��鑬�x�̌덷���v�Z(��)
			verr_r = vref_r - velocity; //���݂̑��x�Ɠ��͂ŗ^���Ă��鑬�x�̌덷���v�Z(�E)
			
			verr_l_sum += verr_l; //�����[�^�[�̑��x�̌덷�𑫂��Ă���
			verr_r_sum += verr_r; //�E���[�^�[�̑��x�̌덷�𑫂��Ă���
		
			verr_l_diff = verr_l - verr_l_pre;
			verr_r_diff = verr_r - verr_r_pre;
			//System.out.println("verr_diff " + verr_l_diff);
			verr_l_pre = verr_l;
			verr_r_pre = verr_r;
			
			yaw_err = yawref - yaw; //���[�p�̌덷
		
			//�ŏI�I�ɗ^������͒l
			ul = (int)(kp_l*verr_l + ki_l*verr_l_sum + kd_l*verr_l_diff - kyaw_l*yaw_err); //���[�^�[A(��)
			ur = (int)(kp_r*verr_r + ki_r*verr_r_sum + kd_r*verr_r_diff + kyaw_r*yaw_err); //���[�^�[B(�E)
			System.out.println("ul => " + ul + " ur => " + ur);
		}
	}
	
	//���ۂɓ��͂�^��EV3�𑖍s�����郁�\�b�h
	public void ControlEV3(String args[]) {
   		Motor.A.setSpeed(ul); //�v�Z�ɂ���ċ��߂����͒l�����[�^�[A�ɗ^����
        Motor.B.setSpeed(ur); //�v�Z�ɂ���ċ��߂����͒l�����[�^�[B�ɗ^����
        Motor.A.forward(); //���[�^�[A�𓮂���
        Motor.B.forward(); //���[�^�[B�𓮂���

        //���[�^�[�̊p���x���擾����
        ul_real = Motor.A.getRotationSpeed(); //���ۂɓ����Ă��郂�[�^�[A�̒l���擾����
        ur_real = Motor.B.getRotationSpeed(); //���ۂɓ����Ă��郂�[�^�[B�̒l���擾����
        //System.out.println("getA " + a_motorspeed); //�p�ςɔA
        //System.out.println("getB " + b_motorspeed); //�m�F�p
	}
}
