package com.mdxco.c4bank.account.commons.utils

import java.util.concurrent.locks.ReentrantLock

object LockingHelpers {
    // need to lock due to concurrency in extreme cases (like multiple users trying to sign up)
    // preventing from duplicated account numbers
    private val lock = ReentrantLock()

    fun <T> withLock(block: () -> T): T {
        try {
            lock.lock()
            return block()
        } finally {
            lock.unlock()
        }
    }
}