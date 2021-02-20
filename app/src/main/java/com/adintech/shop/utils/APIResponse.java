package com.adintech.shop.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import static com.adintech.shop.utils.Status.ERROR;
import static com.adintech.shop.utils.Status.LOADING;
import static com.adintech.shop.utils.Status.SUCCESS;

public class APIResponse {

    public final Status status;
    public final Integer requestID;

    @Nullable
    public final Object data;

    @Nullable
    public final Throwable error;

    private APIResponse(Status status, @Nullable Object data, @Nullable Throwable error, @NonNull Integer requestID) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.requestID = requestID;
    }

    public static APIResponse loading(@NonNull Integer requestID) {
        return new APIResponse(LOADING, null, null, requestID);
    }

    public static APIResponse success(@NonNull Object data, @NonNull Integer requestID) {
        return new APIResponse(SUCCESS, data, null, requestID);
    }

    public static APIResponse error(@NonNull Throwable error, @NonNull Integer requestID) {
        return new APIResponse(ERROR, null, error, requestID);
    }


}
