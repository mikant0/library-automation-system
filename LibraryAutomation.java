/*
 * Library Automation System Project
 * Group Members:
 * Muhammed Hüseyin Can İyibaş - 230610003
 * Enes Gökalp - 220610015
 * Emir Furkan Biçen - 220610004
 * Burak Gemicioğlu - 220611024
 * Mustafa Kemal Demiralay - 220611032
 */

 package LibraryAutomationSystem;

 import javax.swing.*;
 import java.awt.*;
 
 public class LibraryAutomation {
     private Library library;
 
     public LibraryAutomation() {
         library = new Library();
         createGUI();
     }
 
     private void createGUI() {
         JFrame frame = new JFrame("Library Automation System");
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setSize(800, 600);
         frame.setLayout(new BorderLayout());
 
         // Background color
         Color backgroundColor = new Color(245, 245, 220);
         frame.getContentPane().setBackground(backgroundColor);
 
         // Header
         JLabel headerLabel = new JLabel("Library Automation System", JLabel.CENTER);
         headerLabel.setFont(new Font("Serif", Font.BOLD, 30));
         headerLabel.setForeground(new Color(139, 69, 19)); // Brown
         frame.add(headerLabel, BorderLayout.NORTH);
 
         // Display area
         JTextArea displayArea = new JTextArea();
         displayArea.setEditable(false);
         displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
         displayArea.setBackground(Color.WHITE);
         displayArea.setBorder(BorderFactory.createLineBorder(new Color(139, 69, 19), 2));
         JScrollPane scrollPane = new JScrollPane(displayArea);
         frame.add(scrollPane, BorderLayout.CENTER);
 
         // Button panel
         JPanel buttonPanel = new JPanel(new GridLayout(4, 2, 15, 15));
         buttonPanel.setBackground(backgroundColor);
         buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
 
         JButton addButton = new JButton("Add Book");
         JButton addEBookButton = new JButton("Add EBook");
         JButton listButton = new JButton("List Books");
         JButton borrowButton = new JButton("Borrow Book");
         JButton returnButton = new JButton("Return Book");
         JButton searchButton = new JButton("Search Book");
         JButton categoryListButton = new JButton("List by Category");
         JButton themeButton = new JButton("Change Theme");
 
         Font buttonFont = new Font("Serif", Font.BOLD, 18);
         Color buttonColor = new Color(210, 180, 140);
         JButton[] buttons = {addButton, addEBookButton, listButton, borrowButton, returnButton, searchButton, categoryListButton, themeButton};
         for (JButton button : buttons) {
             button.setFont(buttonFont);
             button.setBackground(buttonColor);
             button.setForeground(Color.BLACK);
             button.setFocusPainted(false);
         }
 
         buttonPanel.add(addButton);
         buttonPanel.add(addEBookButton);
         buttonPanel.add(listButton);
         buttonPanel.add(borrowButton);
         buttonPanel.add(returnButton);
         buttonPanel.add(searchButton);
         buttonPanel.add(categoryListButton);
         buttonPanel.add(themeButton);
 
         frame.add(buttonPanel, BorderLayout.SOUTH);
 
         // Add Book functionality
         addButton.addActionListener(e -> {
             String title = JOptionPane.showInputDialog("Enter book title:");
             String author = JOptionPane.showInputDialog("Enter book author:");
             String category = (String) JOptionPane.showInputDialog(
                     frame,
                     "Select category:",
                     "Category",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     Book.CATEGORIES.toArray(),
                     Book.CATEGORIES.get(0)
             );
             int year;
    while (true) {
        try {
            year = Integer.parseInt(JOptionPane.showInputDialog("Enter publication year:"));
            if (year >= Book.MIN_YEAR && year <= Book.MAX_YEAR) {
                break;
            } else {
                JOptionPane.showMessageDialog(frame, "Publication year must be between " + Book.MIN_YEAR + " and " + Book.MAX_YEAR + ".");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid year. Please enter a valid number.");
        }
    }
 
             library.addBook(new Book(title, author, category, year));
             displayArea.setText("Book added successfully!\n" + library.listBooks());
         });
 
         // Add EBook functionality
         addEBookButton.addActionListener(e -> {
             String title = JOptionPane.showInputDialog("Enter eBook title:");
             String author = JOptionPane.showInputDialog("Enter eBook author:");
             String category = (String) JOptionPane.showInputDialog(
                     frame,
                     "Select category:",
                     "Category",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     Book.CATEGORIES.toArray(),
                     Book.CATEGORIES.get(0)
             );
             int year = Integer.parseInt(JOptionPane.showInputDialog("Enter eBook publication year:"));
             String fileSize = JOptionPane.showInputDialog("Enter eBook file size (e.g., 5MB):");
             String format = JOptionPane.showInputDialog("Enter eBook format (e.g., PDF, EPUB):");
 
             library.addBook(new EBook(title, author, category, year, fileSize, format));
             displayArea.setText("EBook added successfully!\n" + library.listBooks());
         });
 
         // List Books functionality
         listButton.addActionListener(e -> displayArea.setText(library.listBooks()));
 
         // Borrow Book functionality
         borrowButton.addActionListener(e -> {
             int id = Integer.parseInt(JOptionPane.showInputDialog("Enter book ID to borrow:"));
             displayArea.setText(library.borrowBookById(id));
         });
 
         // Return Book functionality
         returnButton.addActionListener(e -> {
             int id = Integer.parseInt(JOptionPane.showInputDialog("Enter book ID to return:"));
             displayArea.setText(library.returnBookById(id));
         });
 
         // Search Book functionality
         searchButton.addActionListener(e -> {
             String title = JOptionPane.showInputDialog("Enter book title to search:");
             displayArea.setText(library.searchBooksByTitle(title));
         });
 
         // List by Category functionality
         categoryListButton.addActionListener(e -> {
             String category = (String) JOptionPane.showInputDialog(
                     frame,
                     "Select category:",
                     "Category",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     Book.CATEGORIES.toArray(),
                     Book.CATEGORIES.get(0)
             );
             displayArea.setText(library.listBooksByCategory(category));
         });
 
         // Change Theme functionality
         themeButton.addActionListener(e -> {
             String[] themes = {"Light", "Dark"};
             String selectedTheme = (String) JOptionPane.showInputDialog(
                     frame,
                     "Select a theme:",
                     "Theme",
                     JOptionPane.QUESTION_MESSAGE,
                     null,
                     themes,
                     themes[0]
             );
             if ("Dark".equals(selectedTheme)) {
                 frame.getContentPane().setBackground(Color.DARK_GRAY);
                 displayArea.setBackground(Color.BLACK);
                 displayArea.setForeground(Color.WHITE);
             } else {
                 frame.getContentPane().setBackground(new Color(245, 245, 220));
                 displayArea.setBackground(Color.WHITE);
                 displayArea.setForeground(Color.BLACK);
             }
         });
 
         frame.setVisible(true);
     }
 
     public static void main(String[] args) {
         new LibraryAutomation();
     }
 }
 