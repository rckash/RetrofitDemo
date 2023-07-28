package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitdemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getAllComments()
    }
    private fun getAllComments() {
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getComments().enqueue(object: Callback<List<Comments>>{
            override fun onResponse(
                call: Call<List<Comments>>,
                response: Response<List<Comments>>
            ) {
                if (response.isSuccessful) {
                    for (data in response.body()!!) {
                        Log.d("MYDATA", data.name)
                    }
                }
            }

            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                Log.d("MYDATA", "ERROR")
            }

        })
    }
}