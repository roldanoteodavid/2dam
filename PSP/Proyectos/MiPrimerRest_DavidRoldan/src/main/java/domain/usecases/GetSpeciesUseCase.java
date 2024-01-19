package domain.usecases;

import dao.CharacterDao;
import jakarta.inject.Inject;

import java.util.List;

public class GetSpeciesUseCase {
    private final CharacterDao characterDao;

    @Inject
    public GetSpeciesUseCase(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public List<String> getAllCharacterSpecies() {
        return characterDao.getAllCharacterSpecies();
    }

}
