package com.sas.kafkademo.dao.repository;

import com.sas.kafkademo.dao.ShopDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepo extends JpaRepository<ShopDao, Long> {
    List<ShopDao> findAllByProcessedStatus(String processedStatus);
}
