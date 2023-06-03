package refactoring_guru.builder.example.components;

import refactoring_guru.builder.example.cars.Car;

public class TripComputer {
    private Car car;

    public void setCar(Car car) {
        this.car = car;
    }

    public void showFuelLevel() {
        System.out.println("Fuel level: " + car.getFuel());
    }

    public void showStatus() {
        if (this.car.getEngine().isStarted()) {
            System.out.println("Car i started");
        } else {
            System.out.println("Car isn't started");
        }
    }
}
