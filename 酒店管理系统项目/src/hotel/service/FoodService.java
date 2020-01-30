package hotel.service;

import hotel.bean.Food;

import java.util.List;

public interface FoodService {
    public Food findById(Integer foodId);

    public List<Food> findByfoodTypeId(Integer foodTypeId);
}
