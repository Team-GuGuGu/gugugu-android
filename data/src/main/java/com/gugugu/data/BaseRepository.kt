package com.gugugu.data

interface BaseRepository<REMOTE, CACHE> {
    val remote: REMOTE
    val cache: CACHE
}