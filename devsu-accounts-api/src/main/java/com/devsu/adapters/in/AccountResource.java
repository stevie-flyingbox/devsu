package com.devsu.adapters.in;

import com.devsu.domain.commands.GetAccountCommand;
import com.devsu.domain.ports.in.CreateAccountPort;
import com.devsu.domain.ports.in.DeleteAccountPort;
import com.devsu.domain.ports.in.GetAccountPort;
import com.devsu.domain.ports.in.UpdateAccountPort;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/cuentas")
public class AccountResource {
  private final CreateAccountPort createAccountPort;
  private final UpdateAccountPort updateAccountPort;
  private final DeleteAccountPort deleteAccountPort;
  private final GetAccountPort getAccountPort;

  @GetMapping(value = {"/{clientId}","/{clientId}/{accountId}"})
  public ResponseEntity<List<AccountResponse>> getClientAccounts(@PathVariable Integer clientId,
                                                                 @PathVariable(required = false) Integer accountId){
    return ResponseEntity.of(Optional.of(getAccountPort.execute(GetAccountCommand.builder()
            .clientId(clientId)
            .accountId(accountId)
            .build())));
  }

  @PostMapping()
  public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody NewAccountRequest request){
    return ResponseEntity.of(Optional.of(createAccountPort.execute(request)));
  }

  @PutMapping()
  public ResponseEntity<AccountResponse> updateClient(@Valid @RequestBody UpdateAccountRequest request){
    return ResponseEntity.of(Optional.of(updateAccountPort.execute(request)));
  }

  @DeleteMapping
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteClient(@RequestParam(required = true) Integer clientId) {
    deleteAccountPort.execute(clientId);
  }

}
