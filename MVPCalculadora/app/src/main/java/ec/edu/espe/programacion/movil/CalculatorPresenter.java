package ec.edu.espe.programacion.movil;
/**
 * Clase que representa la Capa de Presentador de la arquitectura  MVP
 * @author Dennys Iza
 * @version 1.0
 */

public class CalculatorPresenter implements CalculatorContract.ForwardInputInteractionToPresenter,
        CalculatorContract.ForwardDisplayInteractionToPresenter, Calculation.CalculationResult {

    private CalculatorContract.PublishToView publishResult;
    private Calculation calc;



    /**
     * Constructor de la Clase CalculatorPresenter
     * @param publishResult
     */

    public CalculatorPresenter(CalculatorContract.PublishToView publishResult){
        this.publishResult = publishResult;
        calc = new Calculation();
        calc.setCalculationResultListener(this);
    }


    @Override
    public void onDeleteShortClick() {
        calc.deleteCharacter();
    }

    @Override
    public void onDeleteLongClick() {
        calc.deleteExpression();
    }

    @Override
    public void onNumberClick(int number) {
        calc.appendNumber(Integer.toString(number));
    }

    @Override
    public void onDecimalClick() {
        calc.appendDecimal();
    }

    @Override
    public void onEvaluateClick() {
        calc.performEvaluation();
    }

    @Override
    public void onOperatorClick(String operator) {
        calc.appendOperator(operator);
    }

    @Override
    public void onMemory(Double data) {

    }

    @Override
    public void onExpressionChanged(String result, boolean successful) {
        if (successful) {
            publishResult.ShowResult(result);
        } else {
            publishResult.showToastMessage(result);
        }
    }
}
