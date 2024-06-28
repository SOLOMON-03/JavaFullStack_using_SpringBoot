package com.example.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class carService {
    private final carRepository name;

    @Autowired

    public carService(carRepository name) {
        this.name = name;
    }

    public List<Car> getAllcars() {
        return name.getAllCars();
    }

    public void addBook(Car newCar) {
        name.addCar(newCar);
    }

    public Car getCarById(Long id) {
        return name.getCar(id);
    }

    public void deleteCarById(Long id) {
        name.deleteCarById(id);
    }

    public void updateCarById(Long id, Car car) {
        name.updateCarById(id, car);
    }

    public List<Car> listCars(){
        return name.getAllCars();
    }
}