package com.devsu.domain.ports.in;

import com.devsu.adapters.in.MovementRequest;
import com.devsu.adapters.in.MovementResponse;
import com.devsu.domain.ports.UseCase;

public interface SaveMovementPort extends UseCase<MovementRequest, MovementResponse> {
}
