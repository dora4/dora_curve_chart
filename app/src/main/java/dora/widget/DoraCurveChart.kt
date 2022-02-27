package dora.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import dora.widget.chart.*
import dora.widget.chart.animation.DoraChartAnimator
import dora.widget.chart.animation.DoraChartAnimator.Companion.ANIMATOR_TIME
import dora.widget.chart.renderer.DoraDataRenderer
import kotlin.math.abs

class DoraCurveChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0) : DoraAxisChartView<DoraCurveEntry,
        DoraCurveDataSet, DoraCurveData>(context, attrs, defStyleAttr) {

    override fun createDataRenderer(): DoraDataRenderer {

        var phaseY = 1f

        return object : DoraDataRenderer(DoraChartAnimator { animator ->
            animator.addUpdateListener {
                phaseY = it.animatedValue as Float
                postInvalidate()
            }
        }) {

            override fun init() {
                super.init()
                animator.animateY(ANIMATOR_TIME)
            }

            override fun initPaints() {
                super.initPaints()
                shapePaint.strokeCap = Paint.Cap.ROUND
                shapePaint.style = Paint.Style.STROKE
                shapePaint.strokeJoin = Paint.Join.ROUND
            }

            override fun drawShape(canvas: Canvas) {
                val maxHeight = height - yAxisPlaceholder * 2
                val halfMaxHeight = maxHeight / 2
                val maxValue = data.calcMaxEntryValue()
                val minValue = data.calcMinEntryValue()
                for (dataSet in data.dataSets) {
                    shapePaint.strokeWidth = dataSet.lineWidth
                    shapePaint.color = dataSet.lineColor
                    when (dataSet.mode) {
                        DoraCurveDataSet.Mode.CURVE -> {
                            when (calcXAxisType()) {
                                XAxisType.BOTTOM -> {
                                    // 每一个刻度能表示的值的大小
                                    val scale = maxValue / maxHeight
                                    val bezierPath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    var prevX = 0f
                                    var prevY = 0f
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {

                                        var curX = xAxisPlaceholder + gridWidth * index
                                        var curY = yAxisPlaceholder + maxHeight - (entry.value / scale) * phaseY

                                        if (index == 0) {
                                            bezierPath.moveTo(curX, curY)
                                        } else {
                                            // 计算曲线的控制点

                                            val ctrX: Float = (prevX
                                                    + (curX - prevX) / 2.0f)
                                            val ctrY1 = prevY * phaseY
                                            val ctrY2 = curY * phaseY
                                            bezierPath.cubicTo(ctrX.toFloat(), ctrY1.toFloat(), ctrX.toFloat(), ctrY2.toFloat(),
                                                    curX, curY)
                                        }
                                        prevX = curX
                                        prevY = curY
                                    }
                                    canvas.drawPath(bezierPath, shapePaint)
                                    val pathMeasure = PathMeasure(bezierPath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                                XAxisType.TOP -> {
                                    // 每一个刻度能表示的值的大小
                                    val scale = abs(minValue) / maxHeight
                                    val bezierPath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    var prevX = 0f
                                    var prevY = 0f
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {

                                        var curX = xAxisPlaceholder + gridWidth * index
                                        val curY = yAxisPlaceholder + (abs(entry.value) / scale) * phaseY
                                        if (index == 0) {
                                            bezierPath.moveTo(curX, curY)
                                        } else {
                                            // 计算曲线的控制点

                                            val ctrX: Float = (prevX
                                                    + (curX - prevX) / 2.0f)
                                            val ctrY1 = prevY * phaseY
                                            val ctrY2 = curY * phaseY
                                            bezierPath.cubicTo(ctrX.toFloat(), ctrY1.toFloat(), ctrX.toFloat(), ctrY2.toFloat(),
                                                    curX, curY)
                                        }
                                        prevX = curX
                                        prevY = curY
                                    }
                                    canvas.drawPath(bezierPath, shapePaint)
                                    val pathMeasure = PathMeasure(bezierPath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                                XAxisType.INSIDE -> {
                                    val valueScope = abs(maxValue).coerceAtLeast(abs(minValue))
                                    val halfMaxHeight = maxHeight / 2
                                    // 每一个刻度能表示的值的大小
                                    val scale = valueScope / halfMaxHeight
                                    val bezierPath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    var prevX = 0f
                                    var prevY = 0f
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {

                                        var curX = xAxisPlaceholder + gridWidth * index
                                        val curY = if (entry.value < 0) {
                                            yAxisPlaceholder + halfMaxHeight + (abs(entry.value) / scale) * phaseY
                                        } else {
                                            yAxisPlaceholder + halfMaxHeight - (entry.value / scale) * phaseY
                                        }

                                        if (index == 0) {
                                            bezierPath.moveTo(curX, curY)
                                        } else {
                                            // 计算曲线的控制点

                                            val ctrX: Float = (prevX
                                                    + (curX - prevX) / 2.0f)
                                            val ctrY1 = prevY * phaseY
                                            val ctrY2 = curY * phaseY
                                            bezierPath.cubicTo(ctrX.toFloat(), ctrY1.toFloat(), ctrX.toFloat(), ctrY2.toFloat(),
                                                    curX, curY)
                                        }
                                        prevX = curX
                                        prevY = curY
                                    }
                                    canvas.drawPath(bezierPath, shapePaint)
                                    val pathMeasure = PathMeasure(bezierPath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                            }
                        }
                        DoraCurveDataSet.Mode.LINEAR -> {
                            when (calcXAxisType()) {
                                XAxisType.BOTTOM -> {
                                    // 每一个刻度能表示的值的大小
                                    val scale = maxValue / maxHeight
                                    val linePath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {
                                        val curX = xAxisPlaceholder + gridWidth * index
                                        val curY = yAxisPlaceholder + maxHeight - (entry.value / scale) * phaseY
                                        if (index == 0) {
                                            linePath.moveTo(curX, curY)
                                        } else {
                                            linePath.lineTo(curX, curY)
                                        }
                                    }
                                    canvas.drawPath(linePath, shapePaint)
                                    val pathMeasure = PathMeasure(linePath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                                XAxisType.TOP -> {
                                    // 每一个刻度能表示的值的大小
                                    val scale = abs(minValue) / maxHeight
                                    val linePath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {
                                        val curX = xAxisPlaceholder + gridWidth * index
                                        val curY = yAxisPlaceholder + (abs(entry.value) / scale) * phaseY
                                        if (index == 0) {
                                            linePath.moveTo(curX, curY)
                                        } else {
                                            linePath.lineTo(curX, curY)
                                        }
                                    }
                                    canvas.drawPath(linePath, shapePaint)
                                    val pathMeasure = PathMeasure(linePath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                                XAxisType.INSIDE -> {
                                    val valueScope = abs(maxValue).coerceAtLeast(abs(minValue))
                                    val halfMaxHeight = maxHeight / 2
                                    // 每一个刻度能表示的值的大小
                                    val scale = valueScope / halfMaxHeight
                                    val linePath = Path()
                                    var size = getXAxisGridCount()
                                    if (size == 0) {
                                        return
                                    }
                                    if (size > maxVisibleCount) {
                                        size = maxVisibleCount
                                    }
                                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {
                                        val curX = xAxisPlaceholder + gridWidth * index
                                        val curY = if (entry.value < 0) {
                                            yAxisPlaceholder + halfMaxHeight + (abs(entry.value) / scale) * phaseY
                                        } else {
                                            yAxisPlaceholder + halfMaxHeight - (entry.value / scale) * phaseY
                                        }
                                        if (index == 0) {
                                            linePath.moveTo(curX, curY)
                                        } else {
                                            linePath.lineTo(curX, curY)
                                        }
                                    }
                                    canvas.drawPath(linePath, shapePaint)
                                    val pathMeasure = PathMeasure(linePath, false)
                                    val pathLength = pathMeasure.length
                                    Log.d("DoraCurveChart", "pathLength=${pathLength}")
                                }
                            }
                        }
                    }
                }
            }

            override fun drawLabel(canvas: Canvas) {
                val maxHeight = height - yAxisPlaceholder * 2
                val maxValue = data.calcMaxEntryValue()
                // 每一个刻度能表示的值的大小
                val scale = maxValue / maxHeight
                for (dataSet in data.dataSets) {
                    labelPaint.textSize = dataSet.valueTextSize
                    labelPaint.color = dataSet.valueTextColor
                    var size = getXAxisGridCount()
                    if (size == 0) {
                        return
                    }
                    if (size > maxVisibleCount) {
                        size = maxVisibleCount
                    }
                    val gridWidth = ((width - xAxisPlaceholder * 2) / size)
                    for ((index, entry) in dataSet.entries.subList(0, size + 1).withIndex()) {
                        if (entry.showValue) {
                            val curX = xAxisPlaceholder + gridWidth * index
                            val curY = yAxisPlaceholder + maxHeight - (entry.value / scale) * phaseY
                            canvas.drawText("${entry.value}", curX, curY, labelPaint)
                        }
                    }
                }
            }
        }
    }

    override fun getXAxisGridCount(): Int {
        return data.calcDatasetGridCount()
    }
}