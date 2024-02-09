package com.devsu.domain.ports.in;

import com.devsu.adapters.in.MovementResponse;
import com.devsu.domain.commands.GetMovementsCommand;
import com.devsu.domain.ports.UseCase;
import java.util.List;

public interface GetMovementsPort extends UseCase<GetMovementsCommand, List<MovementResponse>> {
}
