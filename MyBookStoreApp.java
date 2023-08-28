package Testpack;

import java.util.*;

class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

class Book {

    int bookId;
    String title;
    String author;
    boolean isAvailable;
    String userReview;
    double totalRatings;
    int numRatings;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.userReview = "";
        this.totalRatings = 0.0;
        this.numRatings = 0;
    }

    public double calculateAverageRating() {
        if (numRatings == 0) {
            return 0.0;
        }
        return totalRatings / numRatings;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}

class BookLibrary {
    private List<Book> books;

    public BookLibrary() {
        books = new ArrayList<>();

        books.add(new Book(1, "Brave New World", "Aldous Huxley"));
        books.add(new Book(2, "The Catcher in the Rye", "J.D. Salinger"));
        books.add(new Book(3, "The Hobbit", "J.R.R. Tolkien"));
        books.add(new Book(4, "The Alchemist", "Paulo Coelho"));
        books.add(new Book(5, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(6, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(7, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(8, "The Lord of the Rings", "J.R.R. Tolkien"));
        books.add(new Book(9, "To Kill a Mockingbird", "Harper Lee"));

    }

    public List<Book> fetchAllBooks() {
        return books;
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public void addNewBook(int bookId, String title, String author) {
        books.add(new Book(bookId, title, author));
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.bookId == bookId) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }

        if (!book.isAvailable) {
            System.out.println("Book with ID " + bookId + " is not available for borrowing");
            return;
        }

        book.isAvailable = false;
        System.out.println("Book with ID " + bookId + " has been borrowed");
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }

        if (book.isAvailable) {
            System.out.println("Book with ID " + bookId + " is already available");
            return;
        }

        book.isAvailable = true;
        System.out.println("Book with ID " + bookId + " has been returned");
    }

    public void rateAndReviewBook(int bookId, int rating, String review) {
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Book with ID " + bookId + " not found");
            return;
        }
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Rating should be between 1 to 5");
            return;
        }

        book.totalRatings += rating;
        book.numRatings++;
        book.userReview = review;

        System.out.println("Book with ID " + bookId + " has been rated and reviewed");
    }

}

public class MyBookStoreApp {
    private static Scanner scanner = new Scanner(System.in);
    private static BookLibrary bookLibrary = new BookLibrary();
    private static User loggedInUser;

    public static void main(String[] args) {
        displayMainMenu();
    }

    private static void borrowBook() {
        System.out.print("Enter the Book ID you want to borrow: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        bookLibrary.borrowBook(bookId);
    }

    private static void returnBook() {
        System.out.print("Enter the Book ID you want to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        bookLibrary.returnBook(bookId);
    }

    private static void rateAndReviewBook() {
        System.out.print("Enter the Book ID you want to rate and review: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the rating (1 to 5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the review: ");
        String review = scanner.nextLine();
        bookLibrary.rateAndReviewBook(bookId, rating, review);
    }

    private static void displayMainMenu() {
        System.out.println("Welcome to the Ramsi Book Store");
        System.out.println("_____________________________________");
        System.out.println("*  1. Register         *");
        System.out.println("*  2. Login            *");
        System.out.println("*  3. Exit             *");
        System.out.println("_____________________________________");
        System.out.println("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                performRegistration();
                break;
            case 2:
                performLogin();
                break;
            case 3:
                System.out.println("Goodbye");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again");
                displayMainMenu();
        }
    }

    private static void performRegistration() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        loggedInUser = new User(username, password);
        System.out.println("*******Registration Successful*******");
        scanner.nextLine();
        showBookSection();
    }

    private static void performLogin() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (loggedInUser != null && loggedInUser.username.equals(username) && loggedInUser.password.equals(password)) {
            System.out.println("******Login Successful*******");
            showBookSection();
        } else {
            System.out.println("Invalid username and password. Please try again");
            displayMainMenu();
        }
    }

    private static void showBookSection() {
        scanner.nextLine();
        System.out.println("          Book Section          ");
        System.out.println("_____________________________________");
        System.out.println("*  1. Show All Books           *");
        System.out.println("*  2. Add Books                *");
        System.out.println("*  3. Rate and Review Book     *");
        System.out.println("*  4. Show Available Books     *");
        System.out.println("*  5. Borrow Book              *");
        System.out.println("*  6. Return Book              *");
        System.out.println("*  7. Exit                     *");
        System.out.println("_____________________________________");
        System.out.println("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                displayAllBooks();
                break;
            case 2:
                addNewBooks();
                break;
            case 3:
                rateAndReviewBook();
                break;
            case 4:
                displayAvailableBooks();
                break;
            case 5:
                borrowBook();
                break;
            case 6:
                returnBook();
                break;
            case 7:
                System.out.println("Thank you for using the Book Store");
                System.exit(0);
                return;
            default:
                System.out.println("Invalid choice. Please try again");
        }
        showBookSection();
    }

    private static void addNewBooks() {
        System.out.print("Enter the book ID you want to add: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the title of the book: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author of the book: ");
        String author = scanner.nextLine();
        bookLibrary.addNewBook(bookId, title, author);
    }

    private static void displayAllBooks() {
        System.out.println("**********All Books**********");
        List<Book> allBooks = bookLibrary.fetchAllBooks();
        showBooks(allBooks);
    }

    private static void displayAvailableBooks() {
        System.out.println("**********Available Books**********");
        List<Book> availableBooks = bookLibrary.getAvailableBooks();
        showBooks(availableBooks);
    }

    private static void showBooks(List<Book> books) {
        for (Book book : books) {
            System.out.println("ID: " + book.bookId);
            System.out.println("Title: " + book.title);
            System.out.println("Author: " + book.author);
            System.out.println("Available: " + (book.isAvailable() ? "Yes" : "No"));
            System.out.println("Average Rating: " + book.calculateAverageRating());
            System.out.println("Review: " + book.userReview);
            System.out.println();
        }
    }
}
