package domain.modelo.characters;

import lombok.Data;

import java.util.List;

@Data
public class Characters {
    private String image;
    private String gender;
    private String species;
    private String created;
    private Origin origin;
    private String name;
    private Location location;
    private List<String> episode;
    private int id;
    private String type;
    private String url;
    private String status;
}