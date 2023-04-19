import com.alibaba.fastjson.JSON;
import com.shungen.entity.CommonResult;
import com.shungen.entity.Dish;
import com.shungen.entity.DishDto;
import com.shungen.entity.DishFlavor;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author shungen
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2023/4/8
 */
public class FastJsonTest01 {

    @Test
    public void test01(){
        Dish dish1 = new Dish();
        dish1.setId(10001L);
        dish1.setName("蘑菇炒黄瓜");
        dish1.setPrice(new BigDecimal("11.11"));
        dish1.setCategory_id(222L);


        DishDto dishDto = null;
        List<Dish> dishes = new ArrayList<>();
        List<DishDto> dishDtos = new ArrayList<>(3);
        for(int i = 0;i < 3;i++){
            dishDto = new DishDto();
            BeanUtils.copyProperties(dish1,dishDto);
            ArrayList<DishFlavor> dishFalvorList = new ArrayList<>();
            dishFalvorList.add(new DishFlavor(1001L,10001L,"我就是name"));
            dishFalvorList.add(new DishFlavor(1001L,10001L,"我就是name"));
            dishFalvorList.add(new DishFlavor(1001L,10001L,"我就是name"));
            dishDto.setDishFlavors(dishFalvorList);
            dishes.add(dishDto);
            dishDtos.add(dishDto);
        }

        CommonResult<List<DishDto>> result1 = CommonResult.success(dishDtos);
        CommonResult<List<Dish>> result2 = CommonResult.success(dishes);

        //终于他妈到正题了
//        System.out.println(JSON.toJSONString(result1));//全数据显示

        //处理一下，用父类接受一下，多态
        System.out.println(JSON.toJSONString(result2));//全部显示

    }
}
