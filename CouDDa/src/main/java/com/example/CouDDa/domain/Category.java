package com.example.CouDDa.domain;

public class Category {
    private long id;
    private String name;
    private Long parentId;

    public Category(long id, String name, Long parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    // getters, setters, toString() 등
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
