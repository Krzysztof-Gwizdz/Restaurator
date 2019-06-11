package pl.polkod.restaurator.GUI.components;

import pl.polkod.restaurator.GUI.MainFrame;

import javax.swing.*;
import java.awt.*;

public class LoginScreen extends JPanel {

    Login loginPanel;
    LoginSidePanel loginSidePanel;
    private MainFrame parent;

    public LoginScreen(MainFrame parent){
        this.parent = parent;
        setLayout(new GridBagLayout());
        loginPanel = new Login(this);
        loginSidePanel = new LoginSidePanel();

        GridBagConstraints loginPanelConstraints = new GridBagConstraints();
        loginPanelConstraints.gridx =0;
        loginPanelConstraints.gridy =0;
        loginPanelConstraints.gridwidth =4;
        loginPanelConstraints.gridheight = 1;
        loginPanelConstraints.weightx = 100;
        loginPanelConstraints.weighty = 100;
        loginPanelConstraints.fill = GridBagConstraints.BOTH;
        add(loginPanel, loginPanelConstraints);

        GridBagConstraints loginSidePanelConstraints = new GridBagConstraints();
        loginSidePanelConstraints.gridx = 4;
        loginSidePanelConstraints.gridy = 0;
        loginSidePanelConstraints.gridwidth = 1;
        loginSidePanelConstraints.gridheight = 1;
        loginSidePanelConstraints.weighty = 100;
        loginSidePanelConstraints.weightx = 100;
        loginSidePanelConstraints.insets.left = 50;
        loginSidePanelConstraints.insets.right = 50;
        loginSidePanelConstraints.insets.bottom = 50;
        loginSidePanelConstraints.insets.top = 50;
        loginSidePanelConstraints.fill = GridBagConstraints.BOTH;
        add(loginSidePanel, loginSidePanelConstraints);
    }

    void logIn(String password){
        parent.logIn(password);
    }
}
