package com.sas.kafkademo.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"shopDao"})
@Table(name = "Book", schema = "public")
public class BookDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String name;
    private String author;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "other_id", referencedColumnName = "id")
    private OtherDao otherDao;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private ShopDao shopDao;
}
