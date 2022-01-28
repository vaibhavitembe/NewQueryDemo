package com.zplus.dto;

import lombok.Data;

@Data
public class SendMailDto {
    private String name;
    private String to;
    private String from;
    private String subject;
    private String message;
}


