package domain.modelo.characters;

import lombok.Data;

import java.util.List;

@Data
public class CharactersResponse {
    private List<Characters> results;
    private Info info;
}