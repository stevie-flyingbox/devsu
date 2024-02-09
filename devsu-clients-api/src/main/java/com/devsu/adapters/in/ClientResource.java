package com.devsu.adapters.in;

import com.devsu.domain.ports.in.CreateClientPort;
import com.devsu.domain.ports.in.DeleteClientPort;
import com.devsu.domain.ports.in.GetClientsPort;
import com.devsu.domain.ports.in.UpdateClientPort;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/clientes")
public class ClientResource {
  private final CreateClientPort createClientPort;
  private final UpdateClientPort updateClientPort;
  private final DeleteClientPort deleteClientPort;
  private final GetClientsPort getClientPort;

  @GetMapping
  public ResponseEntity<List<ClientResponse>> getClients(@RequestParam(required = false) Integer clientId){
    return ResponseEntity.of(Optional.of(getClientPort.execute(clientId)));
  }

  @PostMapping()
  public ResponseEntity<ClientResponse> createClient(@Valid @RequestBody NewClientRequest request){
    return ResponseEntity.of(Optional.of(createClientPort.execute(request)));
  }

  @PutMapping()
  public ResponseEntity<ClientResponse> updateClient(@Valid @RequestBody UpdateClientRequest request){
    return ResponseEntity.of(Optional.of(updateClientPort.execute(request)));
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteClient(@RequestParam(required = true) Integer clientId) {
    deleteClientPort.execute(clientId);
  }

}
