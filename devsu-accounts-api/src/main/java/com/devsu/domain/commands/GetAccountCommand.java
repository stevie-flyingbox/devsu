package com.devsu.domain.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetAccountCommand {
  private Integer clientId;
  private Integer accountId;
}
