package com.automation.integrations.rest.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CriarPostResponse {

    private int id;
    private String title;
    private String body;
    private int userId;
}