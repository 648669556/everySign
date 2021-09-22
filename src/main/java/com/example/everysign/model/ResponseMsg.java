package com.example.everysign.model;

import lombok.Data;

@Data
public class ResponseMsg<T> {
    Boolean isSuccess;
    T content;
}
