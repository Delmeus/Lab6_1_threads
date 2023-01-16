import java.awt.*;
import java.util.Random;

public class Circle extends Thread{

    private double speed;
    private double angle;
    private final Point position;
    private Color color;

    Circle(int index, double angle){

        this.angle = angle;

        Random random = new Random();
        speed = random.nextDouble(360d);
        //speed = 0;

        if(index%3 == 0) color = new Color(15 * index, 10 * index, 5 * index);

        if(index%3 == 1) color = new Color(10 * index, 5 * index, 15 * index);

        if(index%3 == 2) color = new Color(5 * index, 15 * index, 10 * index);

        position = new Point(0,0);
        createPosition();

    }

    public void createPosition(){
        double newAngle = Math.toRadians(angle%360);
        int startingX = 280; //250 + 30 ponieważ promien okręgu to 250 z środkiem na współrzędnych (30, 30)
        int startingY = 280;
        int x = startingX + (int) (250 * Math.sin(newAngle));
        int y = startingY + (int) (250 * Math.cos(newAngle));
        position.setX(x);
        position.setY(y);
    }

    public Point getPosition() {
        return position;
    }

    public double getAngle() {
        return angle;
    }

    public Color getColor() {
        return color;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
