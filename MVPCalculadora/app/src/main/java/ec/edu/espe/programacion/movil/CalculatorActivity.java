package ec.edu.espe.programacion.movil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
/**
 * Clase donde se realiza la comunicación de la arquitectura  MVP
 * @author Dennys Iza
 * @version 1.0
 */
public class CalculatorActivity extends AppCompatActivity {

    private FragmentManager manager;

    /**
     *Método donde se comunican las capas del modelo MVP
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        manager = getSupportFragmentManager();

        DisplayFragment displayFragment = DisplayFragment.newInstace();
        InputFragment inputFragment = InputFragment.newInstance();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.cont_fragment_display, displayFragment);
        transaction.add(R.id.cont_fragment_input, inputFragment);
        transaction.commit();

        CalculatorPresenter presenter = new CalculatorPresenter(displayFragment);
        displayFragment.setPresenter(presenter);
        inputFragment.setPresenter(presenter);
    }
}
