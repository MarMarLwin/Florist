package com.example.floristbypo.models

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("job")
    lateinit var job: String
    @SerializedName("id")
    var id: String? = null
    @SerializedName("createdAt")
    var createdAt: String? = null

    fun User(name: String, job: String) {
        this.name = name
        this.job = job
    }
}