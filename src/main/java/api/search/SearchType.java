package api.search;

public enum SearchType {
    ALBUM, ARTIST, PLAYLIST, TRACK, SHOW, EPISODE, AUDIOBOOK;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
