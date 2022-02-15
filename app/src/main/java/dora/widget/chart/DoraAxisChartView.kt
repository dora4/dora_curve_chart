package dora.widget.chart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import dora.widget.chart.animation.DoraChartAnimator
import dora.widget.chart.component.DoraXAxis
import dora.widget.chart.component.DoraYAxis
import dora.widget.chart.renderer.DoraAxisRenderer

abstract class DoraAxisChartView<E : DoraChartEntry, S : DoraChartDataSet<E>,
        T : DoraChartData<E, S>>
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                          defStyleAttr: Int = 0) : DoraChartView<E, S, T>(context,
        attrs, defStyleAttr) {

    var xAxis: DoraXAxis<E, S> = DoraXAxis()
    var yAxis: DoraYAxis<E, S> = DoraYAxis()
    var xAxisPlaceholder =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
    20f, resources.displayMetrics)
    var yAxisPlaceholder =  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            20f, resources.displayMetrics)
    var maxVisibleCount: Int = 100
    lateinit var axisRenderer: DoraAxisRenderer<E, S>

    override fun init() {
        super.init()
        axisRenderer = object : DoraAxisRenderer<E, S>(DoraChartAnimator {}) {
            override fun drawXAxis(xAxis: DoraXAxis<E, S>, canvas: Canvas) {
                axisPaint.color = xAxis.axisLineColor
                axisPaint.strokeWidth = xAxis.axisLineWidth
                if (xAxis.isShow) {
                    canvas.drawLine(xAxisPlaceholder, height - yAxisPlaceholder, width - xAxisPlaceholder,
                            height - yAxisPlaceholder, axisPaint)
                }
            }

            override fun drawYAxis(yAxis: DoraYAxis<E, S>, canvas: Canvas) {
                axisPaint.color = yAxis.axisLineColor
                axisPaint.strokeWidth = yAxis.axisLineWidth
                if (yAxis.isShowLeft) {
                    canvas.drawLine(xAxisPlaceholder, 0f,
                            xAxisPlaceholder, height - yAxisPlaceholder, axisPaint)
                }
                if (yAxis.isShowRight) {
                    canvas.drawLine(width - xAxisPlaceholder, 0f,
                            width - xAxisPlaceholder, height - yAxisPlaceholder, axisPaint)
                }
            }

            override fun drawGridLine(xAxis: DoraXAxis<E, S>, canvas: Canvas) {
                for (dataSet in data.dataSets) {
                    var size = dataSet.entries.size
                    if (size == 0) {
                        return
                    }
                    if (size > maxVisibleCount) {
                        size = maxVisibleCount
                    }
                    val gridWidth = ((width - xAxisPlaceholder * 2) / (size - 1))
                    for ((index, _) in dataSet.entries.subList(0, size).withIndex()) {
                        var curX = xAxisPlaceholder + gridWidth * index
                        if (yAxis.drawGridLine && index != 0 && index != dataSet.entries.size - 1) {
                            drawGridLine(curX, canvas)
                        }
                    }
                }
            }

            override fun drawGridLine(yAxis: DoraYAxis<E, S>, canvas: Canvas) {
            }

            private fun drawGridLine(x: Float, canvas: Canvas) {
                val maxHeight = height - yAxisPlaceholder
                val lineLength = 20f
                val spaceLength = 20f
                val gridLinePaint = Paint(Paint.ANTI_ALIAS_FLAG)
                gridLinePaint.strokeWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    1f, resources.displayMetrics)
                gridLinePaint.color = Color.LTGRAY
                gridLinePaint.pathEffect = DashPathEffect(floatArrayOf(
                        lineLength, spaceLength
                ), 30f)
                canvas.drawLine(x, 0f, x, maxHeight, gridLinePaint)
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        axisRenderer.drawXAxis(xAxis, canvas)
        axisRenderer.drawGridLine(xAxis, canvas)
        axisRenderer.drawYAxis(yAxis, canvas)
        axisRenderer.drawGridLine(yAxis, canvas)
    }
}