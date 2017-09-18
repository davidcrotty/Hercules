package net.davidcrotty.hercules


import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_dashboard.*
import net.davidcrotty.hercules.adapter.PlanAdapter
import net.davidcrotty.hercules.domain.DataSource
import net.davidcrotty.hercules.presenter.DashboardPresenter
import net.davidcrotty.hercules.view.DashboardView

class DashboardActivity : AppCompatActivity(), ViewPager.OnPageChangeListener, DashboardView {

    private var sectionsPagerAdapter: PlanAdapter? = null
    private lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        presenter = DashboardPresenter(this, DataSource())
        sectionsPagerAdapter = PlanAdapter(supportFragmentManager)
        pager?.adapter = sectionsPagerAdapter
        pager.currentItem = 1
        presenter.showTitleFor(1)
        bindListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        presenter.showTitleFor(position)
    }

    override fun setTitle(title: String) {
        page_title.text = title
    }

    private fun bindListeners() {
        previous_day.setOnClickListener({
            pager.currentItem = pager.currentItem - 1
        })
        next_day.setOnClickListener({
            pager.currentItem = pager.currentItem + 1
        })
        pager.addOnPageChangeListener(this)
        add_exercise.setOnClickListener({

        })
    }
}
