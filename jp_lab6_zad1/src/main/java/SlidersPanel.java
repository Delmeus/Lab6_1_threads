import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;

public class SlidersPanel extends JPanel {

    public ArrayList<JSlider> sliders = new ArrayList<>();
    private final ArrayList<Circle> circles;

    public SlidersPanel(int width, int height, int numberOfCircles, ArrayList<Circle> circles){
        this.setPreferredSize(new Dimension(width, height));
        this.circles = circles;

        for(int i = 0; i < numberOfCircles; i++){

            JSlider jSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
            jSlider.addChangeListener(new SliderAdjusted());
            jSlider.setName(String.valueOf(i));
            jSlider.setValue((int) circles.get(i).getSpeed());

            sliders.add(jSlider);
            this.add(jSlider);

        }
    }

    class SliderAdjusted implements ChangeListener {

        @Override
        public void stateChanged(ChangeEvent e){
            JSlider source = (JSlider)e.getSource();
            int sliderID = 0;
            for(JSlider slider: sliders){
                if(slider == source){
                    circles.get(sliderID).setSpeed(slider.getValue());
                    break;
                }
                sliderID++;
            }

        }
    }

}
