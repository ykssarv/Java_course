package ee.taltech.iti0202.pokemon;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Tournament.
 */
public class Tournament {

    private static final Map<String, Pokemon> POKEMONS = Storage.getPokemons();

    private static Optional<Pokemon> duel(Pokemon pokemon1, Pokemon pokemon2) {
        pokemon1.initDuel();
        pokemon2.initDuel();
        for (int i = 1; i < 101; i++) {
            double attack1 = pokemon1.getAttack(i)
                * pokemon1.getAttackMultiplier(pokemon2.getTypes())
                - pokemon2.getDefense(i);
            pokemon2.damage(attack1);
            if (pokemon2.isDead()) {
                return Optional.of(pokemon1);
            }

            double attack2 = pokemon2.getAttack(i)
                * pokemon2.getAttackMultiplier(pokemon1.getTypes())
                - pokemon1.getDefense(i);
            pokemon1.damage(attack2);
            if (pokemon1.isDead()) {
                return Optional.of(pokemon2);
            }
        }
        return Optional.empty();
    }

    private static void tournament(int offset, int limit, String file) {
        List<Pokemon> fighters = getFightingPokemon(offset, limit);
        for (int i = 0; i < fighters.size(); i++) {
            for (int j = i + 1; j < fighters.size(); j++) {
                Pokemon poke1 = fighters.get(i);
                Pokemon poke2 = fighters.get(j);
                Pokemon first = findFirst(poke1, poke2);
                Pokemon second = (first == poke1) ? poke2 : poke1;
                duel(first, second).ifPresent(Pokemon::addPoint);
            }
        }
        writeToFile(fighters, file);
    }

    private static void writeToFile(List<Pokemon> fighters, String file) {
        System.out.println("Aboiut to write");
        String results = fighters.stream()
            .sorted(
                Comparator.comparing(Pokemon::getPoints).reversed()
                    .thenComparing(Pokemon::getName)
            )
            .map(poke -> poke.getName() + ":" + poke.getPoints())
            .collect(Collectors.joining("\n"));
        try {
            Request.saveToFile(results, file);
            System.out.println("Saved");
        } catch (IOException e) {
            System.out.println("Unable to access result file.");
        }
    }

    private static Pokemon findFirst(Pokemon poke1, Pokemon poke2) {
        if (poke1.getSpeed() > poke2.getSpeed()) {
            return poke1;
        }
        if (poke2.getSpeed() > poke1.getSpeed()) {
            return poke2;
        }
        if (poke1.getWeight() < poke2.getWeight()) {
            return poke1;
        }
        if (poke2.getWeight() < poke1.getWeight()) {
            return poke2;
        }
        if (poke1.getHeight() < poke2.getHeight()) {
            return poke1;
        }
        if (poke2.getHeight() < poke1.getHeight()) {
            return poke2;
        }
        if (poke1.getAbilityAmount() > poke2.getAbilityAmount()) {
            return poke1;
        }
        if (poke2.getAbilityAmount() > poke1.getAbilityAmount()) {
            return poke2;
        }
        if (poke1.getMovesAmount() > poke2.getMovesAmount()) {
            return poke1;
        }
        if (poke2.getMovesAmount() > poke1.getMovesAmount()) {
            return poke2;
        }
        if (poke1.getBaseExperience() > poke2.getBaseExperience()) {
            return poke1;
        }
        if (poke2.getBaseExperience() > poke1.getBaseExperience()) {
            return poke2;
        }
        return poke1;
    }

    private static List<Pokemon> getFightingPokemon(int offset, int limit) {
        return POKEMONS.values().stream()
            .filter(pokemon -> pokemon.getNumber() >= offset && pokemon.getNumber() <= offset + limit)
            .collect(Collectors.toList());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        tournament(0, 100, "results.txt");
    }
}
