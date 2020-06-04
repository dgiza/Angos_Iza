package ec.edu.espe.programacion.movil;

import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;
/**
 * Clase que representa la Capa de Modelo de la arquitectura  MVP
 * @author Dennys Iza
 * @version 1.0
 */
public class Calculation {
    private final Symbols symbols;
    private CalculationResult calculationResult;
    private static String currentExpression;
    private int newOperation=0;
    private int newPointNumber=0;
    private int newCeroNumber=0;
    private Double memoria;

    interface CalculationResult {
        void onExpressionChanged(String result, boolean successful);
    }

    public void setCalculationResultListener(CalculationResult calculationResult) {
        this.calculationResult = calculationResult;
        currentExpression = "";
    }

    public Calculation() {
        symbols = new Symbols();
    }

    /**
     * Borra solamente un caracter de entrada de la expresión dada, u
     * "" - expresión inválida
     * 543 - expresión válida
     * 45*65 -expresión válida
     */
    public void deleteCharacter() {
        if (currentExpression.length() > 0) {
            currentExpression = currentExpression.substring(0, currentExpression.length() - 1);
            calculationResult.onExpressionChanged(currentExpression, true);
        } else {
            calculationResult.onExpressionChanged("Entrada Inválida", false);
        }
    }

    /**
     * Borra toda la expresión existente en el Display hasta que esta se encuentre vacía
     * "" - Expresión Inválida
     */
    public void deleteExpression() {
        if (currentExpression.equals("")) {
            calculationResult.onExpressionChanged("Entrada Inválida", false);
        }
        currentExpression = "";
        calculationResult.onExpressionChanged(currentExpression, true);
    }

    /**
     * Agrega el número a la expresión actual si es válida:
     * Combinación "0" & otro número que es 0 - resultado invalido
     * "12345678909876543" - Expresión Inválida
     *
     * @param number
     */
    public void appendNumber(String number) {
        if(newOperation==1){
            newOperation=0;
            currentExpression = "";
            calculationResult.onExpressionChanged(currentExpression, true);

        }

            if (currentExpression.startsWith("0") && number.equals("0")&& newCeroNumber==0&newPointNumber==0) {
                calculationResult.onExpressionChanged("Entrada Inválida", false);

            } else {
                if (currentExpression.length() <= 16) {
                    currentExpression += number;
                    calculationResult.onExpressionChanged(currentExpression, true);
                } else {
                    calculationResult.onExpressionChanged("Expression Muy Larga", false);

                }
            }


    }

    /**
     * Agrega un operador a la expresión actual, si es válida:
     * 56 - Expresión Válida
     * 56* - Expresión Inválida
     * 56*2 - Expresión Válida
     * "" - Expresión Inálida
     *
     * @param operator cualquiera de los siguientes:
     *                 - "*"
     *                 - "/"
     *                 - "-"
     *                 - "+"
     */
    public void appendOperator(String operator) {
        if (validateExpression(currentExpression)) {
            newPointNumber=1;
            newCeroNumber=1;
            currentExpression += operator;
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    /**
     * Evalua si la expresión ya contiene un decimal ya existente
     */
    public void appendDecimal() {
        newPointNumber=1;
        if (validateExpression(currentExpression)) {
            currentExpression += ".";
            calculationResult.onExpressionChanged(currentExpression, true);
        }
    }

    public void MemoryD(){
        memoria=Double.parseDouble(currentExpression);
        newOperation=1;
        newPointNumber=0;
        newCeroNumber=0;

    }


    /**
     * Si la Expresión actual pasa las comprobaciones, pasa a la comprobación de signos, \
     * Entonces devolverá un resultado.
     */
    public void performEvaluation() {
        if (validateExpression(currentExpression)) {
            try {
                Double result = symbols.eval(currentExpression);
                currentExpression = Double.toString(result);
                calculationResult.onExpressionChanged(currentExpression, true);
                newOperation=1;
                newPointNumber=0;
                newCeroNumber=0;

            } catch (SyntaxException e) {
                calculationResult.onExpressionChanged("Entrada Inválida", false);
                e.printStackTrace();
            }
        }
    }

    /**
     *Función auxiliar para validar expresiones contra las siguientes comprobaciones:
     * "" - Expresión Inválida;
     * 8765 -Expresión Válida
     *
     * @param expression
     * @return true
     * @return false
     */
    public boolean validateExpression(String expression) {
        if (expression.endsWith("*") ||
                expression.endsWith("/") ||
                expression.endsWith("-") ||
                expression.endsWith("+")
        ) {
            calculationResult.onExpressionChanged("Entrada Inválida", false);
            return false;
        } else if (expression.equals("")) {
            calculationResult.onExpressionChanged("Expresión Vacía", false);
            return false;
        } else if (expression.length() > 16) {
            calculationResult.onExpressionChanged("Expresión Muy Larga", false);
            return false;
        } else {
            return true;
        }
    }
}
