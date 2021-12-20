package com.example.afinal.ViewModel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.Model.Suggest
import com.example.afinal.databinding.FragmentSuggestBinding
import com.google.firebase.firestore.*

class SugestViewModel: ViewModel() {
    lateinit var binding: FragmentSuggestBinding
    lateinit var fragment: Fragment
    private lateinit var suggestList:ArrayList<Suggest>
    private lateinit var myAdapter: SuggestAdapter
    private lateinit var db:FirebaseFirestore

    fun initializeBinding(binding: FragmentSuggestBinding, fragment: Fragment){
        this.binding = binding
        this.fragment = fragment

        loadData()
        loadRecyclerView()
    }
    private fun loadData() {
        suggestList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("sugerencias").
                get().addOnSuccessListener {
                for (document in it){
                    val name:String = document["title"] as String
                    val image:String = document["image"] as String
                    val category:String = document["category"] as String
                    val description:String = document["description"] as String
                    val expand:Boolean = document["expand"] as Boolean
                    suggestList.add(Suggest(name,image,"Categoria: $category",description,expand))
                }
            myAdapter.notifyDataSetChanged()
        }
    }
    private fun loadRecyclerView(){
        binding.rvSuggest.layoutManager = LinearLayoutManager(fragment.activity)
        myAdapter = SuggestAdapter(suggestList)
        binding.rvSuggest.adapter = myAdapter
    }
}