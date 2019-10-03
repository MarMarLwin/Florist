package com.example.floristbypo.repo

import androidx.lifecycle.LiveData
import com.example.floristbypo.models.User

interface UserRepository {
    suspend fun getUser(): User
}