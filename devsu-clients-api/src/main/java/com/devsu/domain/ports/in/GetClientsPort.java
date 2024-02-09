package com.devsu.domain.ports.in;

import com.devsu.adapters.in.ClientResponse;
import com.devsu.domain.ports.UseCase;
import java.util.List;

public interface GetClientsPort extends UseCase<Integer, List<ClientResponse>> {
}
