package br.com.blinkdev.leadsponge.errorValidate;

public abstract class ErroMessage {

    protected ResourceBadRequestException notFouldId(Long id, String nome) {
        return new ResourceBadRequestException("I couldn't find a " + nome + " with id: " + id);
    }

    protected ResourceNotFoundException notFould(String nome) {
        return new ResourceNotFoundException("Could not find " + nome);
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
