package ec.edu.espe.programacion.movil;
/**
 * Interface donde se crean las distintas vistas
 * @author Dennys Iza
 * @version 1.0
 */
public interface CalculatorContract {

    /**
     *Las vistas manejan estos métodos para su comunicación con la capa Modelo
     */

     interface PublishToView{
         void ShowResult(String result);
         //Mensaje de error
         void showToastMessage(String message);
     }

    /**
     * Se pasa los eventos click desde nuestro punto de vista (DisplayFragment)
     * hacia el Presentador
     */


    interface ForwardDisplayInteractionToPresenter{
        void onDeleteShortClick();
        void onDeleteLongClick();

    }
    /**
     * Se pasa los eventos click desde nuestro punto de vista (InputFragment)
     * hacia el Presentador
     */

    interface ForwardInputInteractionToPresenter{
        void onNumberClick(int number);
        void onDecimalClick();
        void onEvaluateClick();
        void onOperatorClick(String operator);
        void onMemory(Double data);
     }

}
