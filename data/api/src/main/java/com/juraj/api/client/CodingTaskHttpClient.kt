package com.juraj.api.client

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.juraj.common.utils.EncryptedPrefs
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.utils.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject
import kotlin.random.Random

@InstallIn(SingletonComponent::class)
@Module
class CodingTaskHttpClient @Inject constructor(@ApplicationContext val context: Context) {
    val client = HttpClient(OkHttp) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true //not specified all values
                isLenient = true //allow to accept different types
            })
        }

        defaultRequest {
            getDefaultHeaders().map {
                header(it.name, it.value)
            }
        }

        engine {
            config {
                addInterceptor(
                    ChuckerInterceptor.Builder(context)
                        .collector(ChuckerCollector(context))
                        .maxContentLength(250000L)
                        .redactHeaders(emptySet())
                        .alwaysReadResponseBody(false)
                        .build()
                )
            }
        }
    }

    fun buildUrl(endpoint: String): Url {
        return Url(BASE_URL + endpoint)
    }

    fun getAuthorizationHeader(): PreparedHeader? {
        val token = EncryptedPrefs.authorizationHeader
        return if (token.isNullOrEmpty()) null else PreparedHeader("Authorization", "$token")
    }

    companion object {
        const val BASE_URL = "https://api.monstarlab.com"
        data class PreparedHeader(val name: String, val value: String)

        fun getDefaultHeaders(): List<PreparedHeader> {
            val headers = mutableListOf<PreparedHeader>()
            //headers.add(PreparedHeader("Authorization", "Bearer asldjaoisd..."))
            return headers
        }
    }

    /**
     * T: class has to be @Serializable
     */
    suspend inline fun <reified T> get(endpoint: String): T {
        return client.get(buildUrl(endpoint)) {
            getAuthorizationHeader()?.let {
                header(it.name, it.value)
            }
        }.body()
    }

    /**
     * requestBode: class has to be @Serializable
     * T: class has to be @Serializable
     */
    suspend inline fun <reified T> post(endpoint: String, requestBody: Any = EmptyContent): T {
        return client.post(buildUrl(endpoint)) {
            contentType(ContentType.Application.Json)
            getAuthorizationHeader()?.let {
                header(it.name, it.value)
            }
            setBody(requestBody)
        }.body()
    }

    /**
     * requestBode: class has to be @Serializable
     */
    suspend inline fun <reified T> put(endpoint: String, requestBody: Any = EmptyContent): T {
        return client.put(buildUrl(endpoint)) {
            contentType(ContentType.Application.Json)
            getAuthorizationHeader()?.let {
                header(it.name, it.value)
            }
            setBody(requestBody)
        }.body()
    }

    suspend inline fun delete(endpoint: String, requestBody: Any = EmptyContent) {
        return client.delete(buildUrl(endpoint)) {
            contentType(ContentType.Application.Json)
            getAuthorizationHeader()?.let {
                header(it.name, it.value)
            }
            setBody(requestBody)
        }.body()
    }
}
