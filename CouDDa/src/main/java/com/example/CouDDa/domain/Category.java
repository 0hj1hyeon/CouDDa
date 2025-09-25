package com.example.CouDDa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long parentId;

    // ⭐ 아래 필드는 양방향 관계를 가질 때만 필요하므로 제거합니다.
    // @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    // private List<Product> products;
}