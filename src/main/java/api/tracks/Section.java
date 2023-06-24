package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Section {
    private Float start;
    private Float duration;
    private Float confidence;
    private Float loudness;
    private Float tempo;
    private Float tempoConfidence;
    private Integer key;
    private Float keyConfidence;
    private Integer mode;
    private Float modeConfidence;
    private Integer timeSignature;
    private Float timeSignatureConfidence;
}
