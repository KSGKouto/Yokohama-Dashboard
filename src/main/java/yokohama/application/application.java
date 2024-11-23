package yokohama.application;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import yokohama.components.MainForm;

import javax.swing.*;
import java.awt.*;

public class application extends JFrame {

    private MainForm mainForm= new MainForm();

    public application() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
        getContentPane().add(mainForm);
    }

    public static void main (String [] args) {
        FlatRobotoFont.install();
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> new application().setVisible(true));
    }
}
