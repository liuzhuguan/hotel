package hotel.service;

import hotel.bean.Food;
import hotel.dao.FoodDao;
import hotel.dao.FoodDaoImp;

import java.util.List;

public class FoodServiceImp implements FoodService {


    @Override
    public Food findById(Integer foodId) {
        FoodDao foodDao = new FoodDaoImp();
        return foodDao.findById(foodId);
    }

    @Override
    public List<Food> findByfoodTypeId(Integer foodTypeId) {
        FoodDao foodDao = new FoodDaoImp();
        return foodDao.findByfoodTypeId(foodTypeId);
    }
}
