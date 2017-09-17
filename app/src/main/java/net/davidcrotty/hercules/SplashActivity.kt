package net.davidcrotty.hercules

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.hercules.view.SetState

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, ExerciseActivity::class.java)
        val setList = generateSet()
        intent.putParcelableArrayListExtra(ExerciseActivity.SET_KEY, setList)
        startActivity(intent)
    }

    private fun generateSet() : ArrayList<Set> {
        val first = Set("Biceps curl", 10, 30, SetState.PENDING)
        val second = Set("Shoulder press", 10, 30, SetState.PENDING)
        val third = Set("Dead lifts", 10, 30, SetState.PENDING)
        val fourth = Set("Press ups", 10, 30, SetState.PENDING)

        return ArrayList<Set>().apply {
            add(first)
            add(second)
            add(third)
            add(fourth)
        }
    }
}