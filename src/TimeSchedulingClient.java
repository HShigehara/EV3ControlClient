/*
 * TimeSchedulingClient.java
 * �N���C�A���g(EV3)���̎��Ԃ��X�P�W���[�����O����N���X�D
 */
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimeSchedulingClient extends TimerTask{
    private long timeStart;
    private String id;

    TimeSchedulingClient(String arg){
	timeStart = System.nanoTime();
	id = arg;
    }

    public void run(){
	long timeNow = System.nanoTime() - timeStart;

	System.out.println(id +  " : " + TimeUnit.MILLISECONDS.convert(timeNow, TimeUnit.NANOSECONDS));
    }
}