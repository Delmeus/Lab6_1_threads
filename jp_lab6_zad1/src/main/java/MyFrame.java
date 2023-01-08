import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame{

    private final CirclePanel circlePanel;

    public MyFrame(String[] args){

        int numberOfCircles;

        try {
            numberOfCircles = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "Błędny argument wejściowy");
            numberOfCircles = 0;
        }

        if (numberOfCircles > 17){
            JOptionPane.showMessageDialog(this, "Maksymalna ilosc kółek to 17");
            numberOfCircles = 17;
        }

        JPanel mainPanel = new JPanel();
        circlePanel = new CirclePanel(600, 600, numberOfCircles);
        JPanel panelRight = new SlidersPanel(200, 600, numberOfCircles, circlePanel.circles);

        mainPanel.add(circlePanel, BorderLayout.WEST);
        mainPanel.add(panelRight, BorderLayout.EAST);

        this.add(mainPanel);

        this.setTitle("Zadanie 1");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);


    }

    public CirclePanel getCirclePanel() {
        return circlePanel;
    }
}

