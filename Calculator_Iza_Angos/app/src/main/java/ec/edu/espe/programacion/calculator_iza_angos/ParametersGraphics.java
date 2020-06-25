package ec.edu.espe.programacion.calculator_iza_angos;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class ParametersGraphics extends AppCompatActivity {
    private LineGraphSeries<DataPoint> series1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters_graphics);

        double x,y;
        x=0;
        GraphView graph=(GraphView) findViewById(R.id.graph2);
        series1= new LineGraphSeries<>();
        int NumDataPoints=250;
        for(int i=0;i<NumDataPoints;i++){
            x=x+0.1;
            y=Math.cos(x);
            series1.appendData(new DataPoint(x,y),true,10000);
        }
        // activate horizontal zooming and scrolling
        graph.getViewport().setScalable(true);

// activate horizontal scrolling
        graph.getViewport().setScrollable(true);

// activate horizontal and vertical zooming and scrolling
        graph.getViewport().setScalableY(true);

// activate vertical scrolling
        graph.getViewport().setScrollableY(true);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Eje X Función Coseno");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Eje Y Función Coseno");
        series1.setColor(Color.RED);
        graph.addSeries(series1);
    }
}
