import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Random;

public class tictactoe implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    int player1_score, player2_score;
    JLabel player1_score_label, player2_score_label;

    tictactoe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(new Color(50, 50, 50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(new Color(25, 25, 25));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 75));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-Tac-Toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        player1_score_label = new JLabel("Player 1: " + player1_score);
        player1_score_label.setForeground(new Color(25, 255, 0));
        player1_score_label.setFont(new Font("Ink Free", Font.BOLD, 30));
        player1_score_label.setHorizontalAlignment(JLabel.CENTER);

        player2_score_label = new JLabel("Player 2: " + player2_score);
        player2_score_label.setForeground(new Color(255, 0, 0));
        player2_score_label.setFont(new Font("Ink Free", Font.BOLD, 30));
        player2_score_label.setHorizontalAlignment(JLabel.CENTER);

        title_panel.add(player1_score_label, BorderLayout.WEST);
        title_panel.add(textfield);
        title_panel.add(player2_score_label, BorderLayout.EAST);

        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (player1_turn) {
                    if (Objects.equals(buttons[i].getText(), "")) {
                        buttons[i].setForeground(new Color(0, 60, 255));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn");
                        check();
                    }
                } else {
                    if (Objects.equals(buttons[i].getText(), "")) {
                        buttons[i].setForeground(new Color(255, 242, 0));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    private void check() {
        for (int i = 0; i < 9; i += 3) {
            if (Objects.equals(buttons[i].getText(), buttons[i + 1].getText()) &&
                    Objects.equals(buttons[i + 1].getText(), buttons[i + 2].getText()) &&
                    !Objects.equals(buttons[i].getText(), "")) {
                gameOver(buttons[i].getText() + " wins!");
                return;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (Objects.equals(buttons[i].getText(), buttons[i + 3].getText()) &&
                    Objects.equals(buttons[i + 3].getText(), buttons[i + 6].getText()) &&
                    !Objects.equals(buttons[i].getText(), "")) {
                gameOver(buttons[i].getText() + " wins!");
                return;
            }
        }

        if (Objects.equals(buttons[0].getText(), buttons[4].getText()) &&
                Objects.equals(buttons[4].getText(), buttons[8].getText()) &&
                !Objects.equals(buttons[0].getText(), "")) {
            gameOver(buttons[0].getText() + " wins!");
            return;
        }

        if (Objects.equals(buttons[2].getText(), buttons[4].getText()) &&
                Objects.equals(buttons[4].getText(), buttons[6].getText()) &&
                !Objects.equals(buttons[2].getText(), "")) {
            gameOver(buttons[2].getText() + " wins!");
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (Objects.equals(buttons[i].getText(), "")) {
                return;
            }
        }

        gameOver("It's a tie!");
    }

    private void gameOver(String message) {
        textfield.setText(message);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }

        int choice = JOptionPane.showConfirmDialog(frame,
                "Do you want to play again?",
                "Play again?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (choice == JOptionPane.YES_OPTION) {
            // Reset game
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(true);
                buttons[i].setText("");
            }

            player1_turn = true;
            textfield.setText("X turn");
        } else {
            // Exit game
            System.exit(0);
        }
    }

    public void setVisible(boolean b) {
    }
}