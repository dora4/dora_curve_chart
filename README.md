# DoraCurveChart

描述：图表引擎之折线统计图

复杂度：★★★★☆

分组：【图表引擎】

关系：[dora_bar_chart](https://github.com/dora4/dora_bar_chart)

技术要点：基本绘图、Path的使用、贝瑟尔曲线、属性动画

### 照片

![avatar](https://github.com/dora4/dora_curve_chart/blob/main/art/dora_curve_chart.jpg)

### 动图

![avatar](https://github.com/dora4/dora_curve_chart/blob/main/art/dora_curve_chart.gif)

### 软件包

https://github.com/dora4/dora_curve_chart/blob/main/art/dora_curve_chart.apk

### 用法

| 类            | API           | 描述                                       |
| ------------- | ------------- | ------------------------------------------ |
| CurveEntry    | value         | 一个点的值                                 |
| CurveEntry    | showValue     | 是否显示点的值                             |
| CurveDataSet  | lineWidth     | 一个图例注记代表的数据集的折线的宽度       |
| CurveDataSet  | lineColor     | 一个图例注记代表的数据集的折线的颜色       |
| CurveDataSet  | label         | 注记的文本                                 |
| CurveDataSet  | mode          | 线的模式，LINEAR为折线，CURVE为曲线        |
| DoraChartView | setData()     | 设置图表的数据                             |
| DoraChartView | setLegend()   | 设置图表的图例和注记                       |
| DoraAxis      | axisLineColor | 坐标轴线的颜色                             |
| DoraAxis      | axisLineWidth | 坐标轴线的宽度                             |
| DoraAxis      | drawGridLine  | 是否绘制网格线                             |
| DoraLegend    | iconLabelGap  | 图例和注记的间距                           |
| DoraLegend    | legendItemGap | 图例之间的间距                             |
| DoraLegend    | iconSize      | 图例的大小，所有图例大小一致               |
| DoraLegend    | entries       | 所有的图例和注记的数据                     |
| DoraLegend    | type          | 图例的类型，SQUARE代表方形，CIRCLE代表圆形 |
| LegendEntry   | label         | 注记的文本，等同于CurveDataSet的label      |
| LegendEntry   | labelColor    | 注记的颜色                                 |
| LegendEntry   | iconColor     | 图例的颜色                                 |
