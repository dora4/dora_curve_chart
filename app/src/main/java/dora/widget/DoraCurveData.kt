package dora.widget

import dora.widget.chart.DoraChartData

/**
 * 折线统计图数据。
 */
class DoraCurveData(vararg dataSets: DoraCurveDataSet) : DoraChartData<DoraCurveEntry, DoraCurveDataSet>(*dataSets) {

    // 求出最大值
    override fun calcMaxEntryValue() : Float {
        // 如果给定Float.MIN_VALUE，在数据为全负数时，会让极差巨大，导致绘图错误，所以先计算最小值
        var max = calcMinEntryValue()
        for (dataSet in dataSets) {
            max = max.coerceAtLeast(dataSet.entries.maxOf { it.value })
        }
        return max
    }

    // 求出最小值
    override fun calcMinEntryValue() : Float {
        var min = Float.MAX_VALUE
        for (dataSet in dataSets) {
            min = min.coerceAtMost(dataSet.entries.minOf { it.value })
        }
        return min
    }

    // 计算网格数量
    fun calcDatasetGridCount() : Int {
        var count = 0
        for (dataSet in dataSets) {
            count = count.coerceAtLeast(dataSet.entries.size - 1)
        }
        return count
    }
}