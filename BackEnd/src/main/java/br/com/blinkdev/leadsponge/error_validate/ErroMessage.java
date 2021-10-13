package br.com.blinkdev.leadsponge.error_validate;

public abstract class ErroMessage {

    protected ResourceBadRequestException notFouldId(Long id, String name) {
        return new ResourceBadRequestException("I couldn't find a " + name + " with id: " + id);
    }

    protected ResourceNotFoundException notFould(String name) {
        return new ResourceNotFoundException("Could not find " + name);
    }

    protected ResourceNotFoundException notFouldError() {
        return new ResourceNotFoundException("Miscellaneous error, please contact the tool administrator.");
    }

    protected ResourceNotFoundException otherMensagemNotFound(String msg) {
        return new ResourceNotFoundException(msg);
    }

    protected ResourceBadRequestException otherMensagemBadRequest(String msg) {
        return new ResourceBadRequestException(msg);
    }

}
