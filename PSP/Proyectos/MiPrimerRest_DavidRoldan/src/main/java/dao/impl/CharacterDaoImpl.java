package dao.impl;

import common.Constantes;
import dao.CharacterDao;
import dao.retrofit.llamadas.CharactersApi;
import domain.modelo.characters.Characters;
import domain.modelo.characters.CharactersResponse;
import domain.modelo.episodes.Episodes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CharacterDaoImpl implements CharacterDao {

    private final CharactersApi charactersApi;

    @Inject
    public CharacterDaoImpl(CharactersApi charactersApi) {
        this.charactersApi = charactersApi;
    }

    @Override
    public Either<String, List<Characters>> getCharactersByName(String name) {
        Either<String, List<Characters>> respuesta;

        Response<CharactersResponse> r;
        try {
            r = charactersApi.getCharacterByName(name).execute();

            if (r.isSuccessful()) {
                if (r.body() == null) {
                    respuesta = Either.left(Constantes.EL_PERSONAJE_NO_EXISTE);
                } else {
                    respuesta = Either.right(r.body().getResults());
                }
            } else {
                respuesta = Either.left(Constantes.EL_PERSONAJE_NO_EXISTE);
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }

        return respuesta;
    }

    @Override
    public Either<String, List<Characters>> getCharactersByType(String type) {
        Either<String, List<Characters>> respuesta = null;
        Response<CharactersResponse> r = null;
        try {
            List<Characters> allCharacters = new ArrayList<>();

            int currentPage = 1;
            boolean hasNextPage = true;

            while (hasNextPage) {
                r = charactersApi.getCharacterByType(type, currentPage).execute();

                if (r.isSuccessful() && r.body() != null) {
                    List<Characters> characters = r.body().getResults();
                    allCharacters.addAll(characters);
                    if (currentPage < r.body().getInfo().getPages()) {
                        currentPage++;
                    } else {
                        hasNextPage = false;
                    }
                } else {
                    respuesta = Either.left(Constantes.ERROR_AL_OBTENER_LOS_PERSONAJES);
                    hasNextPage = false;
                }
            }
            if (respuesta == null) {
                respuesta = Either.right(allCharacters);
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    @Override
    public Either<String, List<Characters>> getCharacters() {
        Either<String, List<Characters>> respuesta = null;
        Response<CharactersResponse> r = null;
        try {
            List<Characters> allCharacters = new ArrayList<>();

            int currentPage = 1;
            boolean hasNextPage = true;

            while (hasNextPage) {
                r = charactersApi.getCharacter(currentPage).execute();

                if (r.isSuccessful() && r.body() != null) {
                    List<Characters> characters = r.body().getResults();
                    allCharacters.addAll(characters);
                    if (currentPage < r.body().getInfo().getPages()) {
                        currentPage++;
                    } else {
                        hasNextPage = false;
                    }
                } else {
                    respuesta = Either.left(Constantes.ERROR_AL_OBTENER_LOS_PERSONAJES);
                    hasNextPage = false;
                }
            }
            if (respuesta == null) {
                respuesta = Either.right(allCharacters);
            }
        } catch (IOException e) {
            respuesta = Either.left(e.getMessage());
        }
        return respuesta;
    }

    public List<String> getAllCharacterSpecies() {
        List<String> allTypes = new ArrayList<>();

        Either<String, List<Characters>> charactersResult = getCharacters();

        if (charactersResult.isRight()) {
            List<Characters> charactersList = charactersResult.get();

            for (Characters character : charactersList) {
                String type = character.getSpecies();

                if (!allTypes.contains(type)) {
                    allTypes.add(type);
                }
            }
        }
        return allTypes;
    }

    @Override
    public Either<String, List<Episodes>> getEpisodesByUrl(List<String> urls) {
        Either<String, List<Episodes>> respuesta;
        List<Episodes> episodios = new ArrayList<>();

        List<String> errors = new ArrayList<>();

        for (String url : urls) {
            try {
                int id = Integer.parseInt(url.substring(url.lastIndexOf("/") + 1));
                Response<Episodes> r = charactersApi.getEpisodeById(id).execute();

                if (r.isSuccessful() && r.body() != null) {
                    episodios.add(r.body());
                } else {
                    errors.add(Constantes.ERROR_AL_OBTENER_EL_EPISODIO);
                }
            } catch (IOException e) {
                errors.add(Constantes.ERROR_AL_OBTENER_EL_EPISODIO);
            }
        }
        if (!errors.isEmpty()) {
            respuesta = Either.left(Constantes.ERRORES_AL_OBTENER_EPISODIOS);
        } else {
            respuesta = Either.right(episodios);
        }

        respuesta = errors.isEmpty() ? respuesta : Either.left(Constantes.ERRORES_AL_OBTENER_EPISODIOS);

        return respuesta;
    }
}
