package Online_Quiz_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class QuizApp extends JFrame implements ActionListener {
    // Questions, options, and answers
    private final String[] questions = {
        "Which company created Java?",
        "Which is not a Java feature?",
        "Which keyword is used for inheritance?",
        "Which method is entry point in Java app?",
        "What is extension for Java bytecode?"
    };

    private final String[][] options = {
        {"Oracle", "Sun Microsystems", "Microsoft", "Google"},
        {"Object Oriented", "Use of pointers", "Platform Independent", "Dynamic"},
        {"extends", "implement", "inherits", "super"},
        {"start()", "main()", "run()", "init()"},
        {".java", ".class", ".js", ".exe"}
    };

    private final int[] answers = {1, 1, 0, 1, 1};

    private int score = 0, cur = 0;
    private int[] userAns = new int[questions.length];

    JLabel qLabel = new JLabel();
    JRadioButton[] rb = new JRadioButton[4];
    ButtonGroup bg = new ButtonGroup();
    JButton next = new JButton("Next");
    JButton submit = new JButton("Submit");

    public QuizApp() {
        setTitle("Online Quiz System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 240);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Question
        qLabel.setFont(new Font("Arial", Font.BOLD, 15));
        add(qLabel, BorderLayout.NORTH);

        // Options
        JPanel opPanel = new JPanel(new GridLayout(4, 1));
        for(int i=0;i<4;i++) {
            rb[i] = new JRadioButton();
            bg.add(rb[i]);
            opPanel.add(rb[i]);
        }
        add(opPanel, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();
        btnPanel.add(next); btnPanel.add(submit);
        add(btnPanel, BorderLayout.SOUTH);

        next.addActionListener(this);
        submit.addActionListener(this);
        submit.setEnabled(false);

        display();

        setVisible(true);
    }

    void display() {
        bg.clearSelection();
        qLabel.setText("Q"+(cur+1)+": "+questions[cur]);
        for(int i=0;i<4;i++) rb[i].setText(options[cur][i]);
        if(cur==questions.length-1) {
            next.setEnabled(false);
            submit.setEnabled(true);
        }
    }

    public void actionPerformed(ActionEvent e){
        int selected = -1;
        for(int i=0;i<4;i++) if(rb[i].isSelected()) selected = i;
        if(selected==-1) {
            JOptionPane.showMessageDialog(this, "Please select an answer.");
            return;
        }
        userAns[cur]=selected;
        if(selected==answers[cur]) score++;

        if(e.getSource()==next) {
            cur++;
            display();
        } else if(e.getSource()==submit) {
            showScore();
        }
    }

    void showScore() {
        String user = JOptionPane.showInputDialog(this, "Quiz Finished!\nEnter your name:");
        // Save Result
        ResultStorage.saveResult(user, score, questions.length);

        // Show result & review
        StringBuilder sb = new StringBuilder("Your Score: "+score+"/"+questions.length+"\n\nReview:\n\n");
        for(int i=0;i<questions.length;i++){
            sb.append("Q").append(i+1).append(". ").append(questions[i]).append("\n   Correct: ")
              .append(options[i][answers[i]]).append(", Your answer: ").append(options[i][userAns[i]]).append("\n\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString());
        System.exit(0);
    }

    public static void main(String[] args) {
        // Show GUI in thread-safe mode
        SwingUtilities.invokeLater(QuizApp::new);
    }
}

