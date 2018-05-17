package pruebaapi.pruebaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import pruebaapi.pruebaapi.Pokeapi.PokeapiService;
import pruebaapi.pruebaapi.models.PokemonRespuesta;
import pruebaapi.pruebaapi.models.pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler;
    private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;
    private boolean AptoParaCargar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recycler.setAdapter(listaPokemonAdapter);
        recycler.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recycler.setLayoutManager(layoutManager);
        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0 ){
                    int visibleIntemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (AptoParaCargar){
                        if ((visibleIntemCount + pastVisibleItems)>= totalItemCount) {
                            Log.i(TAG, "Llegamos al final.");

                            AptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }


                }
            }
        });



        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AptoParaCargar = true;
        offset = 0;
        obtenerDatos(offset);
        
    }

    private void obtenerDatos(int offset) {
        PokeapiService service = retrofit.create(PokeapiService.class);
        Call<PokemonRespuesta> pokemonRespuestaCall = service.obtenerListaPokemon(20 , this.offset);

        pokemonRespuestaCall.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                AptoParaCargar = true;
                if (response.isSuccessful()){

                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<pokemon> listapokemon = pokemonRespuesta.getResults();

                    listaPokemonAdapter.adicionarlistapokemon(listapokemon);

                }else{
                    Log.e(TAG ,"onResponse:" + response.errorBody());

                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                AptoParaCargar = true;
                Log.e(TAG, "onFailure:" + t.getMessage());

            }
        });

    }


}
