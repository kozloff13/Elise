import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * created by kozloff 3.05.2018
 *
 * Чатбот v 1.51
 *
 * Дополнительный функционал помечен соответствующими комментариями (*)
 *
 * Класс основного окна и точка входа в программу
 */

class Main extends JFrame implements ActionListener {

    private final String TITLE_OF_PROGRAM = "T-1000: Bender mode";
    private final int WINDOW_WIDTH = 650;
    private final int WINDOW_HEIGHT = 450;

    private JTextArea dialogue;
    private JCheckBox ai;
    private JTextField message;

    private Controller sbot;

    public static void main(String[] args) {
        new Main();
    }

    private Main() {

        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);

        dialogue = new JTextArea();
        dialogue.setLineWrap(true);
        JScrollPane scrollbar = new JScrollPane(dialogue);

        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));

        ai = new JCheckBox("Злой режим");

        message = new JTextField();
        message.addActionListener(this);

        JButton enter = new JButton("Enter");
        enter.addActionListener(this);

        bp.add(ai);
        bp.add(message);
        bp.add(enter);

        add(BorderLayout.CENTER, scrollbar);
        add(BorderLayout.SOUTH, bp);

        setVisible(true);

        sbot = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (message.getText().trim().length() > 0) {
            dialogue.append(message.getText() + "\n");
            dialogue.append(TITLE_OF_PROGRAM.substring(0, 8) +
                    sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}