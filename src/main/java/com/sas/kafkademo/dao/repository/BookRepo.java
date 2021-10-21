package com.sas.kafkademo.dao.repository;

import com.sas.kafkademo.dao.BookDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<BookDao, Long> {
}
