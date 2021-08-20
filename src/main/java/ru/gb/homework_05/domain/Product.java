package ru.gb.homework_05.domain;

import javax.persistence.*;

@Entity
@Table(name = "simple_item")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Title;

    private int price;

    public Product() {
    }

    public Product(String title, int price) {
        Title = title;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", Title='" + Title + '\'' +
                ", price=" + price +
                '}';
    }
}
