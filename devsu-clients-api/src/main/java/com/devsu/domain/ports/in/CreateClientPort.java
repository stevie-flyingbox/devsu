package com.devsu.domain.ports.in;

import com.devsu.adapters.in.NewClientRequest;
import com.devsu.adapters.in.ClientResponse;
import com.devsu.domain.ports.UseCase;

public interface CreateClientPort extends UseCase<NewClientRequest, ClientResponse> {
}
