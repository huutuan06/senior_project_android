package com.android.project.service.connect

import android.annotation.SuppressLint
import okhttp3.OkHttpClient
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateException
import javax.inject.Inject
import javax.net.ssl.*

class TrustHtppS @Inject
constructor(private val mClient: OkHttpClient.Builder) {

    fun intializeCertificate() {
        // Handle Handshake failed retrofit
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {
            }

            @SuppressLint("TrustAllX509TrustManager")
            @Throws(CertificateException::class)
            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>,
                authType: String
            ) {

            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })
        try {
            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            keyStore.load(null, null)

            val sslContext = SSLContext.getInstance("TLS")

            val trustManagerFactory =
                TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
            trustManagerFactory.init(keyStore)
            val keyManagerFactory =
                KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
            keyManagerFactory.init(keyStore, "keystore_pass".toCharArray())
            sslContext.init(null, trustAllCerts, SecureRandom())

            mClient.sslSocketFactory(sslContext.socketFactory)
                .hostnameVerifier { hostname, session -> true }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

}
