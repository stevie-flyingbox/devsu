package com.devsu.domain.ports.in;

import com.devsu.adapters.in.AccountResponse;
import com.devsu.domain.commands.GetAccountCommand;
import com.devsu.domain.ports.UseCase;
import java.util.List;

public interface GetAccountPort extends UseCase<GetAccountCommand, List<AccountResponse>> {
}
