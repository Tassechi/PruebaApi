package pruebaapi.pruebaapi.Pokeapi;

import pruebaapi.pruebaapi.models.PokemonRespuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by orimu on 15/05/2018.
 */

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("Limit")int limit,@Query("offset") int offset);
}
