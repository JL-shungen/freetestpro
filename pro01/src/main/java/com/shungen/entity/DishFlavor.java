package com.shungen.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishFlavor {
    private Long id;
    private Long dishId;
    private String name;
}
