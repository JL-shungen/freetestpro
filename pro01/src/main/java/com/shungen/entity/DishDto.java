package com.shungen.entity;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/8
 */
@Data
public class DishDto extends Dish{
    private List<DishFlavor> dishFlavors;
    private Integer code;
}
