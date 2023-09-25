package com.nttdata.reactivo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
  private Integer id;
  private String lastName;
  private String firstName;
}
