package com.devsu.domain.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import com.devsu.adapters.in.ClientResponse;
import com.devsu.adapters.out.ClientEntity;
import com.devsu.adapters.out.ClientPersistenceImpl;
import com.devsu.adapters.out.ClientRepository;
import com.devsu.adapters.out.PersonEntity;
import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.ports.out.ClientPersistencePort;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GetClientUseCaseTest {
  @InjectMocks
  private GetClientsUseCase getClientsUseCase;

  @Mock
  private ClientRepository clientRepository;

  private ClientPersistencePort clientPersistencePort;

  @Before
  public void setup(){
    clientPersistencePort= new ClientPersistenceImpl(clientRepository);
    getClientsUseCase= new GetClientsUseCase(clientPersistencePort);
  }
  @Test
  public void getClientSuccess(){
    Integer clientId=1;
    ClientEntity client= ClientEntity.builder()
            .id(1)
            .estado(true)
            .persona(PersonEntity.builder().nombre("XXXXXX").telefono("123123").build())
            .build();
    given(clientRepository.findById(clientId)).willReturn(Optional.of(client));
    List<ClientResponse> response= getClientsUseCase.execute(clientId);
    assertNotNull(response);
    assertEquals("123123", response.get(0).getTelefono());
    assertEquals(response.size(), 1);
  }

  @Test
  public void getInvalidClientError(){
    Integer clientId=1;
    given(clientRepository.findById(clientId)).willReturn(Optional.empty());
    ApiException exception= assertThrows(ApiException.class, () ->getClientsUseCase.execute(clientId));
    assertEquals(exception.getCode(), ErrorsEnum.CLIENT_NOT_FOUND.getCode());
  }

  @Test
  public void getClientsSuccess(){
    ClientEntity client1= ClientEntity.builder()
            .id(1)
            .estado(true)
            .persona(PersonEntity.builder().nombre("XXXXXX").telefono("123123").build())
            .build();
    ClientEntity client2= ClientEntity.builder()
            .id(2)
            .estado(true)
            .persona(PersonEntity.builder().nombre("YYYYYYY").telefono("2222222").build())
            .build();
    given(clientRepository.findAll()).willReturn(List.of(client1, client2));
    List<ClientResponse> response= getClientsUseCase.execute(null);
    assertNotNull(response);
    assertEquals("123123", response.get(0).getTelefono());
    assertEquals(response.size(), 2);
  }
}
