package com.example.autoscan

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
private const val NUM_PAGES = 3


class MainActivity : AppCompatActivity() {
    private lateinit var pieChart: PieChart

    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pieChart = findViewById<PieChart>(R.id.pieChart)

        pieChart.setUsePercentValues(true)
        pieChart.setDrawEntryLabels(false)
        pieChart.setDrawMarkers(false)
        pieChart.isRotationEnabled = false
        pieChart.getDescription().setEnabled(false)

        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(60f, "9 mit Autoscan"))
        dataEntries.add(PieEntry(13f, "11 Pos. erledigt"))
        dataEntries.add(PieEntry(27f, "60% mit Autoscan"))


        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#ED4A3E"))
        colors.add(Color.parseColor("#302E2E"))
        colors.add(Color.parseColor("#CED4D6"))


        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)


        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        pieChart.data = data
        pieChart.setCenterText(" 73%")
        pieChart.setCenterTextSize(35F)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.holeRadius = 80f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)


        val l = pieChart.legend
        pieChart.legend.isWordWrapEnabled = true
        pieChart.legend.isEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.formSize = 5f
        l.formToTextSpace = 5f
        l.form = Legend.LegendForm.SQUARE
        l.textSize = 16f
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.isWordWrapEnabled = true

        pieChart.invalidate()
        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter



    }
    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment = today()
    }


}