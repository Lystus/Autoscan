package com.example.autoscan

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class week : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var pie: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_week, container, false)
        pie = view.findViewById<PieChart>(R.id.pie)
        pie.setUsePercentValues(true)
        pie.setDrawEntryLabels(false)
        pie.setDrawMarkers(false)
        pie.isRotationEnabled = false
        pie.getDescription().setEnabled(false)
        pie.setCenterText("6 h")
        pie.setCenterTextColor(Color.WHITE)
        pie.setCenterTextSize(15F)

        pie.setUsePercentValues(false)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(100f, ""))


        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#ED4A3E"))


        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)


        dataSet.colors = colors
        dataSet.setDrawValues(false)
        pie.data = data
        pie.isDrawHoleEnabled = false
        pie.legend.isEnabled = false

        pie.invalidate()

        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            week().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}