package com.bcs.spring.mockdata;


import com.bcs.spring.bean.BookEntity;
import net.andreinc.mockneat.MockNeat;

import java.util.List;

public class BookMock {

	public void createBookData(){
		/** Internally uses the ThreadLocalRandom implementation */
		MockNeat mock = MockNeat.threadLocal();


		// Generating 10 Books
		List<BookEntity> bookEntities = mock.reflect(BookEntity.class)
				.field("name", mock.names())
				.field("author", mock.names())
				.field("price", mock.doubles())
				.field("date", mock.localDates())
				.list(10)
				.val();
	}

}
