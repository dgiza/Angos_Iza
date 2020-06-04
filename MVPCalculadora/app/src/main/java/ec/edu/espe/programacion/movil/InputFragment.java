package ec.edu.espe.programacion.movil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Clase que representa la Capa de Vista de la arquitectura  MVP
 * @author Dennys Iza
 * @version 1.0
 */
public class InputFragment extends Fragment {

    private CalculatorContract.ForwardInputInteractionToPresenter forwardInteraction;

    /**
     * Constructor
     * @param forwardInteraction
     */
    public void setPresenter(CalculatorContract.ForwardInputInteractionToPresenter
                                     forwardInteraction){
        this.forwardInteraction=forwardInteraction;
    }
    /**
     * Método donde se definen las acciones para los objetos
     * tipo botón de la categoría Números, al dar click
     * @param v
     */
    @OnClick({R.id.btn_Uno,R.id.btn_Dos,R.id.btn_Tres,R.id.btn_Cuatro,R.id.btn_Cinco,R.id.btn_Seis
            ,R.id.btn_Siete,R.id.btn_Ocho,R.id.btn_Nueve,R.id.btn_Cero})
    public void onNumberClick(Button v){
        forwardInteraction.onNumberClick(
                Integer.parseInt(v.getText().toString())
        );
    }
    /**
     * Método donde se definen las acciones para los objetos
     * tipo botón de la categoría Operaciones, al dar click
     * @param v
     */
    @OnClick({R.id.btn_Sumar,R.id.btn_Restar,R.id.btn_Multiplicar,R.id.btn_Dividir})
    public void onOperatorClick(Button v){
        forwardInteraction.onOperatorClick(v.getText().toString());
    }
    /**
     * Método donde se definen las acciones para los objetos
     * tipo botón de la categoría Decimal, al dar click
     * @param v
     */
    @OnClick({R.id.btn_Decimal})
    public void onDecimalClick(Button v){
        forwardInteraction.onDecimalClick();
    }


    /**
     * Método donde se definen las acciones para los objetos
     * tipo botón de la categoría Resultado, al dar click
     * @param v
     */
    @OnClick({R.id.btn_Igual})
    public void onEvaluateClick(Button v){
        forwardInteraction.onEvaluateClick();

    }

    /**
     * Constructor por defecto
     */
    public InputFragment() {

    }

    public static InputFragment newInstance(){
        return new InputFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View v=inflater.inflate(R.layout.fragment_input, container, false);
        ButterKnife.bind(this, v);

        return v;
    }
}
