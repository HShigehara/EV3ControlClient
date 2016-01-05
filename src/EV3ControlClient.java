/*
 * EV3ControlClient.java
 * EV3を制御するクライアント(EV3)側のメインクラス．
 */
import java.util.Timer;

public class EV3ControlClient{
    public static void main(String[] args){
	Timer t1 = new Timer();
	Timer t2 = new Timer();

	t1.schedule(new TimeSchedulingClient("First"), 1000, 100);
	t2.scheduleAtFixedRate(new TimeSchedulingClient("Second"), 1000, 100);

	// 5000 [msec]だけ動作
	try{
	    Thread.sleep(5000);
	} catch(InterruptedException e){
	    System.out.println("error : " + e);
	}

	// タスクの停止
	t1.cancel();
	t2.cancel();

    }
}