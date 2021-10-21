package com.sas.kafkademo.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@DynamicUpdate
@Table(name = "Shop", schema = "public")
public class ShopDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String processedStatus;
    @OneToMany(mappedBy = "shopDao", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<BookDao> bookDaoList;
}
