package is.shapes.graphiccomponents;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JDoubleTextField extends JFormattedTextField {

    private double lastDouble = 0.0;

    public JDoubleTextField()
    {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyReleased();
            }
        });
    }

    private void handleKeyReleased() {
        String text = this.getText();
        if (text.isEmpty()) return;

        try {
            lastDouble = Double.parseDouble(text);
        } catch (NumberFormatException ex) {
            this.setText(lastDouble + ""); // or set to other values you want
        }
    }


}




