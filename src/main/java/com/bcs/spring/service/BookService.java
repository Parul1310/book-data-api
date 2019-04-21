package com.bcs.spring.service;

import com.bcs.spring.bean.BookEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
	List<BookEntity> getAllBooks();
	BookEntity update(BookEntity bookEntity);
	void deleteBookById(int id);
	BookEntity findBookById(int id);
	void addBook(MultipartFile file, String name, Double price, String author, String date);
	List<BookEntity> getFilteredBooks(List<BookEntity> allBookEntities, Integer limit, String author, Double price, String date);
}
