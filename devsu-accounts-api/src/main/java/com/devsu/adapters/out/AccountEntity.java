package com.devsu.adapters.out;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cuentas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class AccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idCuenta;
  private Integer idCliente;
  private String numero;
  private String tipo;
  private BigDecimal saldoInicial;
  private Boolean estado;

  @OneToMany(mappedBy = "cuenta", fetch = FetchType.LAZY)
  private Set<MovementEntity> movements;
}
