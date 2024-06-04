package com.selaviu.library.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.selaviu.library.models.Person;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> show(int id){
        return jdbcTemplate.query("SELECT *FROM Person WHERE id=?", new Object[]{id}, 
            new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    // public Optional<Person> show(String email){
        // return jdbcTemplate.query("SELECT *FROM Person WHERE email=?", new Object[]{email}, new BeanPropertyRowMapper<>(Person.class))
        //         .stream().findAny();
    // }

    public void save(Person person){
        //INSERT INTO Person VALUES(1, ?, ?, ?)");
       jdbcTemplate.update("INSERT INTO Person(first_name, last_name, year_of_birth) VALUES(?, ?, ?)", person.getFirstName(), person.getLastName(), person.getYearOfBirth());
    }

    public void update(int id, Person updatedPerson){
        jdbcTemplate.update("UPDATE Person SET first_name=?, last_name=?, year_of_birth=? WHERE id=?", updatedPerson.getFirstName(), 
        updatedPerson.getLastName(), updatedPerson.getYearOfBirth(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }
}
