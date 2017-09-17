package net.davidcrotty.progressview

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
enum class SetState(val value: Int) {
    DONE(0),
    IN_PROGRESS(1),
    PENDING(2);

    companion object {
        fun from(findValue: Int): SetState = SetState.values().first { it.value == findValue }
    }
}