package org.mkt.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private int status;
    private String message;
    private T data;

    public Response(int status, T data) {
        this.status = status;
        this.data = data;
    }

    public Response(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
