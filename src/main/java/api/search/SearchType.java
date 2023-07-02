package api.search;

import java.util.Arrays;

public enum SearchType {
    ALBUM, ARTIST, PLAYLIST, TRACK, SHOW, EPISODE, AUDIOBOOK;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

    public static String getAllAsCommaSeparatedString() {
        return String.join(",", Arrays.stream(SearchType.values()).map(SearchType::toString).toList());
    }
}
