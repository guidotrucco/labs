package ar.uba.fi.compiladores.parte1;

import java.util.regex.Pattern;

public class RegexLibrary {
    /**
     * Regex para decimales que no empiezan en 0
     * @return
     */
    Pattern getDecimalsRegex(){ return Pattern.compile("[1-9][0-9]*"); }

    /**
     * Regex para decimales que no empiezan en 0
     * @return
     */
    Pattern getHexaRegex(){ return Pattern.compile("[1-9A-F][0-9A-F]*"); }

    /**
     * Regex para brainfuck. 
     * @return
     */
    Pattern getBrainfuckRegex(){ return Pattern.compile("[\\+\\-\\[\\]<>]+"); }

    /**
     * Nombres: Palabras con mayusculas de al menos 3 letras.
     * @return
     */
    Pattern getNamesRegex(){ return Pattern.compile("[A-Z][a-z]{2,}"); }

}
