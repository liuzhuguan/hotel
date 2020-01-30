package hotel.service;

import hotel.bean.Apartment;
import hotel.dao.ApartmentDao;
import hotel.dao.ApartmentDaoImp;

import java.util.List;

public class ApartmentServiceImp implements ApartmentService {
    @Override
    public List<Apartment> findApartment(String apartmentStatus, String apartmentName) {
        ApartmentDao apartmentDao = new ApartmentDaoImp();

        return apartmentDao.findApartment(apartmentStatus,apartmentName);
    }

    @Override
    public Apartment findById(int id) {
        ApartmentDao apartmentDao = new ApartmentDaoImp();

        return apartmentDao.findById(id);
    }

    @Override
    public void update(Apartment apartment) {
        ApartmentDao apartmentDao = new ApartmentDaoImp();

        apartmentDao.update(apartment);
    }
}
