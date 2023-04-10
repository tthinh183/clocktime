package clock;

import java.time.LocalTime;
import java.util.Observable;

public class Clock extends Observable implements Runnable {
    private int second;
    private int minute;
    private int hour;
    public Clock(){
        update();
        Thread t = new Thread(this);
        t.start();
    }
    public void set(int hour,int minute,int second){
        this.hour=hour;
        this.minute=minute;
        this.second=second;
        this.setChanged();
        this.notifyObservers();
    }
    public void update(){
        LocalTime time = LocalTime.now();
        set(time.getHour(),time.getMinute(), time.getSecond());
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    @Override
    public void run() {
        try {
            while(true){
                this.update();
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
