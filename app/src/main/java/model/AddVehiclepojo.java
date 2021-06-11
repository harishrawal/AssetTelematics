package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AddVehiclepojo {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("vehicle_type")
    @Expose
    private List<VehicleType> vehicleType = null;
    @SerializedName("vehicle_capacity")
    @Expose
    private List<VehicleCapacity> vehicleCapacity = null;
    @SerializedName("vehicle_make")
    @Expose
    private List<VehicleMake> vehicleMake = null;
    @SerializedName("manufacture_year")
    @Expose
    private List<ManufactureYear> manufactureYear = null;
    @SerializedName("fuel_type")
    @Expose
    private List<FuelType> fuelType = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<VehicleType> getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(List<VehicleType> vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<VehicleCapacity> getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(List<VehicleCapacity> vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public List<VehicleMake> getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(List<VehicleMake> vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public List<ManufactureYear> getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(List<ManufactureYear> manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public List<FuelType> getFuelType() {
        return fuelType;
    }

    public void setFuelType(List<FuelType> fuelType) {
        this.fuelType = fuelType;
    }

}
