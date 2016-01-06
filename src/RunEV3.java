import lejos.hardware.motor.Motor; //���[�^�[�̐ݒ�
import lejos.hardware.port.SensorPort; //�Z���T�[�|�[�g�̐ݒ�
import lejos.hardware.sensor.EV3UltrasonicSensor; //�����g�Z���T�[�̐ݒ�
import lejos.utility.Delay;

import java.io.*;

public class RunEV3 {
	public static void ControlEV3(double velocity, double yaw) { //main()
		
		System.out.println(velocity + " " + yaw);
		
		//long timeMillisStart = System.currentTimeMillis(); //���Ԃ�[ms]�Ŏ擾
		//float[] ut_value = new float[1]; //�����f�[�^�ۑ��p
		//EV3UltrasonicSensor us1 = new EV3UltrasonicSensor(SensorPort.S3); //�����g�Z���T�[�̃C���X�^���X���|�[�g3�Ő���
		//float distance = 10; //�f�t�H���g�̋���(�߂Â��ꍇ�͓K���ɑ傫�����Ă���)
		
		//Delay.msDelay(10000); //������x�点��[ms]
		
		//while(distance > 0.4){ //������0.4[m]���傫���Ԃ͎��s����
   		Motor.B.setSpeed((int)velocity * 50); //Default = 200
        Motor.A.setSpeed((int)velocity * 50); //Default = 200
        Motor.B.forward(); //���[�^�[�𓮂���
        Motor.A.forward(); //���[�^�[�𓮂���
   		//us1.getDistanceMode().fetchSample(ut_value, 0); //�������擾
   		//distance = ut_value[0]; //�������i�[
   		//}
		
		//Motor.B.stop(); //���[�^�[���~
        //Motor.A.stop(); //���[�^�[���~
	}
}