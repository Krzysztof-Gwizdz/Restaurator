import pl.polkod.restaurator.GUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class TestBaza {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("Nimbus");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            JFrame frame = new MainFrame();
            frame.setTitle("RESTAURATOR");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
