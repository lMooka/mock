package br.com.guiabolso.mock.util;

import java.util.Random;

public class WordGenerator {
    // Letras do alfabeto
    private static final String[] letters = {
            "b", "c", "ç", "d", "f", "g", "h", "j", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"
    };
    // Vogais do alfabeto
    private static final String[] vowels = {
            "a", "e", "i", "o", "u"
    };
    /*
     *                           Tamanho máximo de palavras              Espaços (última palavra não tem espaço)
     * Tamanho máximo da frase = maxWordCount * (maxSyllableCount * 2) + (maxWordCount - 1) = 54
     */
    // Quantidade mínima de palavras
    private static final int minWordCount = 3;
    // Quantidade máxima de palavras
    private static final int maxWordCount = 5;
    // Quantidade mínima de silabas na palavra
    private static final int minSyllableCount = 3;
    // Quantidade máxima de silabas na palavra
    private static final int maxSyllableCount = 5;

    public static String generatePhrase(Random random) {
        return generatePhrase(random, minWordCount, maxWordCount, minSyllableCount, maxSyllableCount);
    }

    public static String generatePhrase(Random random, int minWordCount, int maxWordCount, int minSyllableCount, int maxSyllableCount) {
        // Gera de forma aleatória o tamanho da frase
        int wordCount = minWordCount + random.nextInt(maxWordCount - minWordCount);
        StringBuilder sb = new StringBuilder();

        // Inicia a geração da frase
        for (int i = 0; i < wordCount; i++) {
            // Gera de forma aleatória o tamanho da palavra
            int syllableCount = minSyllableCount + random.nextInt(maxSyllableCount - minSyllableCount);
            // Incia a geração de silabas da palavra
            for (int j = 0; j < syllableCount; j += 1) {
                sb.append(letters[random.nextInt(letters.length)]).append(vowels[random.nextInt(vowels.length)]);
            }
            // Verifica se há necessidade de adicionar espaço.
            if (i < wordCount - 1)
                sb.append(" ");
        }
        return sb.toString();
    }
}
