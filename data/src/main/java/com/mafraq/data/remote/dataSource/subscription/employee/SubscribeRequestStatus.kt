package com.mafraq.data.remote.dataSource.subscription.employee


enum class SubscribeRequestStatus {
    Idle,
    Pending,
    Cancelled,
    Accepted;

    val isPending: Boolean
        get() = this == Pending

    val isCancelled: Boolean
        get() = this == Cancelled

    val isAccepted: Boolean
        get() = this == Accepted

    companion object {
        fun fromString(value: String): SubscribeRequestStatus = when (value) {
            Idle.name -> Idle
            Pending.name -> Pending
            Cancelled.name -> Cancelled
            Accepted.name -> Accepted
            else -> Idle
        }
    }
}
