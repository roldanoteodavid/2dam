package domain.usecases;

import dao.CharacterDao;
import domain.modelo.episodes.Episodes;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetEpisodesByUrlUseCase {
    private final CharacterDao characterDao;

    @Inject
    public GetEpisodesByUrlUseCase(CharacterDao characterDao) {
        this.characterDao = characterDao;
    }

    public Either<String, List<Episodes>> getEpisodesByUrl(List<String> urls) {
        return characterDao.getEpisodesByUrl(urls);
    }
}
