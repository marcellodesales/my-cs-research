package obliquelaunch;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class JSliderText extends JPanel {

    public JSlider slider;
    public JTextField text;

	/**
	 * Creates a new JSliderText with the initialText in the field, the max
	 * and min values into the slider and the interval of the Jslider.
	 */
    public JSliderText(String initialText, int min, int max, int interval) {

        text   = new JTextField(initialText, 3);
        slider = new JSlider(SwingConstants.HORIZONTAL, min, max, interval);
        slider.setMajorTickSpacing(10);
        slider.setPaintTicks(true);
		slider.setToolTipText("Intervalo do Ângulo desejado");
		slider.setSize(2,0);
     
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged( ChangeEvent e){
                text.setText("" + slider.getValue() );
                repaint(); }
        });

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        add(text);
        add(slider);
    }
	
}
