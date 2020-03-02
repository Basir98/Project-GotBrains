package gotBrains;

import javax.swing.*;

public class ControllerTestInterface {
    private Controller controller;

    public ControllerTestInterface() {
        JFrame frame = new JFrame();
        this.controller = new Controller(frame);
    }
}
