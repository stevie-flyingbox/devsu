package com.devsu.domain.ports.in;

import com.devsu.adapters.in.AccountResponse;
import com.devsu.adapters.in.UpdateAccountRequest;
import com.devsu.domain.ports.UseCase;

public interface UpdateAccountPort extends UseCase<UpdateAccountRequest, AccountResponse> {
}
