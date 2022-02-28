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
        legend.xAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 35f,
                resources.displayMetrics)
        legend.yAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                resources.displayMetrics)
        val dataSet = DoraCurveDataSet(label = "K", mode = DoraCurveDataSet.Mode.CURVE)
        dataSet.lineColor = Color.parseColor("#FFFFFF")

        dataSet.addEntry(DoraCurveEntry(52.15f))
        dataSet.addEntry(DoraCurveEntry(57.81f))
        dataSet.addEntry(DoraCurveEntry(44.94f))
        dataSet.addEntry(DoraCurveEntry(77.79f))
        dataSet.addEntry(DoraCurveEntry(42.69f))
        legend.entries.add(dataSet.calcLegend())

        val dataSet2 = DoraCurveDataSet(label = "D", mode = DoraCurveDataSet.Mode.CURVE)
        dataSet2.lineColor = Color.parseColor("#FFFF00")
        dataSet2.addEntry(DoraCurveEntry( 60.59f))
        dataSet2.addEntry(DoraCurveEntry(50.57f))
        dataSet2.addEntry(DoraCurveEntry(43.46f))
        dataSet2.addEntry(DoraCurveEntry( 77.38f))
        dataSet2.addEntry(DoraCurveEntry( 49.6f))
        legend.entries.add(dataSet2.calcLegend())

        val dataSet3 = DoraCurveDataSet(label = "J", mode = DoraCurveDataSet.Mode.CURVE)
        dataSet3.lineColor = Color.parseColor("#F00FFF")
        dataSet3.addEntry(DoraCurveEntry( 35.26f))
        dataSet3.addEntry(DoraCurveEntry( 72.29f))
        dataSet3.addEntry(DoraCurveEntry( 47.89f))
        dataSet3.addEntry(DoraCurveEntry( 78.61f))
        dataSet3.addEntry(DoraCurveEntry(31.93f))
        legend.entries.add(dataSet3.calcLegend())
        kdjChart.setLegend(legend)
        val data = DoraCurveData(dataSet, dataSet2, dataSet3)
        kdjChart.xAxis.axisLineColor = Color.GRAY
        kdjChart.xAxis.axisLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1f, resources.displayMetrics)
        kdjChart.xAxisPlaceholder = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                30f, resources.displayMetrics)
        kdjChart.yAxis.axisLineColor = Color.GRAY
        kdjChart.yAxis.axisLineWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                1f, resources.displayMetrics)
        kdjChart.setData(data)

        val legend2 = DoraLegend()
        legend2.type = DoraLegend.LegendType.SQUARE
        legend2.xAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                35f, resources.displayMetrics)
        legend2.yAxisOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f,
                resources.displayMetrics)
        val dataSet4 = DoraCurveDataSet(label = "RSI6")
        dataSet4.lineColor = Color.parseColor("#FFFFFF")
        dataSet4.addEntry(DoraCurveEntry(17.58f))
        dataSet4.addEntry(DoraCurveEntry(49.69f))
        dataSet4.addEntry(DoraCurveEntry(59.76f))
        dataSet4.addEntry(DoraCurveEntry(41.99f))
        dataSet4.addEntry(DoraCurveEntry(42.68f))
        dataSet4.addEntry(DoraCurveEntry(26.04f))
        dataSet4.addEntry(DoraCurveEntry(39.02f))
        dataSet4.addEntry(DoraCurveEntry(80.93f))
        dataSet4.addEntry(DoraCurveEntry(39.59f))
        dataSet4.addEntry(DoraCurveEntry(67.93f))
        legend2.entries.add(dataSet4.calcLegend())

        val dataSet5 = DoraCurveDataSet(label = "RSI12")
        dataSet5.lineColor = Color.parseColor("#FFFF00")
        dataSet5.addEntry(DoraCurveEntry( 27.85f))
        dataSet5.addEntry(DoraCurveEntry(45.43f))
        dataSet5.addEntry(DoraCurveEntry(53.69f))
        dataSet5.addEntry(DoraCurveEntry( 46.77f))
        dataSet5.addEntry(DoraCurveEntry( 45.79f))
        dataSet5.addEntry(DoraCurveEntry( 36.71f))
        dataSet5.addEntry(DoraCurveEntry(39.58f))
        dataSet5.addEntry(DoraCurveEntry(70.94f))
        dataSet5.addEntry(DoraCurveEntry( 40.94f))
        dataSet5.addEntry(DoraCurveEntry( 59.61f))
        legend2.entries.add(dataSet5.calcLegend())

        val dataSet6 = DoraCurveDataSet(label = "RSI24")
        dataSet6.lineColor = Color.parseColor("#F00FFF")
        dataSet6.addEntry(DoraCurveEntry( 34.19f))
        dataSet6.addEntry(DoraCurveEntry( 42.65f))
        dataSet6.addEntry(DoraCurveEntry( 48.11f))
        dataSet6.addEntry(DoraCurveEntry( 45.51f))
        dataSet6.addEntry(DoraCurveEntry(45.45f))
        dataSet6.addEntry(DoraCurveEntry( 41.17f))
        dataSet6.addEntry(DoraCurveEntry( 41.31f))
        dataSet6.addEntry(DoraCurveEntry( 60.83f))
        dataSet6.addEntry(DoraCurveEntry( 44.87f))
        dataSet6.addEntry(DoraCurveEntry(54.25f))
        legend2.entries.add(dataSet6.calcLegend())
        rsiChart.setLegend(legend2)
        rsiChart.yAxis.drawGridLine = false
        rsiChart.xAxisPlaceholder = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                30f, resources.displayMetrics)
        val data2 = DoraCurveData(dataSet4, dataSet5, dataSet6)
        rsiChart.setData(data2)
    }
}