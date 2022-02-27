package dora.widget

import dora.widget.chart.DoraChartEntry

class DoraCurveEntry(value: Float = 0f, showValue: Boolean = false) : DoraChartEntry(value, showValue) {
    constructor(value: Float = 0f) : this(value, false)
}