package com.sas.kafkademo.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = {"bookDao"})
@Table(name = "Other", schema = "public")
public class OtherDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String metadata;

    @OneToOne(mappedBy = "otherDao", fetch = FetchType.EAGER)
    private BookDao bookDao;
}
