package ru.baumanclass.di

import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.baumanclass.data.datasource.remote.api.IGigaChatChatApi
import ru.baumanclass.data.datasource.remote.api.IGigaChatOAuthApi
import ru.baumanclass.utils.GIGA_CHAT_BASE_URL
import ru.baumanclass.utils.GIGA_OAUTH_BASE_URL
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/*
/ Это не костыль, а временная фича. Будет правиться в ближайшие сроки.
 */
fun provideOkHttp(): OkHttpClient {
    val trustAllCerts: Array<TrustManager> = arrayOf(
        object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate>? {
                return arrayOf()
            }

            override fun checkClientTrusted(certs: Array<java.security.cert.X509Certificate>, authType: String) {}

            override fun checkServerTrusted(certs: Array<java.security.cert.X509Certificate>, authType: String) {}
        }
    )

    val sslContext: SSLContext = SSLContext.getInstance("SSL")
    sslContext.init(null, trustAllCerts, java.security.SecureRandom())

    return OkHttpClient.Builder()
        .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier { _, _ -> true } // Bypass hostname verification
        .build()
}

fun provideOauthRetrofit(baseUrl: String, client: OkHttpClient): IGigaChatOAuthApi =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(IGigaChatOAuthApi::class.java)

fun provideChatRetrofit(baseUrl: String, client: OkHttpClient): IGigaChatChatApi =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(IGigaChatChatApi::class.java)


val apiModule = module {
    single { provideOkHttp() }
    single { provideOauthRetrofit(GIGA_OAUTH_BASE_URL, get()) }
    single { provideChatRetrofit(GIGA_CHAT_BASE_URL, get()) }
}
