package com.example.alliase

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [register.newInstance] factory method to
 * create an instance of this fragment.
 */
class register : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    data class Data(
        val name:String? = null,
        val father:String? = null,
        val mother:String? = null,
        val age:String? = null,
        val Bio:String? = null,
        val gender:String?= null
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = Firebase.firestore
        val n = view.findViewById<TextInputLayout>(R.id.name).editText?.text
        val f = view.findViewById<TextInputLayout>(R.id.dad).editText?.text
        val m = view.findViewById<TextInputLayout>(R.id.mom).editText?.text
        val a = view.findViewById<TextInputLayout>(R.id.age).editText?.text
        val b = view.findViewById<TextInputLayout>(R.id.bio).editText?.text
        val g = view.findViewById<TextInputLayout>(R.id.gender).editText?.text

        val next: Button = view.findViewById(R.id.next)

        next.setOnClickListener {
            if ("$n" != "") {
                val data = Data("${n}", "${f}", "${m}", "$a", "$b", "$g")
                Navigation.findNavController(view).navigate(R.id.action_register2_to_home1)
                lifecycleScope.launch(Dispatchers.IO) {
                    val docRef = db.collection("data").document("$n")
                    docRef.set(data)
                        .addOnSuccessListener { Log.d("mytag", "document successfully written") }


                }
            }
            else
            {
                Toast.makeText(requireContext(), "please fill the details", Toast.LENGTH_SHORT).show()
            }

        }

    }

    }


