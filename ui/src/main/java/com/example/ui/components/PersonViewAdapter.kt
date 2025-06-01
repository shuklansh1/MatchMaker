package com.example.ui.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.person.model.Result
import com.example.ui.R
import com.squareup.picasso.Picasso

class PersonAdapter(
    private val people: MutableList<Result>,
    private val onClickAccept: (Result) -> Unit = {},
) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val age: TextView = itemView.findViewById(R.id.tvAge)
        val image: ImageView = itemView.findViewById(R.id.ivProfile)
        val btnTick: Button = itemView.findViewById(R.id.btnTick)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PersonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = people[position]
        val imageView = holder.image.findViewById<ImageView>(R.id.ivProfile)
        val btnTick = holder.btnTick.findViewById<Button>(R.id.btnTick)
        holder.name.text = person.name.first
        holder.age.text = buildString {
            append("Age: ")
            append(person.dob?.age)
        }
        val imageUrl = person.picture?.large.orEmpty()

        Picasso.get()
            .load(imageUrl)
            //.placeholder(R.drawable.placeholder)
            //.error(R.drawable.error_image)
            .into(imageView)
        btnTick.setOnClickListener { onClickAccept(person) }
    }

    override fun getItemCount(): Int = people.size

    fun updatePeople(people: List<Result>) {
        this.people.clear()
        this.people.addAll(people)
        notifyDataSetChanged()
    }
}
