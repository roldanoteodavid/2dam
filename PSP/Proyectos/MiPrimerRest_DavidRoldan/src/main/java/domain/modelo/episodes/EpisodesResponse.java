package domain.modelo.episodes;

import lombok.Data;

import java.util.List;

@Data
public class EpisodesResponse {
    private List<Episodes> results;
    private Info info;
}