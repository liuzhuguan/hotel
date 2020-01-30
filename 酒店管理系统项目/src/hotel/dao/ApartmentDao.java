package hotel.dao;

import hotel.bean.Apartment;

import java.util.List;

public interface ApartmentDao {
    public List<Apartment> findApartment(String apartmentStatus, String apartmentName) ;

    public Apartment findById(int id);

    void update(Apartment apartment);
}
