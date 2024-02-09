package com.devsu.adapters.out;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movimientos")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MovementEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer idMovimiento;

  private LocalDate fecha;
  private String tipo;
  private BigDecimal saldoInicial;
  private BigDecimal saldo;
  private BigDecimal valor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "idCuenta", referencedColumnName = "idCuenta")
  private AccountEntity cuenta;

}
