package com.parthraval.cloudshift.common.dto;

public record ValidationError(

        String field,

        String message

) {
}