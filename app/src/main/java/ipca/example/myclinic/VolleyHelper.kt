package ipca.example.myclinic

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
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





    fun userLogin (context: Context, userName : String, password: String, loginEvent : ((Boolean)->Unit) ) {
        doAsync {
            queue = Volley.newRequestQueue(context)

            val jsonObject = JSONObject()
            jsonObject.put("utilizador", userName)
            jsonObject.put("password", password)

            val jsonObjectRequest = object : JsonObjectRequest(
                Request.Method.POST,
                BASE_API + USER_LOGIN,
                jsonObject,
                Response.Listener{
                    // login bem sucedido
                    Log.d("VolleyHelper", it.toString())
                    if (it.getBoolean("auth")){
                        VolleyHelper.token = it.getString("token")
                        loginEvent.invoke(true)
                    }else {
                        // login mal sucedido
                        loginEvent.invoke(false)
                    }

                },
                Response.ErrorListener {
                    Log.d("VolleyHelper", it.toString())
                    // login mal sucedido
                    loginEvent.invoke(false)
                }
            ){

            }
            queue!!.add(jsonObjectRequest)
        }
    }

    companion object {

        const val  BASE_API = "http://89.153.72.138:3000"
        const val  USER_LOGIN = "/user/login"

        var token = ""

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