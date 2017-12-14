package com.wyn.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "book_category")
public class BookCategory implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    
    @JsonBackReference
    @OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
    private Set<Book> books;

}
