package com.durbar.bangabandhuplay.ui.pathshala

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.durbar.bangabandhuplay.R
import com.durbar.bangabandhuplay.data.model.pathshala.DataX
import com.durbar.bangabandhuplay.data.model.pathshala.Ebook
import com.durbar.bangabandhuplay.databinding.ItemParentPathshalaBinding
import com.durbar.bangabandhuplay.databinding.ItemRcvPathshalaBinding
import com.squareup.picasso.Picasso

class CommonAdapter( private val category: DataX) : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemParentPathshalaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.binding.categoryTv.text = category.title
        holder.binding.rcvPathshala.apply {

            val layoutManager = GridLayoutManager(holder.binding.rcvPathshala.context, 3)
            setLayoutManager(layoutManager)
            adapter = CategoryItemAdapter(category.ebooks)
        }
    }


    inner class ViewHolder(val binding: ItemParentPathshalaBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun getItemCount(): Int = 1
}

class CategoryItemAdapter(private val list: List<Ebook> ) : RecyclerView.Adapter<CategoryItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRcvPathshalaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list?.get(position)
        val image = current?.thumbnail
        val title = current?.title
        val pdf =current?.pdf.toString()

        Log.d("pathshala", "pathsha adapter class image: $image")

        if (image != null) {
            holder.binding.ivPdf.clipToOutline = true
            Picasso.get().load(image).fit().into(holder.binding.ivPdf)
        }

        holder.binding.tvTitle.text = title?: ""

        holder.binding.root.setOnClickListener {
            // callbackUrl(pdf)
            val bundle = Bundle().apply {
                putString("pdfurl", pdf)
            }

            // Assuming you are in a Fragment and can use findNavController directly
            it.findNavController().navigate(R.id.pdfViewFragment, bundle)
        }
    }


    inner class ViewHolder(val binding: ItemRcvPathshalaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = list.size


}