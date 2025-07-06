package com.library.main;

import com.library.service.BookService;

public class LibraryApp {
    public static void main(String[] args) {

        // Load Spring configuration file
        applicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // Get the BookService bean from context
        BookService bookService = context.getBean("bookService", BookService.class);

        // Use the BookService to add a book
        bookService.addBook("The Great Gatsby");

        // Close the context (good practice)
        ((ClassPathXmlApplicationContext) context).close();
    }
}
