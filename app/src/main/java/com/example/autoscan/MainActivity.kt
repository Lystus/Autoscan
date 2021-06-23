package com.example.autoscan

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter

private const val NUM_PAGES = 3


class MainActivity : AppCompatActivity() {
    private lateinit var pieChart: PieChart
    private lateinit var collapseButton: ImageButton

    private lateinit var viewPager: ViewPager2
    private var collapseState = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        pieChart = findViewById<PieChart>(R.id.pieChart)
        collapseButton = findViewById<ImageButton>(R.id.dropdownButton)

        pieChart.setUsePercentValues(true)
        pieChart.setDrawEntryLabels(false)
        pieChart.setDrawMarkers(false)
        pieChart.isRotationEnabled = false
        pieChart.getDescription().setEnabled(false)

        pieChart.setUsePercentValues(true)
        val dataEntries = ArrayList<PieEntry>()
        dataEntries.add(PieEntry(60f))
        dataEntries.add(PieEntry(13f))
        dataEntries.add(PieEntry(27f))


        val colors: ArrayList<Int> = ArrayList()
        colors.add(Color.parseColor("#ED4A3E"))
        colors.add(Color.parseColor("#302E2E"))
        colors.add(Color.parseColor("#CED4D6"))


        val dataSet = PieDataSet(dataEntries, "")
        val data = PieData(dataSet)


        data.setValueFormatter(PercentFormatter())
        dataSet.sliceSpace = 3f
        dataSet.colors = colors
        dataSet.setDrawValues(false)
        pieChart.data = data
        pieChart.setCenterText("73%")
        pieChart.setCenterTextSize(25F)
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        pieChart.holeRadius = 80f
        pieChart.transparentCircleRadius = 61f
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.WHITE)


        val entries: ArrayList<LegendEntry> = ArrayList()
        entries.add(LegendEntry("11 Pos. erledigt", Legend.LegendForm.SQUARE, 5f, Float.NaN, null, Color.BLACK))
        entries.add(LegendEntry("9 mit Autoscan", Legend.LegendForm.SQUARE, 5f, Float.NaN, null, Color.RED))
        entries.add(LegendEntry("60% mit Autoscan", Legend.LegendForm.SQUARE, 5f, Float.NaN, null, Color.RED))

        val l = pieChart.legend

        l.resetCustom()
        l.setCustom(entries)

        l.isWordWrapEnabled = true
        l.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.formToTextSpace = 5f
        l.form = Legend.LegendForm.SQUARE
        l.textSize = 16f
        l.orientation = Legend.LegendOrientation.VERTICAL


        pieChart.invalidate()
        viewPager = findViewById(R.id.pager)

        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        viewPager.setCurrentItem(1)

        collapseButton.setOnClickListener {
            if (collapseState) collapse() else expand()
        }
        collapse()
    }

    override fun onBackPressed() {
        if (viewPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return if (position == 2) {
                week()
            } else if(position == 1){
               today()
            }else{
               total()
            }
        }
    }
    private fun expand()
    {
        AnimationUtils.expand(viewPager)
        collapseButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        collapseState = true
    }
    private fun collapse()
    {
        AnimationUtils.collapse(viewPager)
        collapseButton.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        collapseState = false
    }
}