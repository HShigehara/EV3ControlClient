/*
 * RunControlEV3.java
 * EV3�̑��s�𐧌䂷��N���X
 */
import lejos.hardware.motor.Motor; //���[�^�[�̐ݒ�
//import lejos.hardware.port.SensorPort; //�Z���T�[�|�[�g�̐ݒ�
//import lejos.hardware.sensor.EV3UltrasonicSensor; //�����g�Z���T�[�̐ݒ�
//import lejos.utility.Delay;

//import java.io.*;
import java.lang.Math;

//�N���X�̒�`
//A�����̃��[�^�[l�CB���E�̃��[�^�[r
public class RunControlEV3 {
	//�ϐ�
	public int ul; //���݂̃��[�^�[A(��)�̊p���x
	public int ur; //���݂̃��[�^�[B(�E)�̊p���x
	public int ul_real; //���[�^�[A(��)�̎��ۂ̊p���x
	public int ur_real; //���[�^�[B(�E)�̎��ۂ̊p���x
	
	//���͒l���v�Z���邽�߂̕ϐ�
	double r; //�^�C���̔��a
	int vref_l; //���x�̖ڕW�l(��)
	int vref_r; //���x�̖ڕW�l(�E)
	double yawref; //���[�p�̖ڕW�l
	double kp_l, ki_l, kd_l, kyaw_l; //�e�Q�C��(�����[�^�[)
	double kp_r, ki_r, kd_r, kyaw_r; //�e�Q�C��(�E���[�^�[)
	double verr_l; //���x�̌덷(�����[�^�[)
	double verr_r; //���x�̌덷(�E���[�^�[)
	double verr_l_pre; //�O�t���[���̑��x�̌덷(�����[�^�[)
	double verr_r_pre; //�O�t���[���̑��x�̌덷(�E���[�^�[)
	double yaw_err; //���[�p�̌덷
	double verr_sum_l; //verr_l�̑��a
	double verr_sum_r; //verr_r�̑��a
	double verr_diff_l; //verr_l�̍�
	double verr_diff_r; //verr_r�̍�
		
	//���\�b�h
	//�R���X�g���N�^
	RunControlEV3(){
		//�C���X�^���X�������̏����l��ݒ�
		r = 55.0; //�^�C���̔��a��ݒ�
		
		vref_l = 96; //���^�C���̖ڕW�l[mm/sec]
		vref_r = 96; //�E�^�C���̖ڕW�l[mm/sec]
		
		//1��ڂ̓��́D�ڕW���xvref_l����ڕW�p���xu�́C�^�C���̔��a��r�Ƃ���ƁC
		//u = vref*360/2��r�ŋ��߂���D
		ul = (int)((vref_l*360.0) / (2.0*Math.PI*r)); //1��ڂ̓���
		ur = (int)((vref_r*360.0) / (2.0*Math.PI*r)); //1��ڂ̓���
		
		yawref = 0.0; //���[�p�̏����l
		
		kp_l = 1.0; ki_l = 1.0; kd_l = 1.0; kyaw_l = 1.0; //�����[�^�[�̃Q�C���̏����l
		kp_r = 1.0; ki_r = 1.0; kd_r = 1.0; kyaw_r = 1.0; //�E���[�^�[�̃Q�C���̏����l
		
		verr_sum_l = 0.0; //verr_l�̑��a�̏����l
		verr_sum_r = 0.0; //verr_r�̑��a�̏����l
		verr_diff_l = 0.0; //verr_l�̍��̏����l
		verr_diff_r = 0.0; //verr_r�̍��̏����l
	}
	
	//���E���[�^�[�̓��͂��v�Z���郁�\�b�h(�v�C��)
	public void CalculateInputValue(double velocity, double yaw){
		verr_l = vref_l - velocity; //���݂̑��x�Ɠ��͂ŗ^���Ă��鑬�x�̌덷���v�Z(��)
		verr_r = vref_r - velocity; //���݂̑��x�Ɠ��͂ŗ^���Ă��鑬�x�̌덷���v�Z(�E)
		//System.out.println("verr_l = " + verr_l);
		
		verr_sum_l += verr_l; //�����[�^�[�̑��x�̌덷�𑫂��Ă���
		verr_sum_r += verr_r; //�E���[�^�[�̑��x�̌덷�𑫂��Ă���
		
		verr_l_pre = verr_l_pre - verr_l; //�����[�^�[�̑��x�̌덷�̍����v�Z
		verr_r_pre = verr_r_pre - verr_r; //�E���[�^�[�̑��x�̌덷�̍����v�Z
		
		yaw_err = yawref - yaw;
		
		//�ŏI�I�ɗ^������͒l
		ul = (int)(kp_l*verr_l + ki_l*verr_sum_l + kd_l*verr_l_pre - kyaw_l*yaw_err); //���[�^�[A(��)
		ur = (int)(kp_r*verr_r + ki_r*verr_sum_l + kd_r*verr_r_pre + kyaw_r*yaw_err); //���[�^�[B(�E)
		System.out.println("ul => " + ul + " ur => " + ur);
	}
	
	//���ۂɓ��͂�^��EV3�𑖍s�����郁�\�b�h
	public void ControlEV3(String args[]) {
		//�m�F�p�DEV3�̃f�B�X�v���C�ɕ\�������
		//System.out.println("=================");
		//System.out.println("v = " + velocity); //�m�F�p
 		//System.out.println("yaw = " + yaw); //�m�F�p
		//System.out.println("=================");
		
   		Motor.A.setSpeed(ul); //�v�Z�ɂ���ċ��߂����͒l�����[�^�[A�ɗ^����
        Motor.B.setSpeed(ur); //�v�Z�ɂ���ċ��߂����͒l�����[�^�[B�ɗ^����
        Motor.A.forward(); //���[�^�[A�𓮂���
        Motor.B.forward(); //���[�^�[B�𓮂���
		
        //���[�^�[�̊p���x���擾����
        ul_real = Motor.A.getRotationSpeed(); //���ۂɓ����Ă��郂�[�^�[A�̒l���擾����
        ur_real = Motor.B.getRotationSpeed(); //���ۂɓ����Ă��郂�[�^�[B�̒l���擾����
        //System.out.println("getA " + a_motorspeed); //�p�ςɔA
        //System.out.println("getB " + b_motorspeed); //�m�F�p
        
		//Motor.B.stop(); //���[�^�[���~
        //Motor.A.stop(); //���[�^�[���~
	}
}
