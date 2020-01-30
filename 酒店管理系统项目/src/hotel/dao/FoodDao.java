package hotel.dao;

import hotel.bean.Food;

import java.util.List;

public interface FoodDao {
    public List<Food> findByfoodTypeId(Integer foodTypeId);

    public Food findById(Integer foodId);
}
