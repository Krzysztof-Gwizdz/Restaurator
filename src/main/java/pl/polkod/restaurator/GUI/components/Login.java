package pl.polkod.restaurator.GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JPanel {

    private LoginScreen parent;
    private JPanel panel;
    private JPasswordField pinCodeField;
    private static Font font = new Font(Font.SANS_SERIF, Font.BOLD, 32);


    public Login(LoginScreen parent) {
        this.parent=parent;
        setLayout(new GridBagLayout());
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 3));

        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);

        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);

        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);

        addButton("X", command);
        addButton("0", insert);
        addButton("Login", command);

        pinCodeField = new JPasswordField(30);
        pinCodeField.setFont(font);
        pinCodeField.setEchoChar('•');

        GridBagConstraints displayConstraints = new GridBagConstraints();
        displayConstraints.gridwidth = 1;
        displayConstraints.gridheight = 1;
        displayConstraints.gridx = 0;
        displayConstraints.gridy = 0;
        displayConstraints.fill = GridBagConstraints.HORIZONTAL;
        displayConstraints.insets.top = 10;
        displayConstraints.insets.left = 50;
        displayConstraints.insets.right = 50;
        displayConstraints.weightx = 100;
        displayConstraints.weighty = 30;

        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridy = 1;
        panelConstraints.gridx = 0;
        panelConstraints.gridwidth = 1;
        panelConstraints.gridheight = 6;
        panelConstraints.fill = GridBagConstraints.BOTH;
        panelConstraints.insets.bottom = 50;
        panelConstraints.insets.left = 50;
        panelConstraints.insets.right = 50;
        panelConstraints.insets.top = 50;
        panelConstraints.weighty = 100;
        panelConstraints.weightx = 100;

        add(panel, panelConstraints);
        add(pinCodeField, displayConstraints);

    }

    String getPassValue() {
        StringBuilder passVal = new StringBuilder();
        for (char x : pinCodeField.getPassword())
            passVal.append(x);
        return passVal.toString();
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.setFont(font);
        button.addActionListener(listener);
        panel.add(button);
    }

    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            try {
                Robot robot = new Robot();
                String input = event.getActionCommand();
                pinCodeField.requestFocusInWindow();
                robot.keyPress(KeyStroke.getKeyStroke(input.charAt(0), 0).getKeyCode());
                robot.keyRelease(KeyStroke.getKeyStroke(input.charAt(0), 0).getKeyCode());
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }

    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (input.equals("X")) {
                pinCodeField.setText("");
            }
            if (input.equals("Login"))
                parent.logIn(getPassValue());
                //JOptionPane.showMessageDialog(null, getPassValue());
        }
    }
}
