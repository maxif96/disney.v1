package com.api.disney.auth.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jwt {

    private String token;

}
