
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

    class LibraryManagementSystem extends JFrame {
        private JTextField titleField, authorField, issueDateField, returnDateField;
        private JButton addButton, updateButton, deleteButton, searchButton;
        private JTextArea resultArea;

        public LibraryManagementSystem() {
            // Set up the main frame
            setTitle("Library Management System");
            setSize(400, 400);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Create and add components
            JPanel inputPanel = new JPanel();
            inputPanel.setLayout(new GridLayout(5, 2));

            inputPanel.add(new JLabel("Title:"));
            titleField = new JTextField();
            inputPanel.add(titleField);

            inputPanel.add(new JLabel("Author:"));
            authorField = new JTextField();
            inputPanel.add(authorField);

            inputPanel.add(new JLabel("Issue Date:"));
            issueDateField = new JTextField();
            inputPanel.add(issueDateField);

            inputPanel.add(new JLabel("Return Date:"));
            returnDateField = new JTextField();
            inputPanel.add(returnDateField);

            addButton = new JButton("Add");
            updateButton = new JButton("Update");
            deleteButton = new JButton("Delete");
            searchButton = new JButton("Search");

            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Implement logic to add a book record
                    String title = titleField.getText();
                    String author = authorField.getText();
                    String issueDate = issueDateField.getText();
                    String returnDate = returnDateField.getText();

                    // Add book record to the database or display a message
                    resultArea.setText("Book added: " + title);
                }
            });

            // Similar action listeners for update, delete, and search buttons

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.add(addButton);
            buttonPanel.add(updateButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(searchButton);

            resultArea = new JTextArea();
            resultArea.setEditable(false);

            add(inputPanel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.CENTER);
            add(resultArea, BorderLayout.SOUTH);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new LibraryManagementSystem().setVisible(true);
                }
            });
        }
    }

