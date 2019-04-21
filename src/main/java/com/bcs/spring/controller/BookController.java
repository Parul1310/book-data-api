package com.bcs.spring.controller;

import com.bcs.spring.bean.BookEntity;
import com.bcs.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value={"/book"})
public class BookController {
	@Autowired
	BookService bookService;

	 @PostMapping(value="/addBook")
	 public ResponseEntity<Void> addBook(@RequestPart("file") MultipartFile file,
										 @RequestParam("name") String name,
										 @RequestParam("author") String author,
										 @RequestParam("price") String price,
										 @RequestParam("date") String date){
	     System.out.println("Creating Book "+ name);
	     bookService.addBook(file, name, Double.valueOf(price), author, date);
	     HttpHeaders headers = new HttpHeaders();
	     return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	 }

	 @GetMapping(value="/getAllBooks", headers="Accept=application/json")
	 public List<BookEntity> getAllBooks() {
	  List<BookEntity> bookEntities = bookService.getAllBooks();
	  return bookEntities;

	 }

	@GetMapping(value="/updatePrice", headers="Accept=application/json")
	public ResponseEntity<String> updateBookPrice(@RequestParam("id") int id, @RequestParam("price") double price)
	{
	BookEntity currentBook = bookService.findBookById(id);
	if (currentBook==null) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	currentBook.setPrice(BigDecimal.valueOf(price));
	bookService.update(currentBook);
	return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping(value="/{id}", headers ="Accept=application/json")
	public ResponseEntity<BookEntity> deleteBook(@PathVariable("id") int id){
		BookEntity bookEntity = bookService.findBookById(id);

		bookService.deleteBookById(id);
		return new ResponseEntity<BookEntity>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value="/filterBooks", headers="Accept=application/json")
	public List<BookEntity> getFilteredBooks(@RequestParam("limit") Integer limit,
											 @RequestParam("author") String author,
											 @RequestParam("price") Double price,
											 @RequestParam("date") String date) {
		List<BookEntity> allBookEntities = bookService.getAllBooks();
		List<BookEntity> filteredBookEntities = bookService.getFilteredBooks(allBookEntities, limit, author, price, date);
		return filteredBookEntities;

	}
}
