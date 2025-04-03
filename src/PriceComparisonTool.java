package src;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PriceComparisonTool {

    private static ArrayList<Product> products = new ArrayList<>();
    private static JTextField nameField;
    private static JTextField sizeField;
    private static JTextField priceField;
    private static JTable productTable;
    private static DefaultTableModel tableModel;

    // Constructor for setting up the UI
    public PriceComparisonTool() {
        // Create JFrame with specified size
        JFrame frame = new JFrame("Price Comparison Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700); // Set window size
        frame.setLocationRelativeTo(null); // Center the window

        // Set font to support English and make the font larger
        Font font = new Font("Tahoma", Font.PLAIN, 16);

        // Create JPanel for the layout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create top panel for user input (name, size, price)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 5, 5)); // Grid for inputs
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create input fields for product name, size, and price
        JLabel nameLabel = new JLabel("Product Name:");
        nameLabel.setFont(font);
        nameField = new JTextField(20);
        nameField.setFont(font);

        JLabel sizeLabel = new JLabel("Product Size (units):");
        sizeLabel.setFont(font);
        sizeField = new JTextField(20);
        sizeField.setFont(font);

        JLabel priceLabel = new JLabel("Product Price (THB):");
        priceLabel.setFont(font);
        priceField = new JTextField(20);
        priceField.setFont(font);

        // Create Add Product button
        JButton addButton = new JButton("Add Product");
        addButton.setBackground(new Color(30, 144, 255)); // Blue button color
        addButton.setForeground(Color.WHITE);
        addButton.setFont(font);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });

        // Add components to the input panel
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(sizeLabel);
        inputPanel.add(sizeField);
        inputPanel.add(priceLabel);
        inputPanel.add(priceField);
        inputPanel.add(new JLabel()); // Empty label for alignment
        inputPanel.add(addButton);

        // Create JTable for displaying the products
        tableModel = new DefaultTableModel(new String[]{"Product Name", "Size (units)", "Price (THB)", "Price per Unit (THB)"}, 0);
        productTable = new JTable(tableModel);
        productTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Create Compare Prices button
        JButton compareButton = new JButton("Compare Prices");
        compareButton.setBackground(new Color(34, 139, 34)); // Green button color
        compareButton.setForeground(Color.WHITE);
        compareButton.setFont(font);
        compareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comparePrices();
            }
        });

        // Add panels to the frame
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(compareButton, BorderLayout.SOUTH);

        // Set up frame content
        frame.add(panel);
        frame.setVisible(true);
    }

    // Method to add a product
    private void addProduct() {
        String name = nameField.getText();
        String sizeText = sizeField.getText();
        String priceText = priceField.getText();

        if (name.isEmpty() || sizeText.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter all fields: product name, size, and price.");
            return;
        }

        try {
            double size = Double.parseDouble(sizeText);
            double price = Double.parseDouble(priceText);

            if (size <= 0 || price <= 0) {
                JOptionPane.showMessageDialog(null, "Size and price must be greater than 0.");
                return;
            }

            Product product = new Product(name, size, price);
            products.add(product);

            // Add product to the table
            tableModel.addRow(new Object[]{product.getName(), product.getSize(), product.getPrice(), product.getPricePerUnit()});

            // Clear input fields
            nameField.setText("");
            sizeField.setText("");
            priceField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for size and price.");
        }
    }

    // Method to compare prices and show the cheapest product
    private void comparePrices() {
        if (products.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No products to compare.");
            return;
        }

        Product cheapestProduct = products.get(0);
        for (Product product : products) {
            if (product.getPricePerUnit() < cheapestProduct.getPricePerUnit()) {
                cheapestProduct = product;
            }
        }

        JOptionPane.showMessageDialog(null, "The cheapest product is: " + cheapestProduct.getName() +
                "\nPrice: " + cheapestProduct.getPrice() + " THB" +
                "\nPrice per unit: " + cheapestProduct.getPricePerUnit() + " THB/unit");
    }

    // Main method to run the application
    public static void main(String[] args) {
        new PriceComparisonTool();  // Create an instance of the PriceComparisonTool
    }
}
