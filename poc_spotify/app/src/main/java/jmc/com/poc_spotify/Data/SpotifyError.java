package jmc.com.poc_spotify.Data;

import jmc.com.poc_spotify.Entity.ErrorDetails;
import jmc.com.poc_spotify.Entity.ErrorResponse;
import retrofit.RetrofitError;

/**
 * Created by jogan1075 on 28-07-17.
 */

public class SpotifyError extends Exception {

    private final RetrofitError mRetrofitError;
    private final ErrorDetails mErrorDetails;

    public static SpotifyError fromRetrofitError(RetrofitError error) {
        ErrorResponse errorResponse = null;

        try {
            errorResponse = (ErrorResponse) error.getBodyAs(ErrorResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (errorResponse != null && errorResponse.error != null) {
            String message = errorResponse.error.status + " " + errorResponse.error.message;
            return new SpotifyError(error, errorResponse.error, message);
        } else {
            return new SpotifyError(error);
        }
    }

    public SpotifyError(RetrofitError retrofitError, ErrorDetails errorDetails, String message) {
        super(message, retrofitError);
        mRetrofitError = retrofitError;
        mErrorDetails = errorDetails;
    }

    public SpotifyError(RetrofitError retrofitError) {
        super(retrofitError);
        mRetrofitError = retrofitError;
        mErrorDetails = null;
    }

    public RetrofitError getRetrofitError() {
        return mRetrofitError;
    }

    public boolean hasErrorDetails() {
        return mErrorDetails != null;
    }

    public ErrorDetails getErrorDetails() {
        return mErrorDetails;
    }
}
