package clock;

import javax.swing.*;

public class Main extends JFrame {
    private static final long serialVersionID = 1L;
    private JPanel clock;
    public Main(String name, JPanel clock){
        super(name);
        this.add(clock);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

    public static void main(String[] args) {
        Clock data = new Clock();
       // new Main("Đồng hồ truyền thống", new AnalogClock(data));
        new Main("Đồng hồ điện tử", new DigitalClock(data));
    }
}
