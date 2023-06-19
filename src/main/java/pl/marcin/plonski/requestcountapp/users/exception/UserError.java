package pl.marcin.plonski.requestcountapp.users.exception;

public enum UserError {
    USER_NOT_FOUND("Nie znaleziono podanego użytkownika");

    private String message;

    UserError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
