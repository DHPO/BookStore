package Dao;

import entities.BookEntity;
import entities.BookSimple;

import java.util.List;

public interface BookDao{
    List<BookEntity> getBooks();
    List<BookSimple> getSimpleBooks();
    int updateBook(BookEntity book);
    int deleteBook(short id);
    int insertBook(BookEntity book);
    List<BookSimple> findBooksByName(String name);
    BookEntity getBookById(short id);
}
