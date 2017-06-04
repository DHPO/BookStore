package Dao;

import entities.BookEntity;

import java.util.List;

public interface BookDao{
    List<BookEntity> getBooks();
    int updateBook(BookEntity book);
    int deleteBook(short id);
    int insertBook(BookEntity book);
    List<BookEntity> findBooksByName(String name);
}
