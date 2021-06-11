package retrofit;
import java.util.Map;
import model.AddVehiclepojo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface APIInterface {
    @POST("v1/configvalues")
    Call<AddVehiclepojo> VehicleInfoApi(@Body Map<String, String> body);


}

