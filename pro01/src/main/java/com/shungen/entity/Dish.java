package com.shungen.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/8
 */
@Data
public class Dish {
    private Long id;
    private String name;
    private Long category_id;
    private BigDecimal price;
}
