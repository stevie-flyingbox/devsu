package com.devsu.domain.ports.out;

import com.devsu.domain.models.Client;

public interface ClientPort {
  Client getClient(Integer clientId);
}
