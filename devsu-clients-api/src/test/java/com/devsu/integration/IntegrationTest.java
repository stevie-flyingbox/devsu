package com.devsu.integration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void saveNewClient() throws Exception{
    String requestString="{ \"nombre\":\"XXXX XXXXXXX\", \"identificacion\":\"5555555\", \"direccion\":\"asdadad sdssd 55\", \"genero\":\"F\", \"edad\":20, \"telefono\":\"1-4564565\", \"password\":\"123456\" }";
    mockMvc.perform(post("/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestString))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("\"nombre\":\"XXXX XXXXXXX\"")));
  }
}
