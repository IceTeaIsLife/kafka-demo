package com.sas.kafkademo.mapper;

import com.sas.kafkademo.dao.ShopDao;
import com.sas.kafkademo.service.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BookMapper.class)
public interface ShopMapper {
    ShopMapper SHOP_MAPPER = Mappers.getMapper(ShopMapper.class);

    @Mappings({
            @Mapping(target = "bookDaoList", source = "shop.bookList")})
    ShopDao shopToShopDao(Shop shop);

    @Mappings({
            @Mapping(target = "bookList", source = "shopDao.bookDaoList")})
    Shop shopDaoToShop(ShopDao shopDao);
}
