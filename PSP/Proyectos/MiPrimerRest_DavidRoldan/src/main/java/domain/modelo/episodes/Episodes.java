package domain.modelo.episodes;

import lombok.Data;

import java.util.List;

@Data
public class Episodes {
    private String airdate;
    private List<String> characters;
    private String created;
    private String name;
    private String episode;
    private int id;
    private String url;
}