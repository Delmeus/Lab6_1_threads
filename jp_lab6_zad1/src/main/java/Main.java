import javax.swing.*;
import java.util.ArrayList;

public class Main implements Runnable {

    private final ArrayList<Thread> threads = new ArrayList<>();

    private void createThreads(int numberOfCircles, CirclePanel circlePanel){

        for(int i = 0; i < numberOfCircles; i++){

            int currentId = i;
            Thread thread = new Thread(()->{
                if(numberOfCircles == 1){
                    while (Thread.currentThread() == threads.get(currentId)) {
                        circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);

                        circlePanel.repaint();

                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
                }

                if (numberOfCircles == 2) { // do ogarniecia

                    while (Thread.currentThread() == threads.get(currentId)) {
//
//                        if (currentId == 0) {
//                            if (circlePanel.touchesOtherCircle(currentId, 1)) {
//                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
//                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);
//
//                                circlePanel.repaint();
//                            }
//                        } else {
//                            //if (circlePanel.touchesOtherCircle(currentId, 0)) {
//                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
//                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);
//
//                                circlePanel.repaint();
//                           // }
//                        }
//
//                        try {
//                            Thread.sleep(10);
//                        } catch (InterruptedException e) {
//                            System.out.println("Exception: " + e.getMessage());
//                        }
                        boolean canMove = false;
                        double angle1;
                        double angle2;
                        if(currentId == 0) {
                            angle1 = circlePanel.circles.get(0).getAngle();
                            angle2 = circlePanel.circles.get(1).getAngle();
                        }
                        else {
                            angle1 = circlePanel.circles.get(1).getAngle();
                            angle2 = circlePanel.circles.get(0).getAngle();
                        }

                        if (Math.sqrt(Math.pow((circlePanel.circles.get(0).getPosition().getX() - circlePanel.circles.get(1).getPosition().getX()), 2)
                                + Math.pow((circlePanel.circles.get(0).getPosition().getY() - circlePanel.circles.get(1).getPosition().getY()), 2)) > 40) {
                            canMove = true;
                        } else if (angle1 < 10 && angle2 > 350) canMove = true;
                        else if (angle1 > 350 && angle2 < 10) canMove = true;
                        else if (angle1 > angle2) canMove = true;
                        else //{
                            canMove = false;
                            //System.out.println("Cant move: angle 1 = " + angle1 + "; angle 2 = " + angle2 );
                        //}

                        if (canMove) {
                            circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                    + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);
                            circlePanel.repaint();

                        }
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println("Exception: " + e.getMessage());
                        }
                    }
                }

                else{
                    while (Thread.currentThread() == threads.get(currentId)){

                        if(currentId == numberOfCircles - 1){
                            if(circlePanel.touchesOtherCircle(currentId,0)){
                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);

                                circlePanel.repaint();
                            }
                        }
                        else{
                            if(circlePanel.touchesOtherCircle(currentId,currentId + 1)){
                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);

                                circlePanel.repaint();
                            }
                        }

                        try{
                            Thread.sleep(10);
                        }
                        catch (InterruptedException e){
                            System.out.println ( "Exception: " + e.getMessage() );
                        }
                    }
            }});

            threads.add(thread);
            thread.start();
        }
    }

    public static void main(String[] args) {

        MyFrame frame = new MyFrame(args);
        Main main = new Main();
        int numberOfCircles = 0;

        try {
            numberOfCircles = Integer.parseInt(args[0]);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(frame, "Błędny argument wejściowy");
        }

        if (numberOfCircles > 17) numberOfCircles = 17;

        main.createThreads(numberOfCircles, frame.getCirclePanel());

    }

    @Override
    public void run() {

    }
}
