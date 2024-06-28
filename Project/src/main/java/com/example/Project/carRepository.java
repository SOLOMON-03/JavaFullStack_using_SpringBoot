package com.example.Project;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class carRepository {
    private final JdbcClient jdbcClient;

    public carRepository(JdbcTemplate jdbcTemplate, JdbcClient jdbcClient){
        this.jdbcClient =jdbcClient;
    }

    public List<Car> getAllCars(){
        return jdbcClient.sql("SELECT * from Car")
                .query((rs, rownum)-> new Car(
                        rs.getLong("id"),
                        rs.getString("carName"),
                        rs.getString("carDesc"),
                        rs.getInt("price"),
                        rs.getString("carImage"),
                        rs.getString("carModel")
        )).list();
    }

    public Car getCar(long id){
        return jdbcClient.sql("SELECT * from Car where id = :id")
                .params(Map.of("id", id))
                .query((rs, rownum)-> new Car(
                        rs.getLong("id"),
                        rs.getString("carName"),
                        rs.getString("carDesc"),
                        rs.getInt("price"),
                        rs.getString("carImage"),
                        rs.getString("carModel")
                )).single();
    }

    public void addCar(Car newCar) {
        String sql = "INSERT INTO Car (carName, carDesc, price, carImage, carModel) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .params(newCar.getCarName(), newCar.getCarDesc(), newCar.getPrice(), newCar.getCarImage(), newCar.getCarModel())
                .update(keyHolder);

        Number key = keyHolder.getKey();
        if (key != null) {
            newCar.setCarId(key.longValue());
        }
    }

    public void deleteCarById(Long id) {
        jdbcClient.sql("DELETE FROM Car WHERE id = :id")
                .params(Map.of("id", id))
                .update();
    }

    public void updateCarById(Long id, Car car) {
        jdbcClient.sql("UPDATE Car SET carName = :carName, carDesc = :carDesc, price = :price, carImage = :carImage, carModel = :carModel WHERE id = :id")
                .params(Map.of(
                        "id", id,
                        "carName", car.getCarName(),
                        "carDesc", car.getCarDesc(),
                        "price", car.getPrice(),
                        "carImage", car.getCarImage(),
                        "carModel", car.getCarModel()
                )).update();
    }
}
