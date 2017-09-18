package net.davidcrotty.hercules.adapter

import android.view.View

/**
 * Created by David Crotty on 18/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
interface RecyclerListener {
    fun onClick(view: View, position: Int)
}