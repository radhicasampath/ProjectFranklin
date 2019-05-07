package com.practice.dbops.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "item_id")
    private Long itemId;
}
