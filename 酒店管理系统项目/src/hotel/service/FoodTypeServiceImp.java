package hotel.service;

import hotel.bean.FoodType;
import hotel.dao.FoodTypeDao;
import hotel.dao.FoodTypeDaoImp;

import java.util.List;

public class FoodTypeServiceImp implements FoodTypeService {
    private FoodTypeDao foodTypeDao = new FoodTypeDaoImp();

    @Override
    public List<FoodType> findAll() {
        return foodTypeDao.findAll();
    }
}
