package ar.uba.fi.compiladores.parte3;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa un algoritmo de lexeo a partir de un lenguage.
 */
public class ManualLexer<S,T>{
    private LanguageAutomata<S,T> language;
    public ManualLexer(LanguageAutomata<S,T> language){
        this.language=language;
    }
    /**
     * extracts a token from a string and returns the next state of the LanguageAutomata and the length of the extracted token.
     * @param program
     * @return
     */
    public SingleTokenExtraction<S> extractToken(String program){
        S currentState = this.language.getInitialState();
        S lastFinalState = null;
        int lastFinalStateIndex = 0;
        for (int i = 0; i < program.length(); i++) {
            currentState = this.language.transition(currentState, program.charAt(i));
            if(this.language.getDeadState() == currentState){
                if (lastFinalState == null) lastFinalState = currentState;
                return new SingleTokenExtraction(lastFinalState,lastFinalStateIndex);
            }
            if(this.language.isFinal(currentState)){
                lastFinalState=currentState;
            }
            lastFinalStateIndex=i;
        }
        return new SingleTokenExtraction(lastFinalState,lastFinalStateIndex);
    }
    public List<Token<T>> lex(String program) throws BadTokenException{
        List<Token<T>> tokens =  new ArrayList<Token<T>>();
        SingleTokenExtraction<S> result = this.extractToken(program);
        while (result.getFinalState() != null){
            System.out.println(result);
            if (result.getFinalState()==this.language.getErrorState()){
                throw new BadTokenException(program.substring(0,result.getLastFinalIndex()+1));
            }
            if (result.getFinalState() != this.language.getDeadState()){ 
                Token<T> t = new Token(this.language.stateToTokenType(result.getFinalState()),program.substring(0,result.getLastFinalIndex()+1));
                tokens.add(t);
            }
            program = program.substring(result.getLastFinalIndex()+1);
            result = this.extractToken(program);
        }
        tokens.add(new Token(this.language.eofTokenType(),null));
        return tokens;
    }

}