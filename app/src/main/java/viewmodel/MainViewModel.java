package viewmodel;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.AddVehiclepojo;
import model.FuelType;
import model.ManufactureYear;
import model.VehicleCapacity;
import model.VehicleMake;
import model.VehicleType;
import retrofit.APIClient;
import retrofit.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;

public class MainViewModel extends ViewModel {
    public MutableLiveData<Integer> busy;
    APIInterface apiInterface;

    MutableLiveData<ArrayList<VehicleType>> userLiveData_vehicleType;
    ArrayList<VehicleType> userArrayList_vehicleType;

    MutableLiveData<ArrayList<VehicleMake>> userLiveData_vehicleMake;
    ArrayList<VehicleMake> userArrayList_vehicleMake;

    MutableLiveData<ArrayList<VehicleCapacity>> userLiveData_vehicleCapacity;
    ArrayList<VehicleCapacity> userArrayList_vehicleCapacity;

    MutableLiveData<ArrayList<ManufactureYear>> userLiveData_vehicleMyear;
    ArrayList<ManufactureYear> userArrayList_vehicleMyear;

    MutableLiveData<ArrayList<FuelType>> userLiveData_vehicleFuel;
    ArrayList<FuelType> userArrayList_vehicleFuel;

    public MainViewModel() {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        userLiveData_vehicleType = new MutableLiveData<>();
        userLiveData_vehicleMake = new MutableLiveData<>();
        userLiveData_vehicleCapacity = new MutableLiveData<>();
        userLiveData_vehicleMyear = new MutableLiveData<>();
        userLiveData_vehicleFuel = new MutableLiveData<>();
        vehicle_list_api();
    }

    public MutableLiveData<Integer> getBusy() {
        if (busy == null) {
            busy = new MutableLiveData<>();
            busy.setValue(8);
        }
        return busy;
    }

    public MutableLiveData<ArrayList<VehicleType>> getUserMutableLiveData() {
        return userLiveData_vehicleType;
    }

   public MutableLiveData<ArrayList<VehicleMake>> getUserMutableLiveData_vehicelMake() {
        return userLiveData_vehicleMake;
    }

    public MutableLiveData<ArrayList<VehicleCapacity>> getUserMutableLiveData_vehicelCapacity() {
        return userLiveData_vehicleCapacity;
    }

    public MutableLiveData<ArrayList<ManufactureYear>> getUserMutableLiveData_vehicelmYear() {
        return userLiveData_vehicleMyear;
    }

    public MutableLiveData<ArrayList<FuelType>> getUserMutableLiveData_vehicelFuel() {
        return userLiveData_vehicleFuel;
    }



    public void vehicle_list_api() {
        getBusy().setValue(0);
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("clientid", "11");
        requestBody.put("enterprise_code", "1007");
        requestBody.put("mno", "9889897789");
        requestBody.put("passcode", "3476");

        Call<AddVehiclepojo> call1 = apiInterface.VehicleInfoApi(requestBody);
        call1.enqueue(new Callback<AddVehiclepojo>() {
            @Override
            public void onResponse(Call<AddVehiclepojo> call, retrofit2.Response<AddVehiclepojo> response) {
                Log.e("url_activity", response.raw().request().url().toString());
                AddVehiclepojo user1 = response.body();
                busy.setValue(8);
                try {
                    assert user1 != null;
                    userArrayList_vehicleType = (ArrayList<VehicleType>) user1.getVehicleType();
                    userArrayList_vehicleMake = (ArrayList<VehicleMake>) user1.getVehicleMake();
                    userArrayList_vehicleCapacity = (ArrayList<VehicleCapacity>) user1.getVehicleCapacity();
                    userArrayList_vehicleMyear = (ArrayList<ManufactureYear>) user1.getManufactureYear();
                    userArrayList_vehicleFuel = (ArrayList<FuelType>) user1.getFuelType();


                    userLiveData_vehicleType.setValue(userArrayList_vehicleType);
                    userLiveData_vehicleMake.setValue(userArrayList_vehicleMake);
                    userLiveData_vehicleCapacity.setValue(userArrayList_vehicleCapacity);
                    userLiveData_vehicleMyear.setValue(userArrayList_vehicleMyear);
                    userLiveData_vehicleFuel.setValue(userArrayList_vehicleFuel);
                    System.out.println("------------Success----------------"+ userArrayList_vehicleType.size());

                } catch (Exception e) {
                    System.out.println("------------Failure----------------"+ e);
                }
            }
            @Override
            public void onFailure(Call<AddVehiclepojo> call, Throwable t) {
                busy.setValue(8);
                System.out.println("------------Failure----------------"+ t);
                call.cancel();

            }
        });
    }
}
