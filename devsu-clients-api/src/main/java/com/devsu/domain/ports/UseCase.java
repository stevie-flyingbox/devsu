package com.devsu.domain.ports;

public interface UseCase<I,O> {
  O execute (I inputCommand);
}
