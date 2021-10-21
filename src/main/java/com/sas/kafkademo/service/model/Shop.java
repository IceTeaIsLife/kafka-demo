package com.sas.kafkademo.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Shop {
    private Long id;
    private String name;
    private String processedStatus;
    private Other other;
    private List<Book> bookList;
}
