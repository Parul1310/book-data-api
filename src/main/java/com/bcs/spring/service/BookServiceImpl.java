package com.bcs.spring.service;

import com.bcs.spring.bean.BookEntity;
import com.bcs.spring.exception.BookException;
import com.bcs.spring.exception.BookResourceNotFoundException;
import com.bcs.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<BookEntity> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public void deleteBookById(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public BookEntity findBookById(int id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookResourceNotFoundException("Book id- " + id + " not found" ));
	}

	@Override
	public void addBook(MultipartFile file, String name, Double price, String author, String sDate) {
		Date date = Date.valueOf(sDate);
		byte[] bytes;
		try {
			bytes = file.getBytes();
		} catch (IOException e) {
			throw new BookException("Error in reading book document file");
		}
		BookEntity bookEntity = BookEntity.Builder.aBookEntity()
				.withAuthor(author)
				.withPrice(BigDecimal.valueOf(price))
				.withDate(date)
				.withName(name)
				.withFile(bytes)
				.build();

		bookRepository.saveAndFlush(bookEntity);

	}

	@Override
	public List<BookEntity> getFilteredBooks(List<BookEntity> allBookEntities, Integer limit, String author, Double price, String date) {
		List<BookEntity> searchResultsFiltered = new ArrayList<>();

		// Apply Filters
		searchResultsFiltered = allBookEntities.stream()
				.filter(composePredicates(date, author, price))
				.limit(limit)
				.collect(Collectors.toList());
		return searchResultsFiltered;
	}

	/**
	 * Composes several predicates into a single predicate, and then
	 * applies the composite predicate to a stream.
	 */
	Predicate<BookEntity> composePredicates(String date, String author, Double price) {
		System.out.println();
		System.out.println("Composed predicates:");
		System.out.println();
		List<Predicate<BookEntity>> allPredicates = new ArrayList<>();

		if(date != null && !date.equalsIgnoreCase("")){
			allPredicates.add(e -> e.getDate() != null ? e.getDate().equals(Date.valueOf(date)) : true);
		}

		if(author != null && !author.equalsIgnoreCase("")){
			allPredicates.add(e -> e.getAuthor() != null ? e.getAuthor().toString().equalsIgnoreCase(author) : true);

		}

		if(price != null){
			allPredicates.add(e -> e.getPrice() != null ? e.getPrice().equals(BigDecimal.valueOf(price)) : true);
		}

		Predicate<BookEntity> compositePredicate =
				allPredicates.stream()
						.reduce(w -> true, Predicate::and);
		return compositePredicate;
	}


	@Override
	public BookEntity update(BookEntity newBook) {
		return bookRepository.saveAndFlush(newBook);
	}

}
