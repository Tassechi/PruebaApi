package pruebaapi.pruebaapi.models;

import java.util.ArrayList;

/**
 * Created by orimu on 15/05/2018.
 */

public class PokemonRespuesta {

    private ArrayList<pokemon> results;

    public ArrayList<pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<pokemon> results) {
        this.results = results;
    }
}
