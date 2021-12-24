package com.example.afinal.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.Model.Score
import com.example.afinal.databinding.FragmentGraphBinding
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.firestore.FirebaseFirestore


class GraphViewModel:ViewModel() {
    private lateinit var binding:FragmentGraphBinding
    private lateinit var fragment:Fragment
    private var scoreList = ArrayList<Score>()
    private lateinit var woman_Departament:MutableMap<String, Int>

    private lateinit var db: FirebaseFirestore

    fun inicializeBinding(binding: FragmentGraphBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment
        loadData()
        createData()
    }

    private fun createData(){
        scoreList = getScoreList()
        initBarChart()

        val entries: ArrayList<BarEntry> = ArrayList()

        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(BarEntry(i.toFloat(), score.score.toFloat()))
        }

        val barDataSet = BarDataSet(entries, "")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)

        val data = BarData(barDataSet)
        binding.barChart.data = data

        binding.barChart.invalidate()
    }

    private fun getScoreList(): ArrayList<Score> {
        for (key in woman_Departament.keys){
            scoreList.add(Score(key, woman_Departament.get(key)!!.toInt()))
        }

        return scoreList
    }

    private fun initBarChart() {

        binding.barChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = binding.barChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        binding.barChart.axisRight.isEnabled = false
        binding.barChart.legend.isEnabled = false

        binding.barChart.description.isEnabled = false
        binding.barChart.animateY(4000)

        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

    }
    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }


    fun loadData(){
        woman_Departament = mutableMapOf()
        woman_Departament["AMAZONAS"] = 2
        woman_Departament["ANCASH"] = 8
        woman_Departament["APURIMAC"] = 5
        woman_Departament["AREQUIPA"] = 12
        woman_Departament["AYACUCHO"] = 4
        woman_Departament["CAJAMARCA"] = 4
        woman_Departament["CALLAO"] = 4
        woman_Departament["CUSCO"] = 8
        woman_Departament["HUANCAVELICA"] = 3
        woman_Departament["HUÁNUCO"] = 2
        woman_Departament["ICA"] = 10
        woman_Departament["JUNIN"] = 6
        woman_Departament["LA LIBERTAD"] = 8
        woman_Departament["LAMBAYEQUE"] = 9
        woman_Departament["LIMA METROPOLITANA"] = 41
        woman_Departament["LIMA PROVINCIA"] = 11
        woman_Departament["LORETO"] = 3
        woman_Departament["MADRE DE DIOS"] = 1
        woman_Departament["MOQUEGUA"] = 3
        woman_Departament["PASCO"] = 3
        woman_Departament["PIURA"] = 10
        woman_Departament["PUNO"] = 7
        woman_Departament["SAN MARTIN"] = 8
        woman_Departament["TACNA"] = 6
        woman_Departament["TUMBES"] = 4
        woman_Departament["UCAYALI"] = 5

    }


}