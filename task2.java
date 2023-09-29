import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
class SupermarketBillingSystem extends JFrame {
    private DefaultListModel<String> cartModel;
    private JList<String> cartList;
    private JTextField productField, priceField, quantityField, totalField;
    private JButton addButton, removeButton, checkoutButton;
    private double totalAmount = 0;

    public SupermarketBillingSystem() {
        setTitle("Supermarket Billing System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create a cart list
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);

        // Create a panel for product details
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new GridLayout(4, 2));

        productPanel.add(new JLabel("Product Name:"));
        productField = new JTextField();
        productPanel.add(productField);

        productPanel.add(new JLabel("Price (per unit):"));
        priceField = new JTextField();
        productPanel.add(priceField);

        productPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        productPanel.add(quantityField);

        productPanel.add(new JLabel("Total Amount:"));
        totalField = new JTextField();
        totalField.setEditable(false);
        productPanel.add(totalField);

        // Create buttons
        addButton = new JButton("Add to Cart");
        removeButton = new JButton("Remove from Cart");
        checkoutButton = new JButton("Checkout");

        // Add action listeners to buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String product = productField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                double total = price * quantity;

                // Add item to cart and update total amount
                cartModel.addElement(product + " - $" + total);
                totalAmount += total;
                totalField.setText("$" + totalAmount);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!cartList.isSelectionEmpty()) {
                    String selectedItem = cartList.getSelectedValue();
                    String[] parts = selectedItem.split(" - \\$");
                    double total = Double.parseDouble(parts[1]);

                    // Remove item from cart and update total amount
                    cartModel.removeElement(selectedItem);
                    totalAmount -= total;
                    totalField.setText("$" + totalAmount);
                }
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Implement checkout logic (e.g., print receipt)
                JOptionPane.showMessageDialog(null, "Checkout completed. Total Amount: $" + totalAmount);
                // Clear the cart and reset the total amount
                cartModel.clear();
                totalAmount = 0;
                totalField.setText("$" + totalAmount);
            }
        });

        // Add components to the frame
        add(productPanel, BorderLayout.NORTH);
        add(new JScrollPane(cartList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(checkoutButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SupermarketBillingSystem().setVisible(true);
            }
        });
    }
}

