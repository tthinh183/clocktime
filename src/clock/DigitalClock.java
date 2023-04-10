package clock;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class DigitalClock extends JPanel implements Observer {
    private Observable subject;
    JLabel label;
    private int hour = -1;
    private int minute = -1;
    private int second = -1;
    public static int SIZE = 50;
    @Override
    public void update(Observable o, Object arg) {
        if(!(o instanceof Clock)) return;
        Clock clock = (Clock) o;
        this.hour=clock.getHour();
        this.minute=clock.getMinute();
        this.second=clock.getSecond();
        display();
    }

    private void display() {
        this.label.setText(String.format("%s:%s:%s",hour,minute,second));
    }

    public DigitalClock(Observable subject){
        super();
        this.subject=subject;
        subject.addObserver(this);
        this.label = new JLabel();
        this.label.setFont(new Font("Consolas", Font.BOLD,SIZE));
        update(subject,null);
        this.add(label);
        this.setSize(new Dimension(label.getWidth()+20,label.getHeight()+20));
    }
}
