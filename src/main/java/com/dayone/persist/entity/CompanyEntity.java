package com.dayone.persist.entity;

import com.dayone.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 엔티티는 데이터베이스 테이블과 직접적으로 매핑되기 위한 클래스.
 * 따라서, 서비스 내부에서 데이터를 주고 받는 용도로 사용되면 클래스의 원래 역할 범위를 벗어난다고 볼 수 있다.
 * 그래서 Model 클래스는 따로 정의한다.
 */
@Entity(name = "COMPANY")
@Getter
@ToString
@NoArgsConstructor
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String ticker;
    private String name;

    public CompanyEntity(Company company) {
        this.ticker = company.getTicker();
        this.name = company.getName();
    }
}
