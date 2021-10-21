package com.sas.kafkademo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sas.kafkademo.dao.ShopDao;
import com.sas.kafkademo.dao.repository.BookRepo;
import com.sas.kafkademo.dao.repository.ShopRepo;
import com.sas.kafkademo.mapper.ShopMapper;
import com.sas.kafkademo.service.model.Shop;
import com.sas.kafkademo.util.DemoProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.sas.kafkademo.util.AppConstants.*;

@Service
public class ApplicationService {
    @Autowired
    private DemoProducer demoProducer;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    ShopRepo shopRepo;

    @Autowired
    private ObjectMapper objectMapper;

    @Scheduled(fixedDelay = 60, timeUnit = TimeUnit.SECONDS)
    public void runProcess() {
        List<ShopDao> shopDaoList = shopRepo.findAllByProcessedStatus(PROCESSED_STATUS_NO);
        if (!shopDaoList.isEmpty()) {
            for (ShopDao shopDao : shopDaoList) {
                Shop shop = ShopMapper.SHOP_MAPPER.shopDaoToShop(shopDao);
                String message = null;
                try {
                    message = objectMapper.writeValueAsString(shop);
                    demoProducer.sendMessage(message);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                if (message != null) {
                    shopDao.setProcessedStatus(PROCESSED_STATUS_YES);
                } else {
                    shopDao.setProcessedStatus(PROCESSED_STATUS_FAIL);
                }
                shopRepo.save(shopDao);
            }
        }
    }

    public List<ShopDao> getEntitiesFromDB() {
        return shopRepo.findAllByProcessedStatus(PROCESSED_STATUS_NO);
    }

    public String processObjects(ShopDao shopDao) throws JsonProcessingException {
        Shop shop = ShopMapper.SHOP_MAPPER.shopDaoToShop(shopDao);
        String message = objectMapper.writeValueAsString(shop);
        demoProducer.sendMessage(message);                      //need to handle if fails case
        return PROCESSED_STATUS_YES;
    }
}
