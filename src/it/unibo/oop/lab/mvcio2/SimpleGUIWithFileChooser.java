package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    /*
     * 
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface. Suggestion: use a second JPanel with a second
     * BorderLayout, put the panel in the North of the main panel, put the text
     * field in the center of the new panel and put the button in the line_end of
     * the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the current
     * selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should use
     * the method showSaveDialog() to display the file chooser, and if the result is
     * equal to JFileChooser.APPROVE_OPTION the program should set as new file in
     * the Controller the file chosen. If CANCEL_OPTION is returned, then the
     * program should do nothing. Otherwise, a message dialog should be shown
     * telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to update
     * the UI: in this example the UI knows when should be updated, so try to keep
     * things separated.
     */

    private static final int PROPORTION = 5;
    private final Controller controller = new Controller();

    SimpleGUIWithFileChooser() {
        final JFrame frame = new JFrame();
        /* Frame usual setup */
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);

        /* Create a mainPanel with BorderLayout */
        final JPanel mainPanel = new JPanel(new BorderLayout());
        frame.add(mainPanel);

        /* Put topPanel in NORTH of mainPanel */
        final JPanel topPanel = new JPanel(new BorderLayout());
        // topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        /* Put a textfield and a button in the topPanel */
        final JButton browseButton = new JButton("Browse...");
        final JTextField filePath = new JTextField();
        filePath.setEditable(false);
        topPanel.add(browseButton, BorderLayout.LINE_END);
        topPanel.add(filePath, BorderLayout.CENTER);

        /*
         * Handlers
         */
        browseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser filePrompt = new JFileChooser();
                final int result = filePrompt.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(filePrompt.getSelectedFile());
                    filePath.setText(controller.getFilePath());
                } else if (result == JFileChooser.ERROR_OPTION) {
                    JOptionPane.showMessageDialog(frame, "An error has occurred");
                }
            }
        });

        /* Show the main frame */
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new SimpleGUIWithFileChooser();
    }
}
