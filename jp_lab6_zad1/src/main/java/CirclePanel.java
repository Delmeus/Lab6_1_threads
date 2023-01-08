import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class CirclePanel extends JPanel {

    public ArrayList<Circle> circles;

    public CirclePanel(int width, int height, int numberOfCircles){

        this.setPreferredSize(new Dimension(width, height));

        circles = new ArrayList<>();

        distributeCircles(numberOfCircles);

        repaint();

    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawOval(30,30,500,500);

        for(Circle circle : circles){
            circle.createPosition();
            g.setColor(circle.getColor());
            drawCircle(g,  circle.getPosition().getX(), circle.getPosition().getY(),20);
        }

    }

    public void drawCircle(Graphics g, int xCenter, int yCenter, int r) {
        g.fillOval(xCenter-r, yCenter-r, 2*r, 2*r);
    }

    private void distributeCircles ( int numberOfCircles ){

        double angle = 360.0/(numberOfCircles);
        Random random = new Random();
        for(int i = 0; i < numberOfCircles; i++){
            double newAngle = angle * i * (random.nextDouble(0.5) + 0.5) + random.nextDouble(3);
            Circle circle = new Circle(i, newAngle);
            circles.add(circle);
        }

        for(int i = 0; i < numberOfCircles; i++){
            for(int j = 0; j < numberOfCircles - 1; j++){
                if(circles.get(j).getAngle() > circles.get(j+1).getAngle()){
                    Circle circle = circles.get(j);
                    circles.set(j, circles.get(j+1));
                    circles.set(j + 1, circle);
                }
            }
        }
    } //początkowe ustawienie kółek

    public boolean touchesOtherCircle(int i, int j){
        double distance = Math.sqrt(Math.pow((circles.get(i).getPosition().getX() - circles.get(j).getPosition().getX()),2 )
                + Math.pow((circles.get(i).getPosition().getY() - circles.get(j).getPosition().getY()),2 ));

        return !(distance <= 40);
    }
}
