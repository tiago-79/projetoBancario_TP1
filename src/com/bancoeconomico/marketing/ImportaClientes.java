package com.bancoeconomico.marketing;

import com.bancoeconomico.Main;
import com.bancoeconomico.model.ClientePF;
import com.bancoeconomico.model.ClientePJ;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImportaClientes {
    public static List<String> importarClientes(String enderecoOrigem) throws IOException {
        List<String> novosClientes = new ArrayList<>();

        Path pathPessoas = Path.of(enderecoOrigem);

        List<String[]> listaColunas = Files.lines(pathPessoas)
                .skip(1)
                .map(linhaCliente -> linhaCliente.split(","))
                .toList();

        List<ClientePF> novosClientesPF = listaColunas.stream()
                .filter(colunasCliente -> Integer.parseInt(colunasCliente[3]) == 2)
                .filter(colunasCliente -> VerificaSeMaiorDeIdade.temMaisDe18(colunasCliente[1]))
                .map(colunasCliente -> new ClientePF(colunasCliente[0], colunasCliente[2]))
                .toList();
        List<ClientePJ> novosClientesPJ = listaColunas.stream()
                .filter(colunasCliente -> Integer.parseInt(colunasCliente[3]) == 1)
                .map(colunasCliente -> new ClientePJ(colunasCliente[0], colunasCliente[2]))
                .toList();

        for(ClientePF clientePF : novosClientesPF){
            Main.deposito(clientePF,BigDecimal.valueOf(50));
        }
        for(ClientePJ clientePJ : novosClientesPJ){
            Main.deposito(clientePJ,BigDecimal.valueOf(50));
        }

        novosClientes.addAll(novosClientesPF.stream()
                .map(clientePF -> clientePF.toString())
                .toList());
        novosClientes.addAll(novosClientesPJ.stream()
                .map(clientePJ -> clientePJ.toString())
                .toList());

        return novosClientes;
    }
}
