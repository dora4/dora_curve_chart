package dora.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import dora.widget.chart.*
import dora.widget.chart.animation.DoraChartAnimator
import dora.widget.chart.animation.DoraChartAnimator.Companion.ANIMATOR_TIME
import dora.widget.chart.renderer.DoraDataRenderer

class DoraCurveChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0) : DoraAxisChartView<CurveEntry,
        CurveDataSet, CurveData>(context, attrs, defStyleAttr) {

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
                var maxHeight = height - yAxisPlaceholder
                val maxValue = data.calcMaxEntryValue()
                // 每一个刻度能表示的值的大小
                val scale = maxValue / maxHeight
                for (dataSet in data.dataSets) {
                    shapePaint.strokeWidth = dataSet.lineWidth
                    shapePaint.color = dataSet.lineColor
                    when (dataSet.mode) {
                        CurveDataSet.Mode.CURVE -> {
                            val bezierPath = Path()
                            var size = dataSet.entries.size
                            if (size == 0) {
                                return
                            }
                            if (size > maxVisibleCount) {
                                size = maxVisibleCount
                            }
                            val gridWidth = ((width - xAxisPlaceholder * 2) / (size - 1))
                            var prevX = 0f
                            var prevY = 0f
                            for ((index, entry) in dataSet.entries.subList(0, size).withIndex()) {

                                var curX = xAxisPlaceholder + gridWidth * index
                                var curY = (maxHeight - entry.value / scale) * phaseY

                                if (index == 0) {
                                    bezierPath.moveTo(curX, curY)
                                } else {
                                    // 计算曲线的控制点

                                    val ctrX: Float = (prevX
                                            + (curX - prevX) / 2.0f)
                                    val ctrY1 = prevY * phaseY
                                    val ctrY2 = curY * phaseY
                                    bezierPath.cubicTo(ctrX.toFloat(), ctrY1.toFloat(), ctrX.toFloat(), ctrY2.toFloat(),
                                            xAxisPlaceholder + gridWidth * index, (maxHeight - entry.value / scale) * phaseY)
                                }
                                prevX = curX
                                prevY = curY
                            }
                            canvas.drawPath(bezierPath, shapePaint)
                        }
                        CurveDataSet.Mode.LINEAR -> {
                            val linePath = Path()
                            var size = dataSet.entries.size
                            if (size == 0) {
                                return
                            }
                            if (size > maxVisibleCount) {
                                size = maxVisibleCount
                            }
                            val gridWidth = ((width - xAxisPlaceholder * 2) / (size - 1))
                            for ((index, entry) in dataSet.entries.subList(0, size).withIndex()) {
                                val curX = xAxisPlaceholder + gridWidth * index
                                val curY = (maxHeight - entry.value / scale) * phaseY
                                if (index == 0) {
                                    linePath.moveTo(curX, curY)
                                } else {
                                    linePath.lineTo(curX, curY)
                                }
                            }
                            canvas.drawPath(linePath, shapePaint)
                        }
                    }
                }
            }

            override fun drawLabel(canvas: Canvas) {
                val maxHeight = height - yAxisPlaceholder
                val maxValue = data.calcMaxEntryValue()
                // 每一个刻度能表示的值的大小
                val scale = maxValue / maxHeight
                for (dataSet in data.dataSets) {
                    labelPaint.textSize = dataSet.valueTextSize
                    labelPaint.color = dataSet.valueTextColor
                    var size = dataSet.entries.size
                    if (size == 0) {
                        return
                    }
                    if (size > maxVisibleCount) {
                        size = maxVisibleCount
                    }
                    val gridWidth = ((width - xAxisPlaceholder * 2) / (size - 1))
                    for ((index, entry) in dataSet.entries.subList(0, size).withIndex()) {
                        if (entry.showValue) {
                            val curX = xAxisPlaceholder + gridWidth * index
                            val curY = (maxHeight - entry.value / scale) * phaseY
                            canvas.drawText("${entry.value}", curX, curY, labelPaint)
                        }
                    }
                }
            }
        }
    }
}