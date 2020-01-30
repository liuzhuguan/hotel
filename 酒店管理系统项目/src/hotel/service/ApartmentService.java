package hotel.service;

import hotel.bean.Apartment;

import java.util.List;

public interface ApartmentService {
    public List<Apartment> findApartment(String apartmentStatus,String apartmentName);

    public Apartment findById(int parseInt);

    void update(Apartment apartment);
}
