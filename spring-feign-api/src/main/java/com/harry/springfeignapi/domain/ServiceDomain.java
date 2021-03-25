package com.harry.springfeignapi.domain;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceDomain implements Serializable {

  private String name;
}
