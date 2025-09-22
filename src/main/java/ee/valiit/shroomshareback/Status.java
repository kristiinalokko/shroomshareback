package ee.valiit.shroomshareback;

import lombok.Getter;

@Getter
public enum Status {

    ACTIVE("A"),
    PENDING("P"),
    DEACTIVATED("D");

    private final String code;

    Status(String code) {
        this.code = code;
    }
}
