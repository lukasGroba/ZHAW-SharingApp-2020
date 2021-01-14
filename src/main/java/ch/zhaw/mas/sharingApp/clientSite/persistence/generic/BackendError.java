package ch.zhaw.mas.sharingApp.clientSite.persistence.generic;

/**
 * Error that is thrown if there is a error response from the backend.
 *
 * @author Noemi Kälin
 */
public class BackendError extends Exception{
    public BackendError(String message) {
        super(message);
    }
}
