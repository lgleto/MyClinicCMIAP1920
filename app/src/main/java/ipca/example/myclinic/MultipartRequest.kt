package ipca.example.myclinic

import com.android.volley.*

import com.android.volley.toolbox.HttpHeaderParser

//
// Created by lourencogomes on 03/06/2020.
//

class MultipartRequest(url: String, private val mHeaders: Map<String, String>?, private val mMimeType: String, private val mMultipartBody: ByteArray, private val mListener: Response.Listener<NetworkResponse>, private val mErrorListener: Response.ErrorListener) : Request<NetworkResponse>(Request.Method.POST, url, mErrorListener) {

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        return mHeaders ?: super.getHeaders()
    }

    override fun getBodyContentType(): String {
        return mMimeType
    }

    @Throws(AuthFailureError::class)
    override fun getBody(): ByteArray {
        return mMultipartBody
    }

    override fun parseNetworkResponse(response: NetworkResponse): Response<NetworkResponse> {
        try {
            return Response.success(
                    response,
                    HttpHeaderParser.parseCacheHeaders(response))
        } catch (e: Exception) {
            return Response.error(ParseError(e))
        }

    }

    override fun deliverResponse(response: NetworkResponse) {
        mListener.onResponse(response)
    }

    override fun deliverError(error: VolleyError) {
        mErrorListener.onErrorResponse(error)
    }


}