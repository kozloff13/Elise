import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    private final String TITLE_OF_PROGRAM = "T-1000: Bender mode";
    private final int WINDOW_WIDTH = 650;
    private final int WINDOW_HEIGHT = 450;

    private JTextArea dialogueArea;
    private JCheckBox isEvil;
    private JTextField messageField;

    private Controller controller;

    Window() {
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);

        dialogueArea = new JTextArea();
        dialogueArea.setLineWrap(true);
        JScrollPane scrollbar = new JScrollPane(dialogueArea);

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));

        isEvil = new JCheckBox("Злой режим");

        messageField = new JTextField();
        messageField.addActionListener(this);

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);

        bp.add(isEvil);
        bp.add(messageField);
        bp.add(enter);

        add(BorderLayout.CENTER, scrollbar);
        add(BorderLayout.SOUTH, bp);

        setVisible(true);

        controller = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (messageField.getText().trim().length() > 0) {
            dialogueArea.append(messageField.getText() + "\n");
            dialogueArea.append(TITLE_OF_PROGRAM.substring(0, 8) +
                    controller.sayInReturn(messageField.getText(), isEvil.isSelected()) + "\n");
        }
        messageField.setText("");
        messageField.requestFocusInWindow();
    }
}
