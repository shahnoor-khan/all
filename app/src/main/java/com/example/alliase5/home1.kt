package com.example.alliase5

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class home1 : Fragment(),NavigationView.OnNavigationItemSelectedListener{

    lateinit var navcon:NavController
    data class a (val name:String?="",
                  val father:String?="",
                  val mother:String?="",
                  val age:String?="",
                  val gender:String?="",
                  val bio:String?="")

    class ViewHolder (view: View):RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.name2)
        val father: TextView = view.findViewById(R.id.father)
        val mother : TextView = view.findViewById(R.id.mother2)
        val age: TextView = view.findViewById(R.id.age1)
        val gen: TextView =view.findViewById(R.id.gender2)
        val bio: TextView = view.findViewById(R.id.bio1)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home1, container, false)
      }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val na:NavigationView = view.findViewById(R.id.navi)
        val menu: Menu =na.menu
        val navcon:NavController= Navigation.findNavController(view)
        val draw:DrawerLayout=view.findViewById(R.id.drawer)
        val tool =view.findViewById<Toolbar>(R.id.toolbar)
        tool.setNavigationIcon(R.drawable.ic_baseline_menu_24)
        tool.setTitle("Alliase")
        tool.inflateMenu(R.menu.tool)
        tool.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.not ->{
                    navcon.navigate(R.id.action_home1_to_notification)
                    Log.d("tag","sucess")
                    return@setOnMenuItemClickListener true
                }
                R.id.rec ->{
                    navcon.navigate(R.id.action_home1_to_recent)
                    return@setOnMenuItemClickListener true
                }

                else -> return@setOnMenuItemClickListener false
            }
        }
        menu.setGroupCheckable(R.id.grou,true,true)

        tool.setNavigationOnClickListener {
            na.setupWithNavController(navcon)
            draw.openDrawer(na)
            na.setNavigationItemSelectedListener {
                    when (it.itemId) {
                        R.id.profile -> {
                            navcon.navigate(R.id.action_home1_to_profile)
                            Log.d("tag","sucess")
                            return@setNavigationItemSelectedListener true
                        }

                        R.id.apperance -> {
                            navcon.navigate(R.id.action_home1_to_apperance2)
                            return@setNavigationItemSelectedListener true
                        }
                        else -> return@setNavigationItemSelectedListener true
                    }
                }
            }




        val db = Firebase.firestore
        val qu = db.collection("data")
        val g = FirestoreRecyclerOptions.Builder<a>().setQuery(qu,a::class.java).setLifecycleOwner(this).build()
        val adapt = object : FirestoreRecyclerAdapter<a,ViewHolder>(g){
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
                return ViewHolder(layout)
            }


            override fun onBindViewHolder(holder: ViewHolder, position: Int, model: a) {
                holder.name.text = model.name
                holder.mother.text = model.mother
                holder.father.text = model.father
                holder.age.text = model.age
                holder.bio.text = model.bio
                holder.gen.text= model.gender
            }

        }
        val recy = view.findViewById<RecyclerView>(R.id.rey)
        recy.adapter = adapt
        recy.layoutManager=LinearLayoutManager(context)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navcon = Navigation.findNavController(
            activity?.supportFragmentManager?.findFragmentById(R.id.home1)!!.requireView())
        Log.d("tag","sucess")
       when (item.itemId){
           R.id.profile -> {
               navcon.navigate(R.id.action_home1_to_profile)
               Log.d("tag","success")
               return true
           }

           R.id.apperance -> {
               navcon.navigate(R.id.action_home1_to_apperance2)
               return true
           }
           else -> return false

       }
    }


}