package ee.valiit.shroomshareback;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE("A"),
    PENDIG("P"),
    DEACTIVATED("D");

    private final String code;

    Status(String code) {
        this.code = code;
    }
}
