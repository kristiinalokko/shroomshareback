package ee.valiit.shroomshareback;

import lombok.Getter;

@Getter
public enum Error {

    INCORRECT_CREDENTIALS("Vale kasutajanimi või parool", 111),
    INCORRECT_USERNAME("Kasutajanimi on juba kasutusel", 888),
    LOCATION_NOT_FOUND("Sellist asukohta ei leidnud", 999),
    COMMENT_NOT_FOUND("Selle asukohta kohta ei leidnud kommentaare", 555);

    private final String message;
    private final Integer errorCode;

    Error(String message, Integer errorCode) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
