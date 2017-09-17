package net.davidcrotty.hercules


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_dashboard.*
import net.davidcrotty.hercules.adapter.PlanAdapter

class DashboardActivity : AppCompatActivity() {

    private var sectionsPagerAdapter: PlanAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(toolbar)
        supportActionBar?.title = resources.getString(R.string.app_name)
        sectionsPagerAdapter = PlanAdapter(supportFragmentManager)

        container?.adapter = sectionsPagerAdapter
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }
}
