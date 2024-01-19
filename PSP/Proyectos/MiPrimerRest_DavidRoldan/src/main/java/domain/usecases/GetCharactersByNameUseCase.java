package domain.usecases;

import dao.CharacterDao;
import domain.modelo.characters.Characters;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetCharactersByNameUseCase {
    private CharacterDao characterDao;

    @Inject
    public GetCharactersByNameUseCase(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public Either<String, List<Characters>> getCharactersByName(String name) {
        return characterDao.getCharactersByName(name);
    }
}
