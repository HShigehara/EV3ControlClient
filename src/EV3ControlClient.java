/*
  TimerTaskを二つ動作させる例
  以下のようにすると、t1, t2 が並列で動作する
  
  schedule は割り込み間隔が次第に遅延していく
  scheduleAtFixedRate は当初の割り込み間隔を守ろうとする
  当然後者を利用しましょう。
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
	    System.out.println("error :" + e);
	}

	// タスクの停止
	t1.cancel();
	t2.cancel();

    }
}