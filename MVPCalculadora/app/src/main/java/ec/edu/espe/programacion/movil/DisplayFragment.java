package ec.edu.espe.programacion.movil;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;


public class DisplayFragment extends Fragment implements CalculatorContract.PublishToView{

    private CalculatorContract.ForwardDisplayInteractionToPresenter forwardInteraction;

    public void setPresenter(CalculatorContract.ForwardDisplayInteractionToPresenter
                                     forwardInteraction){
        this.forwardInteraction=forwardInteraction;
    }

    @BindView(R.id.lbl_display)
    TextView display;

    /**
     * Método que valida la eliminación de un caracter
     *
     * @param v View
     * @return Elimina un caracter del display
     */
    @OnClick(R.id.imb_delete)
    public void onDeleteShortClick(View v){
        forwardInteraction.onDeleteShortClick();
    }
    /**
     * Método que valida la eliminación de todos los
     * caracteres del display
     *
     * @param v View
     * @return Elimina el contenido del display
     */
    @OnLongClick(R.id.imb_delete)
    public boolean onDeleteLongClick(View v){
        forwardInteraction.onDeleteLongClick();
        return true;
    }

    /**
     * Constructor por Defecto
     */
    public DisplayFragment() {
        // Required empty public constructor
    }


    /**
     * Método que genera un método de la clase DisplayFragment
     *
     *
     * @return nuevo Objeto del tipo DisplayFragment
     */
    public static DisplayFragment newInstace(){
            return new DisplayFragment();
    }

    /**
     * Método del tipo View que crea los contenedores en la pantalla
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return v
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_display, container, false);
        ButterKnife.bind(this,v);
        return v;
    }

    /**
     * Método que desplega en el display el resultado obtenido
     * @param result
     */
    @Override
    public void ShowResult(String result) {
        display.setText(result);
    }

    /**
     * Método que desplega en el display un mensaje de error
     * @param message
     */
    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
