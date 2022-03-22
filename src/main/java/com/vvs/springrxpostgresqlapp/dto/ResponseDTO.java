package com.vvs.springrxpostgresqlapp.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResponseDTO {
  private Object data;
}
