package api.albums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AlbumPage {
    private String href;
    private Integer limit;
    private String next;
    private Integer offset;
    private String previous;
    private Integer total;
    private Set<SimplifiedAlbum> items;
}
