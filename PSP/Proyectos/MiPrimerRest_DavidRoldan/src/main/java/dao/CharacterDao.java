package dao;

import domain.modelo.characters.Characters;
import domain.modelo.episodes.Episodes;
import io.vavr.control.Either;

import java.util.List;

public interface CharacterDao {
    Either<String, List<Characters>> getCharactersByName(String name);

    Either<String, List<Characters>> getCharactersByType(String type);

    Either<String, List<Characters>> getCharacters();

    List<String> getAllCharacterSpecies();

    Either<String, List<Episodes>> getEpisodesByUrl(List<String> urls);
}
