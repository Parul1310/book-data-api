package com.bcs.spring.repository;

import com.bcs.spring.bean.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}