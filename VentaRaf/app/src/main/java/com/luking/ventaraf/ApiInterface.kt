package com.luking.ventaraf

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {
    // obtiene lista de anuncios desde mi api
    @GET("anuncios")
    fun getAnuncios(): Call<List<Anuncio>>

    // obtiene un anuncio completo
    @GET("anuncios/{id}")
    fun getAnuncio(@Path("id") id: Int): Call<Anuncio>

    // obtiene lista de anuncios filtrada por termino de busqueda
    @GET("anuncios")
    fun getResultados(@Query("titulo") palabrasClave: String): Call<List<Anuncio>>

    @POST("anuncios/")
    fun nuevoAnuncio(@Body anuncio: Anuncio): Call<Anuncio>

    companion object {
        // llamar a la api de mockapi
        //val BASE_URL = "https://608b3fb0737e470017b749bd.mockapi.io/"
        // endpoint (red)
        val BASE_URL = "http://192.168.1.111:8000/api/"
        // endpoint
        //val BASE_URL = "http://stablerpi.duckdns.org:8000/api/"

        fun create(): ApiInterface {
            // implementamos el cliente http
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}