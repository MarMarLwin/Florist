package com.example.floristbypo.repo

import androidx.lifecycle.MutableLiveData
import com.example.floristbypo.models.FloristUser
import com.example.floristbypo.models.User
import com.google.firebase.firestore.FirebaseFirestore

class UserRepository {
//    suspend fun getUser(): FloristUser

    fun getUser(): MutableLiveData<FloristUser?>{
        val database = FirebaseFirestore.getInstance()
        var userProfile: MutableLiveData<FloristUser?> = MutableLiveData()
        database.collection("User")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val user=document.toObject(FloristUser::class.java)
                    userProfile.postValue(user)
                }
            }
            .addOnFailureListener { exception ->
                //                Log.w(TAG, "Error getting documents.", exception)

            }

        return  userProfile
    }

}