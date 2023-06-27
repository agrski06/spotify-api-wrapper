package api.auth;

import java.util.Arrays;

public enum AuthScope {
    UGC_IMAGE_UPLOAD,
    USER_READ_PLAYBACK_STATE,
    USER_MODIFY_PLAYBACK_STATE,
    USER_READ_CURRENTLY_PLAYING,
    APP_REMOTE_CONTROL,
    PLAYLIST_READ_PRIVATE,
    PLAYLIST_READ_COLLABORATIVE,
    PLAYLIST_MODIFY_PRIVATE,
    PLAYLIST_MODIFY_PUBLIC,
    USER_FOLLOW_MODIFY,
    USER_FOLLOW_READ,
    USER_READ_PLAYBACK_POSITION,
    USER_TOP_READ,
    USER_READ_RECENTLY_PLAYED,
    USER_LIBRARY_MODIFY,
    USER_LIBRARY_READ,
    USER_READ_EMAIL,
    USER_READ_PRIVATE,
    USER_SOA_LINK,
    USER_SOA_UNLINK,
    USER_MANAGE_ENTITLEMENTS,
    USER_MANAGE_PARTNER,
    USER_CREATE_PARTNER;

    @Override
    public String toString() {
        return this.name().toLowerCase().replace('_', '-');
    }

    public static AuthScope fromString(String name) {
        String value = name.toUpperCase().replace('-', '_');
        if (Arrays.stream(AuthScope.values())
                .anyMatch(authScope -> authScope.name().equals(value))) {
            return AuthScope.valueOf(value);
        }
        return null;
    }

}
