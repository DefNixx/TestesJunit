/*
* classe de testes para verificar se o metodo está funcionando com êxito
* @authors nícolas andré & jefferson eduardo
 */


import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class DnaAnalyzingTeste
{

    private final String pathValid = "validSequence.txt";
    private final String pathInvalid = "invalidSequence.txt";
    private final String pathNotFound = "notFoundSequence.txt";

    @BeforeEach
    public void setUp() throws IOException
    {
        // Criar arquivos de teste
        FileWriter validWriter = new FileWriter(pathValid);
        validWriter.write("AAAGTCTGAC");
        validWriter.close();

        FileWriter invalidWriter = new FileWriter(pathInvalid);
        invalidWriter.write("ABC TEM FALHA");
        invalidWriter.close();
    }

    @AfterEach
    public void tearDown()
    {
        new File(pathValid).delete();
        new File(pathInvalid).delete();
    }

    @Test
    @DisplayName("Verifica se a contagem correta é retornada para uma sequência válida")
    public void testSequenciaValida() throws IOException
    {
        DnaAnalyzing analyzer = new DnaAnalyzing();
        int[] resultado = analyzer.calculaNucleotideos(pathValid);
        int[] esperado = {4, 2, 2, 2, 0};
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica se o valor retornado é null para uma sequência com mais de 10% de caracteres inválidos")
    public void testSequenciaInvalida() throws IOException
    {
        DnaAnalyzing analyzer = new DnaAnalyzing();
        int[] resultado = analyzer.calculaNucleotideos(pathInvalid);
        assertNull(resultado);
    }

    @Test
    @DisplayName("Verifica se uma exceção é lançada quando o arquivo não é encontrado")
    public void testArquivoNaoEncontrado()
    {
        DnaAnalyzing analyzer = new DnaAnalyzing();
        assertThrows(IOException.class, () ->
        {
            analyzer.calculaNucleotideos(pathNotFound);
        });
    }


}
