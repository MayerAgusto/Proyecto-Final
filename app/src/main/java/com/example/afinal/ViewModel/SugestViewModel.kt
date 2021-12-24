package com.example.afinal.ViewModel

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.afinal.Model.Suggest
import com.example.afinal.databinding.FragmentSuggestBinding
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import java.lang.Error


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


        binding.etLookFor.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }
    private fun filter(text: String) {
        val filteredList: ArrayList<Suggest> = ArrayList()
        for (item in suggestList) {
            if (item.category.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item)
            }
        }
        myAdapter.filterList(filteredList)
    }
    private fun loadData() {
        suggestList = arrayListOf()
        db = FirebaseFirestore.getInstance()
        db.collection("sugerencias").addSnapshotListener(object : EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null){

                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if(dc.type == DocumentChange.Type.ADDED){
                        suggestList.add(dc.document.toObject(Suggest::class.java))

                    }
                }

                myAdapter.notifyDataSetChanged()
            }
        })

        /*
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
         */
    }
    private fun loadRecyclerView(){
        binding.rvSuggest.layoutManager = LinearLayoutManager(fragment.activity)
        myAdapter = SuggestAdapter(suggestList)
        binding.rvSuggest.adapter = myAdapter
    }
}