package com.example.floristbypo.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.floristbypo.models.FloristUser
import com.example.floristbypo.models.User
import com.example.floristbypo.repo.UserRepository


//class UserProfileViewModel(private val repo:UserRepository):ViewModel() {
class UserProfileViewModel:ViewModel() {

    private val repository:UserRepository = UserRepository()
    private val _user = MutableLiveData<FloristUser>().apply { value = FloristUser("Po Po","Female","04/11/1993") }
    val user: MutableLiveData<FloristUser?> = repository.getUser()
}