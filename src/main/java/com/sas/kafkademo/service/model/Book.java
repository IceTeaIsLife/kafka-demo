package com.sas.kafkademo.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private Long id;
    private String name;
    private String author;
    private Other other;
}
