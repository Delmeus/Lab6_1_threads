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

                        if (currentId == 0) {
                            if (circlePanel.touchesOtherCircle(currentId, 1)) {
                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);

                                circlePanel.repaint();
                            }
                        } else {
                            //if (circlePanel.touchesOtherCircle(currentId, 0)) {
                                circlePanel.circles.get(currentId).setAngle((circlePanel.circles.get(currentId).getAngle()
                                        + circlePanel.circles.get(currentId).getSpeed() / 100) % 360);

                                circlePanel.repaint();
                           // }
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
