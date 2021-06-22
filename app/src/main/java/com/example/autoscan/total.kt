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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [total.newInstance] factory method to
 * create an instance of this fragment.
 */
class total : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_total, container, false)
        pie = view.findViewById<PieChart>(R.id.pie)
        pie.setUsePercentValues(true)
        pie.setDrawEntryLabels(false)
        pie.setDrawMarkers(false)
        pie.isRotationEnabled = false
        pie.getDescription().setEnabled(false)
        pie.setCenterText("45 h")
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


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment total.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            total().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}