package api.genres;

import lombok.Data;

import java.util.Set;

@Data
public class GenreResponse {
    Set<String> genres;
}
