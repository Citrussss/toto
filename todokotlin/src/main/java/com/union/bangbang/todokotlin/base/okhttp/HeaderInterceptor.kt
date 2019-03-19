package com.union.bangbang.todokotlin.base.okhttp

import com.union.bangbang.todokotlin.base.utils.Configuration.getToken
import com.union.bangbang.zero.AppUtil
import okhttp3.Interceptor
import okhttp3.Response

/**
不乱于心，不困于情。不畏将来，不念过往。如此，安好!

深谋若谷，深交若水。深明大义，深悉小节。已然，静舒!

善宽以怀，善感以恩。善博以浪，善精以业。这般，最佳!

勿感于时，勿伤于怀。勿耽美色，勿沉虚妄。从今，进取!

无愧于天，无愧于地。无怍于人，无惧于鬼。这样，人生!
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        getToken(AppUtil.peekActivity())?.let {
            val requestBuilder = original.newBuilder()
                    .header("Authorization", it)
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
        return chain.proceed(chain.request())
    }
}