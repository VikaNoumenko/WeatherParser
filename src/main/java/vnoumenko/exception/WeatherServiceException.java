package vnoumenko.exception;

/**
 * 17.10.2017
 * WeatherServiceException
 *
 * @author Victoria Noumenko
 * @version v1.0
 */

public class WeatherServiceException extends Exception{


    private String error;

    public WeatherServiceException(String error) {
        this.error = error;
    }

    public WeatherServiceException(String error, Exception cause) {
        super(cause);
        this.error = error;
    }

    public WeatherServiceException(String error, String message, Exception cause) {
        super(message, cause);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
