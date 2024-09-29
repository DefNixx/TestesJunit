/*
Classe DnaAnalyzing utilizada para implementar o método calculaNucleotideos
@authors Nícolas André & Jefferson Eduardo
 */

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DnaAnalyzing
{
    public int[] calculaNucleotideos(String caminhoArquivo) throws IOException
    {
        int[] contagem = {0, 0, 0, 0, 0}; // A, C, G, T, Erros

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo)))
        {
            String linha = br.readLine();
            if (linha == null)
            {
                return null;
            }

            int tamanho = linha.length();
            for (char nucleotideo : linha.toCharArray())
            {
                switch (nucleotideo)
                {
                    case 'A': contagem[0]++; break;
                    case 'C': contagem[1]++; break;
                    case 'G': contagem[2]++; break;
                    case 'T': contagem[3]++; break;
                    default: contagem[4]++; break;
                }
            }

            // Verificar se o número de erros excede 10%
            if (contagem[4] > 0.1 * tamanho)
            {
                return null;
            }
        } catch (IOException e)
        {
            throw new IOException("Arquivo não encontrado ou erro de leitura.");
        }

        return contagem;
    }
}
