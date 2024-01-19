package domain.usecases;

import dao.CharacterDao;
import domain.modelo.characters.Characters;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetCharactersBySpeciesUseCase {
    private CharacterDao characterDao;

    @Inject
    public GetCharactersBySpeciesUseCase(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public Either<String, List<Characters>> getCharactersBySpecies(String type) {
        return characterDao.getCharactersByType(type);
    }
}
