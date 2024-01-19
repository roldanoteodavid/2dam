package dao.retrofit;


import com.squareup.moshi.Moshi;
import common.config.Configuracion;
import dao.retrofit.llamadas.CharactersApi;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ProducesRetrofit {

    @Produces
    @Singleton
    public Moshi getMoshi() {
        return new Moshi.Builder().build();
    }


    @Produces
    public OkHttpClient getOK(Configuracion config) {
        return new OkHttpClient.Builder().connectionPool(new okhttp3.ConnectionPool(1, 1, java.util.concurrent.TimeUnit.SECONDS)).build();

    }

    @Produces
    @Singleton
    public Retrofit retrofits(OkHttpClient clientOK, Moshi moshi, Configuracion config) {
        return new Retrofit.Builder().baseUrl(config.getPathDatos()).addConverterFactory(MoshiConverterFactory.create(moshi)).client(clientOK).build();
    }


    @Produces
    public CharactersApi getCharactersAPI(Retrofit retrofit) {
        return retrofit.create(CharactersApi.class);
    }


}
