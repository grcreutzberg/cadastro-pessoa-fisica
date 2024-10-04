package com.cadastro.DTO;

public class CadastroResponse<T> {

    private final Status status;

    private final String statusMessage;

    private final T content;

    public CadastroResponse(Status status, String statusMessage, T content) {
        this.status = status;
        this.statusMessage = statusMessage;
        this.content = content;
    }

    public static <T> CadastroResponse<T> ok(final T content) {
        return new CadastroResponse<>(Status.OK, null, content);
    }

    public static <T> CadastroResponse<T> error(final String statusMessage) {
        return new CadastroResponse<>(Status.ERROR, statusMessage, null);
    }

    public static <T> CadastroResponse<T> errorWithContent(final String statusMessage, final T content) {
        return new CadastroResponse<>(Status.ERROR, statusMessage, content);
    }

    public static <T> CadastroResponse<T> notFound(final String statusMessage) {
        return new CadastroResponse<>(Status.NOT_FOUND, statusMessage, null);
    }

    public boolean isError() {
        return getStatus() == Status.ERROR;
    }

    public boolean hasContent() {
        return getContent() != null;
    }

    public Status getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public T getContent() {
        return content;
    }

    public enum Status {
        OK,
        ERROR,
        NOT_FOUND
    }
}
