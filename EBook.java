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

 public class EBook extends Book {
     private String fileSize;
     private String format;
 
     public EBook(String title, String author, String category, int publicationYear, String fileSize, String format) {
         super(title, author, category, publicationYear);
         this.fileSize = fileSize;
         this.format = format;
     }
 
     public String getFileSize() {
         return fileSize;
     }
 
     public String getFormat() {
         return format;
     }
 
     @Override
     public String toString() {
         return super.toString() + ", File Size: " + fileSize + ", Format: " + format;
     }
 }
 