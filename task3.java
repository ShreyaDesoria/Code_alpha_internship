import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField textField;
    private String input = "";
    private String operator = "";
    private double num1, num2, result;

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        add(textField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        String[] buttons = {
                "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+",
                "sin", "cos", "tan", "ln", "sqrt", "x^2", "1/x", "(", ")"
        };

        for (String buttonLabel : buttons) {
            JButton button = new JButton(buttonLabel);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.matches("[0-9.]")) {
            input += command;
            textField.setText(input);
        } else if (command.equals("sin") || command.equals("cos") || command.equals("tan")
                || command.equals("ln") || command.equals("sqrt") || command.equals("x^2") || command.equals("1/x")) {
            operator = command;
            calculateUnary();
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            operator = command;
            num1 = Double.parseDouble(input);
            input = "";
        } else if (command.equals("=")) {
            num2 = Double.parseDouble(input);
            calculateBinary();
            operator = "";
        } else if (command.equals("(")) {
            operator = command;
            input += operator;
            textField.setText(input);
        } else if (command.equals(")")) {
            operator = command;
            input += operator;
            textField.setText(input);
        }
    }

    private void calculateBinary() {
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    result = Double.NaN; // Division by zero
                }
                break;
        }
        textField.setText(Double.toString(result));
        input = Double.toString(result);
    }

    private void calculateUnary() {
        switch (operator) {
            case "sin":
                result = Math.sin(Double.parseDouble(input));
                break;
            case "cos":
                result = Math.cos(Double.parseDouble(input));
                break;
            case "tan":
                result = Math.tan(Double.parseDouble(input));
                break;
            case "ln":
                result = Math.log(Double.parseDouble(input));
                break;
            case "sqrt":
                result = Math.sqrt(Double.parseDouble(input));
                break;
            case "x^2":
                result = Math.pow(Double.parseDouble(input), 2);
                break;
            case "1/x":
                if (Double.parseDouble(input) != 0) {
                    result = 1 / Double.parseDouble(input);
                } else {
                    result = Double.NaN; // Division by zero
                }
                break;
        }
        textField.setText(Double.toString(result));
        input = Double.toString(result);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ScientificCalculator().setVisible(true);
            }
        });
    }
}
