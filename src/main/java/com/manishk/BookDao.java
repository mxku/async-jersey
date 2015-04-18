package com.manishk;

import jersey.repackaged.com.google.common.util.concurrent.ListenableFuture;
import jersey.repackaged.com.google.common.util.concurrent.ListeningExecutorService;
import jersey.repackaged.com.google.common.util.concurrent.MoreExecutors;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Created by manish on 14/04/15.
 */
public class BookDao {

    private Map<String,Book> books;

    private ListeningExecutorService executorService;

    public BookDao(){
        books = new ConcurrentHashMap<>();
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(4));
        Book book1 = new Book();
        book1.setId("1");
        book1.setTitle("Title1");
        book1.setAuthor("Author1");
        book1.setIsbn("ISBN1");
        book1.setPublished(new Date());

        Book book2 = new Book();
        book2.setId("2");
        book2.setTitle("Title2");
        book2.setAuthor("Author2");
        book2.setIsbn("ISBN2");
        book2.setPublished(new Date());

        books.put(book1.getId(),book1);
        books.put(book2.getId(),book2);

    }

    public  Collection<Book> getBooks(){
        return books.values();
    }

    public Book getBook(String id){
        return books.get(id);
    }

    public Book addBook(Book book){
        book.setId(UUID.randomUUID().toString());
        books.put(book.getId(),book);
        return book;
    }

    public ListenableFuture<Book> getBooksAsync(final String id){
        ListenableFuture<Book> future = executorService.submit(new Callable<Book>() {
            @Override
            public Book call() throws Exception {
                return getBook(id);
            }
        });

        return future;
    }

    public ListenableFuture<Book> addBooksAsync(final Book book){
        ListenableFuture<Book> future = executorService.submit(new Callable<Book>() {
            @Override
            public Book call() throws Exception {
                return addBook(book);
            }
        });

        return future;
    }
}
