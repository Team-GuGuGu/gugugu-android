package com.gugugu.remote.util

object GuguguUrl {
    private const val API = "/api"
    object Meal {
        private const val PATH = "${API}/meal"
        const val GET = "${PATH}/get"
        const val SCHOOL = "${PATH}/school"
    }
}