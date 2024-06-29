package com.selaviu.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.selaviu.library.models.Book;
import com.selaviu.library.models.Person;

@Component
public class BookDAO {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index(){
        List<Book> books = jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
        for (Book book : books) {
            System.out.println(book);
        }
        return books;
    }

    public void save(Book book){
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES(?, ?, ?)", book.getTitle(),
            book.getAuthor(), book.getYear());
    }

    public Optional<Book> show(int id){
        return jdbcTemplate.query("SELECT * FROM Book WHERE id=? ", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void update(int id, Book updatedBook){
        jdbcTemplate.update("UPDATE Book Set title=?, author=?, year=? WHERE id=?",updatedBook.getTitle(),
        updatedBook.getAuthor(), updatedBook.getYear(), updatedBook.getId());
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Book WHERE id=?", new Object[]{id});
    }

    public Optional<Person> getBookOwner(int bookId) {
        String sql = "SELECT p.* FROM Person p JOIN Book b ON p.id = b.person_id WHERE b.id = ?";
        return jdbcTemplate.query(sql, new Object[]{bookId}, 
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }


    public void release(int bookId){
        jdbcTemplate.update("UPDATE Book Set person_id=null WHERE id=? ", bookId);
    }
    
    public void appoint(int bookId, int selectedPersonId){
        jdbcTemplate.update("UPDATE Book Set person_id=? WHERE id=?",selectedPersonId, bookId);
    }


}
