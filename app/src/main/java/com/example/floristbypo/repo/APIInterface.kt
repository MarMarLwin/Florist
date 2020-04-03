package com.example.floristbypo.repo

import com.example.floristbypo.models.MultipleResource
import com.example.floristbypo.models.StackApiResponse
import com.example.floristbypo.models.User
import com.example.floristbypo.models.UserList
import retrofit2.Call
import retrofit2.http.*

interface APIInterface {
    @GET("/api/unknown")
    abstract fun doGetListResources(): Call<MultipleResource>

    @POST("/api/users")
    abstract fun createUser(@Body user: User): Call<User>

    @GET("/api/users?")
    abstract fun doGetUserList(@Query("page") page: String): Call<UserList>

    @FormUrlEncoded
    @POST("/api/users?")
    abstract fun doCreateUserWithField(@Field("name") name: String, @Field("job") job: String): Call<UserList>

    @GET("/answers")
    abstract fun getAnswers(@Query("page") page:Int,@Query("pagesize") pagesize:Int, @Query("site") site:String):Call<StackApiResponse?>



}