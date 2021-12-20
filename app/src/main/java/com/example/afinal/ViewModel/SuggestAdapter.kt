package com.example.afinal.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.Model.Suggest
import com.example.afinal.R
import com.example.afinal.databinding.SuggestItemBinding
import com.squareup.picasso.Picasso

class SuggestAdapter(val suggestList:List<Suggest>):RecyclerView.Adapter<SuggestAdapter.SuggestHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuggestHolder(layoutInflater.inflate(R.layout.suggest_item,parent ,false))
    }

    override fun onBindViewHolder(holder: SuggestHolder, position: Int) {
        holder.render(suggestList[position])
        holder.binding.cardLayout.setOnClickListener {
            val suggest = suggestList[position]
            suggest.expand = !suggest.expand
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return suggestList.size
    }

    class SuggestHolder(val view:View):RecyclerView.ViewHolder(view){
        val binding = SuggestItemBinding.bind(view)
        fun render(suggest: Suggest){
            binding.tvSuggestName.text = suggest.title
            binding.tvSuggestCategory.text = suggest.category
            binding.tvSuggestDescription.text = suggest.description
            Picasso.get().load(suggest.image).into(binding.ivSuggest)
            binding.expandible.visibility = if(suggest.expand) View.VISIBLE else View.GONE

        }
    }
}