package com.example.floristbypo.repo

import androidx.paging.PageKeyedDataSource
import com.example.floristbypo.models.Item
import com.example.floristbypo.models.StackApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ItemDataSource:PageKeyedDataSource<Int, Item>() {
    companion object{
        //the size of a page that we want
        val PAGE_SIZE = 50

        //we will start from the first page which is 1
        private val FIRST_PAGE = 1

        //we need to fetch from stackoverflow
        private val SITE_NAME = "stackoverflow"
        lateinit var apiInterface:APIInterface
    }


    //this will be called once to load the initial data
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        apiInterface = APIClient.client.create(APIInterface::class.java)
        val call2 = apiInterface.getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)

        call2.enqueue(object : Callback<StackApiResponse?> {
            override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(
                call: Call<StackApiResponse?>,
                response: Response<StackApiResponse?>
            ) {
                if (response.body() != null) {
                    callback.onResult(
                        response.body()!!.items as MutableList<Item>,
                        null,
                        FIRST_PAGE + 1
                    )
                }            }

        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        APIClient.getInstance()
                ?.getApi()
                ?.getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                ?.enqueue(object: Callback<StackApiResponse?> {
                    override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                        call.cancel()
                    }

                    override fun onResponse(
                        call: Call<StackApiResponse?>,
                        response: Response<StackApiResponse?>
                    ) {
                        if(response.body()!=null)
                        {
                            val key=params.key+1
                            callback.onResult(response.body()!!.items!!,key)
                        }
                    }
                });    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
 APIClient.getInstance()
                ?.getApi()?.getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                ?.enqueue(object:Callback<StackApiResponse?>{
                    override fun onFailure(call: Call<StackApiResponse?>, t: Throwable) {
                        call.cancel()
                    }

                    override fun onResponse(
                        call: Call<StackApiResponse?>,
                        response: Response<StackApiResponse?>
                    ) {
                        var adjacentKey=0
                        if(params.key > 1)
                            adjacentKey=params.key-1
                        else
                            adjacentKey=-1
                    }
                })
                }
}