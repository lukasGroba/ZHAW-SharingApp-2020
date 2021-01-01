package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

public class BackendError extends Exception{
    public BackendError(String message) {
        super(message);
    }
}
