/*
 * EV3ControlClient.java
 * EV3�𐧌䂷��N���C�A���g(EV3)���̃��C���N���X�D
 */
import java.util.Timer;

public class EV3ControlClient{
    public static void main(String[] args){
	Timer t1 = new Timer();
	Timer t2 = new Timer();

	t1.schedule(new TimeSchedulingClient("First"), 1000, 100);
	t2.scheduleAtFixedRate(new TimeSchedulingClient("Second"), 1000, 100);

	// 5000 [msec]��������
	try{
	    Thread.sleep(5000);
	} catch(InterruptedException e){
	    System.out.println("error : " + e);
	}

	// �^�X�N�̒�~
	t1.cancel();
	t2.cancel();

    }
}