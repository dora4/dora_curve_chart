package dora.widget

import dora.widget.chart.DoraChartData

/**
 * 折线统计图数据。
 */
class CurveData(vararg dataSets: CurveDataSet) : DoraChartData<CurveEntry, CurveDataSet>(*dataSets) {

    // 数据是否都是非负数
//    var isUnsignedData: Boolean = true

    // 求出最大值
    override fun calcMaxEntryValue() : Float {
        var max = 0f
        for (dataSet in dataSets) {
            max = max.coerceAtLeast(dataSet.entries.maxOf { it.value })
        }
        return max
    }

    // 求出最小值
    override fun calcMinEntryValue() : Float {
        var min = 0f
        for (dataSet in dataSets) {
            min = min.coerceAtMost(dataSet.entries.minOf { it.value })
        }
        return min
    }
}