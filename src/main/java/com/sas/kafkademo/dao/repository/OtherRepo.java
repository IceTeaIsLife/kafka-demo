package com.sas.kafkademo.dao.repository;

import com.sas.kafkademo.dao.OtherDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherRepo extends JpaRepository<OtherDao, Long> {
}
