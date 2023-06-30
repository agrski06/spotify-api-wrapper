package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExternalIds {
    private String isrc;
    private String ean;
    private String upc;
}
