import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {
    JButton startButton;
    JComboBox<String> boardSelectBox;

    public Menu() {
        setTitle("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLUE);
        menuPanel.setLayout(new GridLayout(3, 1));
        add(menuPanel);

        JLabel titleLabel = new JLabel("TicTacToe", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        menuPanel.add(titleLabel);

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        menuPanel.add(startButton);

        JLabel selectLabel = new JLabel("Select Board:");
        selectLabel.setForeground(Color.WHITE);
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        menuPanel.add(selectLabel);

        boardSelectBox = new JComboBox<String>(new String[]{"Board 1"});
        boardSelectBox.setSelectedIndex(0);
        menuPanel.add(boardSelectBox);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            String selectedBoard = (String) boardSelectBox.getSelectedItem();
            tictactoe tictactoe = new tictactoe();
            tictactoe.setVisible(true);
            dispose();
        }
    }

    public static void main(String[] args) {
        new Menu();
    }

    public void run() {
    }
}
