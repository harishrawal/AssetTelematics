package retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public  static String BASE_URL = "http://34.70.239.163/jhsmobileapi/mobile/configure/";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        System.out.println("BASE_URL"+BASE_URL);
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}


