package com.devsu.domain.commands;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMovementsCommand {
  private Integer clientId;
  private LocalDate from;
  private LocalDate to;

}
