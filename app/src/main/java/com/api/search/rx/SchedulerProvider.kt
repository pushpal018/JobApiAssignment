package com.api.search.rx
import io.reactivex.Scheduler


internal interface SchedulerProvider {

    fun io(): Scheduler

    fun ui(): Scheduler

    fun computation(): Scheduler

}
