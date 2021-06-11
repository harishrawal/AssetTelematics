package com.assettelematics.app;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;
import adapter.VehicleTypeAdapter;
import baseactivity.BaseActivity;
import model.FuelType;
import model.ManufactureYear;
import model.VehicleCapacity;
import model.VehicleMake;
import model.VehicleType;
import viewmodel.MainViewModel;

public class AddVehicle extends BaseActivity implements LifecycleOwner {
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 3;
    EditText ed_imei;
    MainViewModel viewModel;
    VehicleTypeAdapter vehicleTypeAdapter;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView_bottom;
    AddVehicle context;
    Spinner spinner_vehiclemake;
    Spinner spinner_vehiclecapacity;
    Spinner spinner_myear;
    Spinner spinner_fuel;
    ProgressBar progressBar;
    BottomSheetDialog bottomSheetDialog;
    BottomSheetBehavior bottomSheetBehavior;
    LinearLayout ll_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        context = this;

        checkcamerapermission();
        ll_add = findViewById(R.id.ll_add);ll_add.setOnClickListener(this);
        ed_imei = findViewById(R.id.ed_imei);ed_imei.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar);
        spinner_fuel = findViewById(R.id.spinner_fuel);
        spinner_myear = findViewById(R.id.spinner_myear);
        spinner_vehiclemake = findViewById(R.id.spinner_vehiclemake);
        spinner_vehiclecapacity = findViewById(R.id.spinner_vehiclecapacity);
        mRecyclerView = findViewById(R.id.mRecyclerView);

        viewModel = new ViewModelProvider(context).get(MainViewModel.class);
        viewModel.getBusy().observe(context, setProgress);
        viewModel.getUserMutableLiveData().observe(context, userListUpdateObserver);
        viewModel.getUserMutableLiveData_vehicelMake().observe(context, userListUpdateObserver_vehicleMake);
        viewModel.getUserMutableLiveData_vehicelCapacity().observe(context, userListUpdateObserver_vehicleCapacity);
        viewModel.getUserMutableLiveData_vehicelmYear().observe(context, userListUpdateObserver_vehicleMyear);
        viewModel.getUserMutableLiveData_vehicelFuel().observe(context, userListUpdateObserver_vehicleFuel);


        View bottomSheetView = getLayoutInflater().inflate(R.layout.bottom_vehicle_type_info, null);
        bottomSheetDialog = new BottomSheetDialog(AddVehicle.this);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetBehavior = BottomSheetBehavior.from((View) bottomSheetView.getParent());
        bottomSheetBehavior.removeBottomSheetCallback (bottomSheetCallback);
        mRecyclerView_bottom = bottomSheetView.findViewById(R.id.mRecyclerView_bottom);

    }

    Observer<Integer> setProgress = new Observer<Integer>() {
        @Override
        public void onChanged(Integer check) {
            progressBar.setVisibility(check);
        }
    };

    Observer<ArrayList<VehicleType>> userListUpdateObserver = new Observer<ArrayList<VehicleType>>() {
        @Override
        public void onChanged(ArrayList<VehicleType> userArrayList) {
            vehicleTypeAdapter = new VehicleTypeAdapter(context,userArrayList);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerView.setAdapter(vehicleTypeAdapter);

            mRecyclerView_bottom.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            mRecyclerView_bottom.setAdapter(vehicleTypeAdapter);
        }
    };

    Observer<ArrayList<VehicleMake>> userListUpdateObserver_vehicleMake = new Observer<ArrayList<VehicleMake>>() {
        @Override
        public void onChanged(ArrayList<VehicleMake> userArrayList) {
            ArrayAdapter<VehicleMake> adapter = new ArrayAdapter<VehicleMake>(context, android.R.layout.simple_list_item_1, userArrayList);
            spinner_vehiclemake.setAdapter(adapter);
        }
    };

    Observer<ArrayList<VehicleCapacity>> userListUpdateObserver_vehicleCapacity = new Observer<ArrayList<VehicleCapacity>>() {
        @Override
        public void onChanged(ArrayList<VehicleCapacity> userArrayList) {
            ArrayAdapter<VehicleCapacity> adapter = new ArrayAdapter<VehicleCapacity>(context, android.R.layout.simple_list_item_1, userArrayList);
            spinner_vehiclecapacity.setAdapter(adapter);
        }
    };

    Observer<ArrayList<ManufactureYear>> userListUpdateObserver_vehicleMyear = new Observer<ArrayList<ManufactureYear>>() {
        @Override
        public void onChanged(ArrayList<ManufactureYear> userArrayList) {
            ArrayAdapter<ManufactureYear> adapter = new ArrayAdapter<ManufactureYear>(context, android.R.layout.simple_list_item_1, userArrayList);
            spinner_myear.setAdapter(adapter);
        }
    };

    Observer<ArrayList<FuelType>> userListUpdateObserver_vehicleFuel = new Observer<ArrayList<FuelType>>() {
        @Override
        public void onChanged(ArrayList<FuelType> userArrayList) {
            ArrayAdapter<FuelType> adapter = new ArrayAdapter<FuelType>(context, android.R.layout.simple_list_item_1, userArrayList);
            spinner_fuel.setAdapter(adapter);
        }
    };



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_imei:
                if (checkcamerapermission()) {
                    Intent intent = new Intent(AddVehicle.this, BarCodeActivity.class);
                    startActivityForResult(intent, 2);
                } else {
                    checkcamerapermission();
                }
                break;


                case R.id.ll_add:
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    bottomSheetDialog.show();

                    break;
        }
    }

    public boolean checkcamerapermission() {
        int storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int storage1 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);


        List<String> listPermissionsNeeded = new ArrayList<>();

        if (camera != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.CAMERA);
        }
        if (storage != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (storage1 != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }

        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray
                    (new String[listPermissionsNeeded.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2)
        {
            try {
                String message=data.getStringExtra("MESSAGE");
                if (message != null) {
                    if(message.length()>0){
                        ed_imei.setText(message);
                    }
                }
                else{
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                }
            }
            catch (Exception e){
                System.out.println("Exception : "+e);
            }
        }
    }

    BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
        }
        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };
}