package net.davidcrotty.hercules.view

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
interface Skippable {
    fun next(viewIndex: Int)
    fun previous(viewIndex: Int)
}