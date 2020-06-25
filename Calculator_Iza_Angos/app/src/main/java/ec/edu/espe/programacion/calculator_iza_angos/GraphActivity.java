package ec.edu.espe.programacion.calculator_iza_angos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class GraphActivity extends AppCompatActivity {
    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        double x,y;
        x=0;
        GraphView graph=(GraphView) findViewById(R.id.graph);
        series1= new LineGraphSeries<>();
        int NumDataPoints=250;
        for(int i=0;i<NumDataPoints;i++){
            x=x+0.1;
            y=Math.sin(x);
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
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Eje X Función Seno");
        graph.getGridLabelRenderer().setVerticalAxisTitle("Eje Y Función Seno");
        graph.addSeries(series1);
    }
}
