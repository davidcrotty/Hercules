package net.davidcrotty.hercules

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_exercise.*

/**
 * Created by David Crotty on 15/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class ExerciseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        val supportActionbar = supportActionBar
        supportActionbar?.setDisplayHomeAsUpEnabled(true)
        supportActionbar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionbar?.title = ""

        progress_countdown.max = 10
        progress_countdown.progress = 4

        inProgressRep.progress = 3
    }
}