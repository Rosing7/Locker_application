import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;

public class LockerApplication extends JFrame implements ActionListener {
    private JPasswordField PField;
    private JButton[] NButtons;
    private JButton enterButton, clearButton;
    private JLabel statusLabel;
    private String savedPassword = null;

    public LockerApplication() {
        setTitle("Lock Class");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(3, 3));
        NButtons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            NButtons[i] = new JButton(String.valueOf(i + 1));
            NButtons[i].addActionListener(this);
            mainPanel.add(NButtons[i]);
        }

        PField = new JPasswordField();
        PField.setPreferredSize(new Dimension(150, 30));
        PField.setEditable(false);

        enterButton = new JButton("Enter");
        enterButton.addActionListener(this);

        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);

        statusLabel = new JLabel("Enter Password");
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(clearButton);
        inputPanel.add(PField);
        inputPanel.add(enterButton);
        inputPanel.add(statusLabel);

        add(mainPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            String enteredPassword = new String(PField.getPassword());
            if (savedPassword == null) {
                savedPassword = enteredPassword;
                statusLabel.setText("Password Set");
            } else {
                if (enteredPassword.equals(savedPassword)) {
                    statusLabel.setText("Correct Password");
                } else {
                    statusLabel.setText("Incorrect Password");
                }
            }
            PField.setText("");
        } else if (e.getSource() == clearButton) {
            PField.setText("");
            statusLabel.setText("Enter Password");
        } else {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == NButtons[i]) {
                    PField.setText(PField.getText() + (i + 1));
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LockerApplication());
    }
}