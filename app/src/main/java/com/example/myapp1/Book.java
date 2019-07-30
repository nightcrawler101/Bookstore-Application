package com.example.myapp1;

/**
 * Book
 * A custom class to store Book information
 */
class Book {

    final String title;
    final String authors;
    final String smallThumbnailLink;




    public Book(
            String title,
            String authors,
            String smallThumbnailLink
    ) {

        this.title = title;
        this.authors = authors;
        this.smallThumbnailLink = smallThumbnailLink;

    }

}
