package com.devsu.adapters.out.clients;

import com.devsu.commons.ApiException;
import com.devsu.commons.ErrorsEnum;
import com.devsu.domain.models.Client;
import com.devsu.domain.ports.out.ClientPort;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class ClientRestImpl implements ClientPort {

  @Value("${apis.clients.url}")
  private String clientApiUrl;
  @Override
  public Client getClient(Integer clientId) {
    try {
      RestTemplate restTemplate = new RestTemplate();
      String requestPath= clientApiUrl.replace("{id}", clientId.toString());
      ResponseEntity<List<ClientDto>> response = restTemplate
              .exchange(requestPath, HttpMethod.GET, null, new ParameterizedTypeReference<List<ClientDto>>(){}) ;

      return response.getBody().stream()
              .findFirst()
              .map(client -> Client.builder().id(client.getClientId()).nombre(client.getNombre()).build())
              .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND));

    }catch (Exception e){
      log.error("Error al intentar obtener cliente ", e);
      throw new ApiException(HttpStatus.NOT_FOUND, ErrorsEnum.CLIENT_NOT_FOUND);
    }

  }
}
