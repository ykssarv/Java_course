package ee.taltech.iti0202.pokemon;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The type Pokemon.
 */
public class Pokemon {

    private static final Map<String, Map<String, Double>> MULTIPLIERS = Multipliers.getMultipliers();
    private static final String URL_BASE = "https://pokeapi.co/api/v2/pokemon/";

    private String name;
    private String filename;
    private String url;
    private boolean hasData;
    private int number;

    private int hp;
    private int attack;
    private int defense;
    private int specialAttack;
    private int specialDefense;
    private int speed;
    private int height;
    private int weight;
    private int baseExperience;

    private int points;
    private double currentHp;

    private List<String> types;
    private List<String> abilities;
    private List<String> forms;
    private List<String> moves;

    /**
     * Instantiates a new Pokemon.
     *
     * @param poke the poke
     */
    public Pokemon(JsonElement poke) {
        JsonObject pokeObject = poke.getAsJsonObject();
        this.name = pokeObject.get("name").getAsString();
        this.filename = "data/" + this.name;
        this.url = pokeObject.get("url").getAsString();
        this.hasData = false;
        this.types = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.forms = new ArrayList<>();
        this.moves = new ArrayList<>();
        loadData();
        this.points = 0;
        this.number = Integer.parseInt(this.url.split(URL_BASE)[1].split("/")[0]);
    }

    /**
     * Gets number.
     *
     * @return the number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Gets weight.
     *
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets ability amount.
     *
     * @return the ability amount
     */
    public int getAbilityAmount() {
        return abilities.size();
    }

    /**
     * Gets moves amount.
     *
     * @return the moves amount
     */
    public int getMovesAmount() {
        return moves.size();
    }

    /**
     * Gets base experience.
     *
     * @return the base experience
     */
    public int getBaseExperience() {
        return baseExperience;
    }

    /**
     * Gets attack.
     *
     * @param turn the turn
     * @return the attack
     */
    public double getAttack(int turn) {
        return turn % 3 == 0 ? specialAttack : attack;
    }

    /**
     * Gets defense.
     *
     * @param turn the turn
     * @return the defense
     */
    public double getDefense(int turn) {
        return 0.5 * (turn % 2 == 0 ? specialDefense : defense);
    }

    /**
     * Gets attack multiplier.
     *
     * @param otherTypes the other types
     * @return the attack multiplier
     */
    public double getAttackMultiplier(List<String> otherTypes) {
        double best = 0;
        for (String type : types) {
            double thisMultiplier = 1;
            for (String otherType : otherTypes) {
                thisMultiplier *= MULTIPLIERS.get(type).get(otherType);
            }
            best = Math.max(best, thisMultiplier);
        }
        return best;
    }

    /**
     * Gets types.
     *
     * @return the types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Add point.
     */
    public void addPoint() {
        points++;
    }

    /**
     * Init duel.
     */
    public void initDuel() {
        currentHp = hp;
    }

    /**
     * Damage.
     *
     * @param amount the amount
     */
    public void damage(double amount) {
        if (amount > 0) {
            currentHp -= amount;
        }
    }

    /**
     * Is dead boolean.
     *
     * @return the boolean
     */
    public boolean isDead() {
        return currentHp <= 0;
    }

    private void loadData() {
        if (!(new File(this.filename).exists())) {
            try {
                String data = Request.getJsonFromUrl(this.url);
                Request.saveToFile(data, this.filename);
            } catch (IOException e) {
                System.out.println("Problems loading data of pokemon " + this.name);
            }
        }
        try {
            parseData(Request.readFromFile(this.filename));
        } catch (FileNotFoundException e) {
            System.out.println("Problems with reading from cache for pokemon " + this.name);
        }
    }

    private void parseData(String data) {
        JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();

        parseStats(jsonObject.get("stats").getAsJsonArray());

        height = jsonObject.get("height").getAsInt();
        weight = jsonObject.get("weight").getAsInt();
        baseExperience = jsonObject.get("base_experience").getAsInt();

        parseList(jsonObject, "types", "type", types);
        parseList(jsonObject, "abilities", "ability", abilities);
        parseList(jsonObject, "forms", "form", forms, false);
        parseList(jsonObject, "moves", "move", moves);

        hasData = true;
    }

    private void parseStats(JsonArray stats) {
        stats.forEach(stat -> {
            JsonObject statObject = stat.getAsJsonObject();
            int value = statObject.get("base_stat").getAsInt();
            String name = statObject.get("stat").getAsJsonObject().get("name").getAsString();
            switch (name) {
                case "hp": hp = value;
                case "attack": attack = value;
                case "defense": defense = value;
                case "special-attack": specialAttack = value;
                case "special-defense": specialDefense = value;
                case "speed": speed = value;
                default:
            }
        });
    }

    private void parseList(JsonObject object, String plural, String singular, List<String> list) {
        parseList(object, plural, singular, list, true);
    }

    private void parseList(
        JsonObject object,
        String plural,
        String singular,
        List<String> list,
        boolean needSingularGet
    ) {
        object.get(plural)
            .getAsJsonArray()
            .forEach(element ->
                list.add(
                    (needSingularGet ? element.getAsJsonObject().get(singular) : element)
                        .getAsJsonObject().get("name")
                        .getAsString()
                )
            );
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }
}
