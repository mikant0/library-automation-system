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
 import java.util.Arrays;
 import java.util.List;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.util.Random;
 
 public class Book {
     private static final Random random = new Random();
     private static final int MIN_ID = 1000;
     private static final int MAX_ID = 9999;
     public static final int MIN_YEAR = 0; // Minimum date
     public static final int MAX_YEAR = LocalDate.now().getYear(); // Present date
 
     // Sabit kategoriler listesi
     public static final List<String> CATEGORIES = Arrays.asList(
             "Fiction", "Non-fiction", "Science", "History", "Biography",
             "Fantasy", "Mystery", "Technology", "Self-help", "Travel"
     );
 
     private int id;
     private String title;
     private String author;
     private String category;
     private int publicationYear;
     private boolean isBorrowed;
     private LocalDate borrowDate;
     private LocalDate returnDate;
 
     public Book(String title, String author, String category, int publicationYear) {
         if (!CATEGORIES.contains(category)) {
             throw new IllegalArgumentException("Invalid category. Available categories: " + CATEGORIES);
         }if (publicationYear < MIN_YEAR || publicationYear > MAX_YEAR) {
            throw new IllegalArgumentException("Publication year must be between " + MIN_YEAR + " and " + MAX_YEAR + ".");
        }
         this.id = generateUniqueId();
         this.title = title;
         this.author = author;
         this.category = category;
         this.publicationYear = publicationYear;
         this.isBorrowed = false;
         this.borrowDate = null;
         this.returnDate = null;
     }
 
     private int generateUniqueId() {
         return MIN_ID + random.nextInt(MAX_ID - MIN_ID + 1);
     }
 
     public int getId() {
         return id;
     }
 
     public String getTitle() {
         return title;
     }
 
     public String getAuthor() {
         return author;
     }
 
     public String getCategory() {
         return category;
     }
 
     public int getPublicationYear() {
         return publicationYear;
     }
 
     public boolean isBorrowed() {
         return isBorrowed;
     }
 
     public LocalDate getBorrowDate() {
         return borrowDate;
     }
 
     public LocalDate getReturnDate() {
         return returnDate;
     }
 
     public boolean borrowBook() {
         if (!isBorrowed) {
             isBorrowed = true;
             borrowDate = LocalDate.now();
             returnDate = borrowDate.plusMonths(1);
             return true;
         } else {
             return false;
         }
     }
 
     public boolean returnBook() {
         if (isBorrowed) {
             isBorrowed = false;
             borrowDate = null;
             returnDate = null;
             return true;
         } else {
             return false;
         }
     }
 
     @Override
     public String toString() {
         return "ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Category: " + category + ", Year: " + publicationYear +
                ", Status: " + (isBorrowed ? "Borrowed" : "Available") +
                ", Return by: " + (isBorrowed ? returnDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "N/A");
     }
 }
 