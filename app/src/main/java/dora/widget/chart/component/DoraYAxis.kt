package dora.widget.chart.component

import dora.widget.chart.DoraChartDataSet
import dora.widget.chart.DoraChartEntry

data class DoraYAxis<E : DoraChartEntry, S : DoraChartDataSet<out E>>(val isShowLeft: Boolean = true,
                                                                      val isShowRight: Boolean = true) : DoraAxis<E, S>()