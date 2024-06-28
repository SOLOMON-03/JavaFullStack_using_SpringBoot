package com.example.Project;

public class Car {
    private long carId;
    private String carName;
    private String carDesc;
    private int price;
    private String carImage;
    private String carModel;
    public Car(long carId, String carName, String carDesc, int price, String carImage, String carModel) {
        this.carId = carId;
        this.carName = carName;
        this.carDesc = carDesc;
        this.price = price;
        this.carImage = carImage;
        this.carModel = carModel;
    }
    public long getCarId() {
        return carId;
    }
    public void setCarId(long carId) {
        this.carId = carId;
    }
    public String getCarName() {
        return carName;
    }
    public void setCarName(String carName) {
        this.carName = carName;
    }
    public String getCarDesc() {
        return carDesc;
    }
    public void setCarDesc(String carDesc) {
        this.carDesc = carDesc;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public String getCarImage() {
        return carImage;
    }
    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }
    public String getCarModel() {
        return carModel;
    }
    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }


    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", carName='" + carName + '\'' +
                ", CarDescription=" + carDesc +
                ", price='" + price + '\'' +
                ", carImage='" + carImage + '\'' +
                ", carModel='" + carModel + '\'' +
                '}';
    }

}
