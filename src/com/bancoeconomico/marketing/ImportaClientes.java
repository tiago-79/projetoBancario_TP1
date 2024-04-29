package com.bancoeconomico.marketing;

import com.bancoeconomico.Main;
import com.bancoeconomico.model.Cliente;
import com.bancoeconomico.model.ClientePF;
import com.bancoeconomico.model.ClientePJ;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ImportaClientes {
    public static List<String> importarClientes(String enderecoOrigem) throws IOException {

        Path pathPessoas = Path.of(enderecoOrigem);
        Stream<String> linhas = Files.lines(pathPessoas);

        List<String[]> listaColunasClientes = Files.lines(pathPessoas)
                .skip(1)
                .map(linhaCliente -> linhaCliente.split(","))
                .filter(linha -> "1".equals(linha[3]) || VerificaSeMaiorDeIdade.temMaisDe18(linha[1]))    // filtrar PJ e maiores de idade
                .toList();

        return listaColunasClientes.stream()
                .map(colunaCliente -> {
                    return getCliente(colunaCliente);
                })
                .map(cliente -> {
                    Main.deposito(cliente, BigDecimal.valueOf(50));
                    return cliente;
                })
                .map(cliente -> (cliente.toString()))
                .toList();
    }

    private static Cliente getCliente(String[] colunaCliente) {
        if(Integer.parseInt(colunaCliente[3]) == 2){
            ClientePF clientePF = new ClientePF(colunaCliente[0], colunaCliente[2]);
            return clientePF;
        } else {
            ClientePJ clientePJ = new ClientePJ(colunaCliente[0], colunaCliente[2]);
            return clientePJ;
        }
    }
}
