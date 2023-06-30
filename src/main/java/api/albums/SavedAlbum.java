package api.albums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavedAlbum {
    private String addedAt;
    private Album album;
}
