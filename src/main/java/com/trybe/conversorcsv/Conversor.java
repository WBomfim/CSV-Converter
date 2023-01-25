package com.trybe.conversorcsv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class Conversor.
 *
 */
public class Conversor {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public static void main(String[] args) throws IOException {
    File pastaDeEntradas = new File("./entradas/");
    File pastaDeSaidas = new File("./saidas/");
    new Conversor().converterPasta(pastaDeEntradas, pastaDeSaidas);
  }

  /**
   * Converte todos os arquivos CSV da pasta de entradas. Os resultados são gerados
   * na pasta de saídas, deixando os arquivos originais inalterados.
   *
   * @param pastaDeEntradas Pasta contendo os arquivos CSV gerados pela página web.
   * @param pastaDeSaidas Pasta em que serão colocados os arquivos gerados no formato
   *                      requerido pelo subsistema.
   *
   * @throws IOException Caso ocorra algum problema ao ler os arquivos de entrada ou
   *                     gravar os arquivos de saída.
   */
  public void converterPasta(File pastaDeEntradas, File pastaDeSaidas) throws IOException {
    if (pastaDeEntradas.isDirectory() && pastaDeEntradas.canRead()) {
      for (File arquivoEntreda : pastaDeEntradas.listFiles()) {
        File arquivoDeSaida = new File(pastaDeSaidas + File.separator + arquivoEntreda.getName());
        converterArquivo(arquivoEntreda, arquivoDeSaida);
      }
    }
  }

  public void converterArquivo(File arquivoDeEntrada, File arquivoDeSaida) throws IOException {
    if (arquivoDeEntrada.isFile() && arquivoDeEntrada.canRead()) {
      BufferedReader reader = new BufferedReader(new FileReader(arquivoDeEntrada));
      BufferedWriter writer = new BufferedWriter(new FileWriter(arquivoDeSaida));

      String linha = reader.readLine();
      writer.write(linha);
      writer.newLine();
      linha = reader.readLine();

      while (linha != null) {
        String[] campos = linha.split(",");

        String nome = campos[0].toUpperCase();


        writer.write(linha);
        writer.newLine();
        linha = reader.readLine();
      }

      reader.close();
      writer.close();
    }
  }

  public String formatarData(String data) {
    String[] campos = data.split("/");
    return campos[2] + "-" + campos[1] + "-" + campos[0];
  }

}
