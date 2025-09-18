package ee.valiit.shroomshareback;

import lombok.Getter;

@Getter
public enum Error {

    INCORECT_CREDENTIALS("vale kasutajanimi või parool", 111);

    private final String message;
    private final Integer errorCode;

    Error(String message, Integer errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
