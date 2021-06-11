package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.assettelematics.app.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.VehicleType;


public class VehicleTypeAdapter extends RecyclerView.Adapter<VehicleTypeAdapter.ViewHolder> {

    Activity context;
    ArrayList<VehicleType> userArrayList;

    public VehicleTypeAdapter(Activity context, ArrayList<VehicleType> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }


    @NonNull
    @Override
    public VehicleTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(context).inflate(R.layout.item_vehicle_type,parent,false);
        VehicleTypeAdapter.ViewHolder viewHolder = new VehicleTypeAdapter.ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleTypeAdapter.ViewHolder viewHolder, int position) {

        VehicleType user = userArrayList.get(position);
    //    RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;

        viewHolder.tv_name.setText(user.getText());

        Glide.with(context)
                .load(user.getImages())
                .placeholder(R.drawable.ic_vehicle)
                .into(viewHolder.img_vehicle);
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_vehicle;
        TextView tv_name;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_vehicle = itemView.findViewById(R.id.img_vehicle);
            tv_name = itemView.findViewById(R.id.tv_name);



        }
    }
}
