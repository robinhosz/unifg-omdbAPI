package io.github.robinhosz.unifg.utils;

import io.github.robinhosz.unifg.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MenuUtil {
    public static void imprimeMenu(Movie movie) {
    String[][] dados = {
            { "Nome do Filme", "Ano", "Genero" },
            { movie.getTitle(), movie.getYear(), movie.getGenre() }
    };

    // Obter o número de colunas na tabela
    int numColunas = dados[0].length;

    // Definir o tamanho máximo de cada coluna
    int[] larguraColunas = new int[numColunas];
        for (String[] linha : dados) {
        for (int i = 0; i < numColunas; i++) {
            int larguraAtual = linha[i].length();
            if (larguraAtual > larguraColunas[i]) {
                larguraColunas[i] = larguraAtual;
            }
        }
    }

    // Criar uma instância de StringBuilder
    StringBuilder tabela = new StringBuilder();

    // Construir a linha superior da tabela
        for (int i = 0; i < numColunas; i++) {
        tabela.append("+");
        tabela.append("-".repeat(larguraColunas[i] + 2));
    }
        tabela.append("+\n");

    // Construir o cabeçalho da tabela
        for (int i = 0; i < numColunas; i++) {
        tabela.append("| ");
        tabela.append(formatarTexto(dados[0][i], larguraColunas[i]));
    }
        tabela.append("|\n");

    // Construir as linhas de dados
        for (int linha = 1; linha < dados.length; linha++) {
        for (int coluna = 0; coluna < numColunas; coluna++) {
            tabela.append("| ");
            tabela.append(formatarTexto(dados[linha][coluna], larguraColunas[coluna]));
        }
        tabela.append("|\n");
    }

    // Construir a linha inferior da tabela
        for (int i = 0; i < numColunas; i++) {
        tabela.append("+");
        tabela.append("-".repeat(larguraColunas[i] + 2));
    }
        tabela.append("+\n");

    // Imprimir a tabela no console
        System.out.println(tabela.toString());
}

    // Método para formatar o texto e alinhar à direita
    private static String formatarTexto(String texto, int largura) {
        return String.format("%-" + largura + "s", texto);
    }
}
