package domain.usecases;

import dao.CharacterDao;
import domain.modelo.characters.Characters;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetCharactersUseCase {
    private final CharacterDao characterDao;

    @Inject
    public GetCharactersUseCase(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public Either<String, List<Characters>> getCharacters() {
        return characterDao.getCharacters();
    }
}
