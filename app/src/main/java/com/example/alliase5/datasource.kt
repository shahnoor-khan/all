package com.example.alliase5

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class datasource {
    var b = ""
    var c = ""
    var a = ""
    var e = ""
    var f = ""
    var g = ""

     fun load():ArrayList<data>{
         val datalist = arrayListOf<data>()
         val d = Firebase.firestore.collection("data")
         d.get()
             .addOnSuccessListener { result ->
                 for (doc in result)
                     if (doc != null) {
                         b = (doc.data.get("name") as String)
                         c = (doc.data.get("father") as String)
                         a = (doc.data.get("mother") as String)
                         e = doc.data.get("gender") as String
                         f = (doc.data.get("age") as String)
                         g = doc.data.get("bio") as String
                         Log.d("tag","sucessfull")

                         val k = (data(b, c, a, f, e, g))
                         datalist.add(k)
                    }
                    Log.d("tag","${result.documents}")
             }
             .addOnFailureListener { ex ->
                 Log.w("tag","error",ex)
             }
         return datalist
     }
}