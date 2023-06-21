package requester.models;

import java.util.Set;

public class Genre {
    Set<String> names;

    public Genre() {

    }

    public Genre(Set<String> names) {
        this.names = names;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }
}
