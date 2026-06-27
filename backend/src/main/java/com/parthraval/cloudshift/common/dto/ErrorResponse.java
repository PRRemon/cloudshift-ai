package com.parthraval.cloudshift.common.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse(

        boolean success,

        int status,

        String error,

        String message,

        String path,

        List<String> details,

        Instant timestamp

) {

    public static ErrorResponse of(
            int status,
            String error,
            String message,
            String path,
            List<String> details
    ) {

        return new ErrorResponse(
                false,
                status,
                error,
                message,
                path,
                details,
                Instant.now()
        );

    }

}