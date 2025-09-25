package com.example.CouDDa.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contactEmail;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // ⭐ 아래 필드는 양방향 관계를 가질 때만 필요하므로 제거합니다.
    // @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    // private List<Product> products;
}