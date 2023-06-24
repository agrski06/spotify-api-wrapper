package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnalyzedTrack {
    private Integer numSamples;
    private Float duration;
    private String sampleMd5;
    private Integer offsetSeconds;
    private Integer windowSeconds;
    private Integer analysisSampleRate;
    private Integer analysisChannels;
    private Float endOfFadeIn;
    private Float startOfFadeOut;
    private Float loudness;
    private Float tempo;
    private Float tempoConfidence;
    private Integer timeSignature;
    private Float timeSignatureConfidence;
    private Integer key;
    private Float keyConfidence;
    private Integer mode;
    private Float modeConfidence;
    private String codestring;
    private Float codeVersion;
    private String echoprintstring;
    private Float echoprintVersion;
    private String synchstring;
    private Float synchVersion;
    private String rhythmstring;
    private Float rhythmVersion;
}
