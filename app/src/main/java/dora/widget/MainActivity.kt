package dora.widget

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import dora.widget.chart.component.DoraLegend

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val kdjChart = findViewById<DoraCurveChart>(R.id.kdjChart)
        val rsiChart = findViewById<DoraCurveChart>(R.id.rsiChart)
        val legend = DoraLegend()
        legend.type = DoraLegend.LegendType.CIRCLE
        legend.xAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25f,
                resources.displayMetrics)
        val dataSet = CurveDataSet(label = "K", mode = CurveDataSet.Mode.CURVE)
        dataSet.lineColor = Color.parseColor("#FFFFFF")
        dataSet.addEntry(CurveEntry(52.15f))
        dataSet.addEntry(CurveEntry(57.81f))
        dataSet.addEntry(CurveEntry(44.94f))
        dataSet.addEntry(CurveEntry(77.79f))
        dataSet.addEntry(CurveEntry(42.69f))
        legend.entries.add(dataSet.calcLegend())

        val dataSet2 = CurveDataSet(label = "D", mode = CurveDataSet.Mode.CURVE)
        dataSet2.lineColor = Color.parseColor("#FFFF00")
        dataSet2.addEntry(CurveEntry( 60.59f))
        dataSet2.addEntry(CurveEntry(50.57f))
        dataSet2.addEntry(CurveEntry(43.46f))
        dataSet2.addEntry(CurveEntry( 77.38f))
        dataSet2.addEntry(CurveEntry( 49.6f))
        legend.entries.add(dataSet2.calcLegend())

        val dataSet3 = CurveDataSet(label = "J", mode = CurveDataSet.Mode.CURVE)
        dataSet3.lineColor = Color.parseColor("#F00FFF")
        dataSet3.addEntry(CurveEntry( 35.26f))
        dataSet3.addEntry(CurveEntry( 72.29f))
        dataSet3.addEntry(CurveEntry( 47.89f))
        dataSet3.addEntry(CurveEntry( 78.61f))
        dataSet3.addEntry(CurveEntry(31.93f))
        legend.entries.add(dataSet3.calcLegend())
        kdjChart.setLegend(legend)
        val data = CurveData(dataSet, dataSet2, dataSet3)
        kdjChart.xAxis.axisLineColor = Color.GRAY
        kdjChart.xAxis.axisLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1f, resources.displayMetrics)
        kdjChart.yAxis.axisLineColor = Color.GRAY
        kdjChart.yAxis.axisLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1f, resources.displayMetrics)
        kdjChart.setData(data)

        val legend2 = DoraLegend()
        legend2.type = DoraLegend.LegendType.SQUARE
        legend2.xAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                25f, resources.displayMetrics)
        val dataSet4 = CurveDataSet(label = "R")
        dataSet4.lineColor = Color.parseColor("#FFFFFF")
        dataSet4.addEntry(CurveEntry(17.58f))
        dataSet4.addEntry(CurveEntry(49.69f))
        dataSet4.addEntry(CurveEntry(59.76f))
        dataSet4.addEntry(CurveEntry(41.99f))
        dataSet4.addEntry(CurveEntry(42.68f))
        dataSet4.addEntry(CurveEntry(26.04f))
        dataSet4.addEntry(CurveEntry(39.02f))
        dataSet4.addEntry(CurveEntry(80.93f))
        dataSet4.addEntry(CurveEntry(39.59f))
        dataSet4.addEntry(CurveEntry(67.93f))
        legend2.entries.add(dataSet4.calcLegend())

        val dataSet5 = CurveDataSet(label = "S")
        dataSet5.lineColor = Color.parseColor("#FFFF00")
        dataSet5.addEntry(CurveEntry( 27.85f))
        dataSet5.addEntry(CurveEntry(45.43f))
        dataSet5.addEntry(CurveEntry(53.69f))
        dataSet5.addEntry(CurveEntry( 46.77f))
        dataSet5.addEntry(CurveEntry( 45.79f))
        dataSet5.addEntry(CurveEntry( 36.71f))
        dataSet5.addEntry(CurveEntry(39.58f))
        dataSet5.addEntry(CurveEntry(70.94f))
        dataSet5.addEntry(CurveEntry( 40.94f))
        dataSet5.addEntry(CurveEntry( 59.61f))
        legend2.entries.add(dataSet5.calcLegend())

        val dataSet6 = CurveDataSet(label = "I")
        dataSet6.lineColor = Color.parseColor("#F00FFF")
        dataSet6.addEntry(CurveEntry( 34.19f))
        dataSet6.addEntry(CurveEntry( 42.65f))
        dataSet6.addEntry(CurveEntry( 48.11f))
        dataSet6.addEntry(CurveEntry( 45.51f))
        dataSet6.addEntry(CurveEntry(45.45f))
        dataSet6.addEntry(CurveEntry( 41.17f))
        dataSet6.addEntry(CurveEntry( 41.31f))
        dataSet6.addEntry(CurveEntry( 60.83f))
        dataSet6.addEntry(CurveEntry( 44.87f))
        dataSet6.addEntry(CurveEntry(54.25f))
        legend2.entries.add(dataSet6.calcLegend())
        rsiChart.setLegend(legend2)
        rsiChart.yAxis.drawGridLine = false
        val data2 = CurveData(dataSet4, dataSet5, dataSet6)
        rsiChart.setData(data2)
    }
}