package com.example.floristbypo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.User
import com.example.floristbypo.repo.UserRepository

//class UserProfileViewModel(private val repo:UserRepository):ViewModel() {
class UserProfileViewModel:ViewModel() {
    private val _user = MutableLiveData<User>().apply { value = User("Po Po","Female","04/11/1993") }
    val user: LiveData<User> = _user //repo.getUser()
}