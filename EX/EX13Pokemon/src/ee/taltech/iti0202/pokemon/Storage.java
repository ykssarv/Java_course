package ee.taltech.iti0202.pokemon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Storage.
 */
public final class Storage {

    private static final String FILENAME = "data/pokemons.txt";
    private static final Map<String, Pokemon> POKEMONS = new HashMap<>();
    private static boolean pokemonLoaded = false;

    /**
     * Cache.
     */
    public static void cache() {
        File cache = new File(FILENAME);
        if (!cache.exists()) {
            try {
                String json = Request.getJsonFromUrl("https://pokeapi.co/api/v2/pokemon?offset=0&limit=100000");
                Request.saveToFile(json, FILENAME);
            } catch (IOException e) {
                throw new RuntimeException("Serious problems: There is no cache and unable to connect to internet.");
            }
        }
    }

    /**
     * Read.
     */
    public static void read() {
        try {
            String json = Request.readFromFile(FILENAME);
            JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
            generatePokemon(jsonObject.getAsJsonArray("results"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to read cache.");
        }
    }

    /**
     * Generate pokemon.
     *
     * @param pokemonJson the pokemon json
     */
    public static void generatePokemon(JsonArray pokemonJson) {
        pokemonJson.forEach(poke -> {
            Pokemon pokemon = new Pokemon(poke);
            POKEMONS.put(pokemon.getName(), pokemon);
        });
    }

    /**
     * Gets pokemons.
     *
     * @return the pokemons
     */
    public static Map<String, Pokemon> getPokemons() {
        if (!pokemonLoaded) {
            cache();
            read();
        }
        return POKEMONS;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Map<String, Pokemon> pokemons = Storage.getPokemons();
    }
}
