package com.ashutosh.androidcleanarchitecture.shared.data.model

data class APIResponse<T>(
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int,
    val data:T
)