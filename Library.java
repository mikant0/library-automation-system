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

 import java.util.ArrayList;
 
 public class Library {
     private ArrayList<Book> books;
 
     public Library() {
         books = new ArrayList<>();
     }
 
     public void addBook(Book book) {
         books.add(book);
     }
 
     public String listBooks() {
         if (books.isEmpty()) {
             return "No books available in the library.";
         }
 
         StringBuilder sb = new StringBuilder();
         for (Book book : books) {
             sb.append(book.toString()).append("\n");
         }
         return sb.toString();
     }
 
     public Book findBookById(int bookId) {
         for (Book book : books) {
             if (book.getId() == bookId) {
                 return book;
             }
         }
         return null;
     }
 
     public String borrowBookById(int bookId) {
         Book book = findBookById(bookId);
         if (book != null) {
             if (book.isBorrowed()) {
                 return "The book '" + book.getTitle() + "' is already borrowed.";
             } else {
                 book.borrowBook();
                 return "You have successfully borrowed '" + book.getTitle() + "'.";
             }
         }
         return "Error: No book found with ID: " + bookId;
     }
 
     public String returnBookById(int bookId) {
         Book book = findBookById(bookId);
         if (book != null) {
             if (!book.isBorrowed()) {
                 return "The book '" + book.getTitle() + "' was not borrowed.";
             } else {
                 book.returnBook();
                 return "You have successfully returned '" + book.getTitle() + "'.";
             }
         }
         return "Error: No book found with ID: " + bookId;
     }
 
     public String searchBooksByTitle(String title) {
         StringBuilder result = new StringBuilder();
         for (Book book : books) {
             if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                 result.append(book.toString()).append("\n");
             }
         }
         return result.length() > 0 ? result.toString() : "No books found with title: " + title;
     }
 
     public String listBooksByCategory(String category) {
         StringBuilder result = new StringBuilder();
         for (Book book : books) {
             if (book.getCategory().equalsIgnoreCase(category)) {
                 result.append(book.toString()).append("\n");
             }
         }
         return result.length() > 0 ? result.toString() : "No books found in category: " + category;
     }
 }
 