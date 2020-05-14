package ipca.example.myclinic

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.nio.charset.Charset

//
// Created by lourencogomes on 07/05/2020.
//
class VolleyHelper {

    private var queue : RequestQueue? = null


    fun userLogin (context: Context, userName : String, password: String) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("utilizador", userName)
            jsonObject.put("password", password)

            val stringRequest = object : StringRequest(
                Request.Method.GET,
                BASE_API + USER_LOGIN,
                Response.Listener<String> {
                    Log.d("VolleyHelper", it)


                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                }
            ){
                override fun getBody(): ByteArray {
                    return jsonObject.toString().toByteArray(Charset.forName("UTF-8")) ?:  ByteArray(0)
                }

                override fun getBodyContentType(): String {
                    return "application/json"
                }
            }
            queue!!.add(stringRequest)
        }
    }

    companion object {

        const val  BASE_API = "http://89.153.72.138:3000"
        const val  USER_LOGIN = "/user/login"

        private var mInstance : VolleyHelper? = VolleyHelper()

        val instance : VolleyHelper
            @Synchronized get(){
                if(null == mInstance){
                    mInstance = VolleyHelper()
                }
                return mInstance!!
            }
    }
}