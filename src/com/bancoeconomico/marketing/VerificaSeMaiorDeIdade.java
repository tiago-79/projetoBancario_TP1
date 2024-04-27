package com.bancoeconomico.marketing;

import com.bancoeconomico.model.Cliente;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class VerificaSeMaiorDeIdade {

    public static boolean temMaisDe18 (String dadosCliente) {
        final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        int idade = (int) ChronoUnit.YEARS.between(LocalDate.parse(dadosCliente + " 00:00:00", DATE_TIME_FORMATTER), LocalDate.now());

        if (idade >= 18) { return true; }

        return false;
    }
}
