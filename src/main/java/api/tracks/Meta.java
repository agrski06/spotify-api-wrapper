package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Meta {
    private String analyzerVersion;
    private String platform;
    private String detailedStatus;
    private Integer statusCode;
    private Integer timestamp;
    private Float analysisTime;
    private String inputProcess;
}
