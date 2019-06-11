package pl.polkod.restaurator.GUI.components;

import javax.swing.*;
import java.awt.*;

public class StringChooserDialog extends JPanel {
    private JTextField inputString;
    private JButton okBtn;
    private boolean ok;
    private JDialog dialog;
    private static final Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);

    public StringChooserDialog() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        JLabel label = new JLabel("Podaj tekst: ");
        label.setFont(font);
        panel.add(label);
        inputString = new JTextField("",20);
        inputString.setFont(font);
        panel.add(inputString);
        add(panel, BorderLayout.CENTER);

        okBtn = new JButton("OK");
        okBtn.addActionListener(e -> {
            ok = true;
            dialog.setVisible(false);
        });

        JButton cancelBtn = new JButton("Analuj");
        cancelBtn.addActionListener(e -> dialog.setVisible(false));

        JPanel btnPanel = new JPanel();
        btnPanel.add(okBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public String getInputString() {
        return inputString.getText();
    }

    public boolean showDialog(Component parent, String title) {
        ok = false;

        Frame owner = null;
        if (parent instanceof Frame) {
            owner = (Frame) parent;
        } else {
            owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
        }

        if (dialog == null || dialog.getOwner() != owner){
            dialog = new JDialog(owner, true);
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okBtn);
            dialog.pack();
        }

        dialog.setTitle(title);
        dialog.setVisible(true);

        return ok;
    }
}
