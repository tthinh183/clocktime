package clock;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")
public class AnalogClock extends JPanel implements Observer {
    private Observable subject;
    private int hour = 9;
    private int minute = 0;
    private int second = 0;
    public static int SIZE = 300;
    public AnalogClock(Observable subject){
        super();
        this.subject=subject;
        subject.addObserver(this);
        this.setPreferredSize(new Dimension(SIZE+20,SIZE+20));
        update(subject,null);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!(o instanceof Clock)) return;
        Clock clock = (Clock) o;
        this.hour= clock.getHour();
        this.minute=clock.getMinute();
        this.second=clock.getSecond();
        repaint();
    }
    public Point center(){
        int x = (int) (this.getHeight()/2);
        int y = (int) (this.getWidth()/2);
        return new Point(x,y);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        drawOval(g2);
        drawCH(g2);
        drawCM(g2);
        drawCS(g2);
        g2.dispose();
    }
    public void drawOval(Graphics g){
        int radius = (50*SIZE/100);
        Point center = center();
        center.x -= radius;
        center.y -= radius;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.white);
        g2.fillOval(center.x, center.y, SIZE, SIZE);
        g2.setColor(Color.black);
        g2.drawOval(center.x, center.y, SIZE, SIZE);
        g2.dispose();
    }
    private void drawCH(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
        Point center = center();
        int length = SIZE / 2 * 50 / 100;
        int hA = ((hour % 12) * 30 + minute / 2);
        int hX = (int) (center.x + length * Math.sin(Math.toRadians(hA)));
        int hY = (int) (center.y - length * Math.cos(Math.toRadians(hA)));
        g2.drawLine(center.x, center.y, hX, hY);
        g2.dispose();
    }

    private void drawCM(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.black);
        Point center = center();
        int length = SIZE / 2 * 70 / 100;
        int mA = (minute * 6 + second / 10);
        int mX = (int) (center.x + length * Math.sin(Math.toRadians(mA)));
        int mY = (int) (center.y - length * Math.cos(Math.toRadians(mA)));
        g2.drawLine(center.x, center.y, mX, mY);
        g2.dispose();
    }

    private void drawCS(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setStroke(new BasicStroke(1));
        g2.setColor(Color.red);
        Point center = center();
        int length = SIZE / 2 * 90 / 100;
        int sA = (second * 6);
        int sX = (int) (center.x + length * Math.sin(Math.toRadians(sA)));
        int sY = (int) (center.y - length * Math.cos(Math.toRadians(sA)));
        g2.drawLine(center.x, center.y, sX, sY);
        g2.dispose();
    }

}
