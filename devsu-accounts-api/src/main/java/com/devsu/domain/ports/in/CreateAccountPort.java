package com.devsu.domain.ports.in;

import com.devsu.adapters.in.NewAccountRequest;
import com.devsu.adapters.in.AccountResponse;
import com.devsu.domain.ports.UseCase;

public interface CreateAccountPort extends UseCase<NewAccountRequest, AccountResponse> {
}
