package com.sas.kafkademo.mapper;

import com.sas.kafkademo.dao.BookDao;
import com.sas.kafkademo.service.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(uses = OtherMapper.class)
public interface BookMapper {
    BookMapper BOOK_MAPPER = Mappers.getMapper(BookMapper.class);

    @Mappings({
            @Mapping(target = "otherDao", source = "book.other")})
    BookDao bookToBookDao(Book book);

    @Mappings({
            @Mapping(target = "other", source = "bookDao.otherDao")})
    Book bookDaoToBook(BookDao bookDao);
}
