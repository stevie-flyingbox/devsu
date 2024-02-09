package com.devsu.domain.ports.in;

import com.devsu.adapters.in.ClientResponse;
import com.devsu.adapters.in.UpdateClientRequest;
import com.devsu.domain.ports.UseCase;

public interface UpdateClientPort extends UseCase<UpdateClientRequest, ClientResponse> {
}
