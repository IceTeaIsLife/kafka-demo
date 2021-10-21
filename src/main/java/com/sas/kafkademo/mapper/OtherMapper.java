package com.sas.kafkademo.mapper;

import com.sas.kafkademo.dao.OtherDao;
import com.sas.kafkademo.service.model.Other;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OtherMapper {
    OtherMapper OTHER_MAPPER = Mappers.getMapper(OtherMapper.class);

    OtherDao otherToOtherDao(Other other);

    Other otherDaoToOther(OtherDao otherDao);
}
