package dao.retrofit.llamadas;

import domain.modelo.characters.CharactersResponse;
import domain.modelo.episodes.Episodes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharactersApi {
    @GET("character")
    Call<CharactersResponse> getCharacterByName(@Query("name") String name);

    @GET("character")
    Call<CharactersResponse> getCharacterByType(@Query("species") String type, @Query("page") int page);

    @GET("character")
    Call<CharactersResponse> getCharacter(@Query("page") int page);

    @GET("episode/{id}")
    Call<Episodes> getEpisodeById(@Path("id") int id);

}