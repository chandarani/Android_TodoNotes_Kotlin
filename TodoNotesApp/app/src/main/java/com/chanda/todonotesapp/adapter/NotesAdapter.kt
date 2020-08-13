package com.chanda.todonotesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.clickListeners.ItemClickListener
import com.chanda.todonotesapp.db.Notes

class NotesAdapter(val list: List<Notes>, val itemClickListener: ItemClickListener) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_adapter_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val notes = list[position]
        val title = notes.title
        val description = notes.description
        holder.textViewTitle.text = title
        holder.textViewDescription.text = description
        holder.checkBoxMarkStatus.isChecked = notes.isTaskCompleted

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                    itemClickListener.onClick(notes)
            }
        })
        holder.checkBoxMarkStatus.setOnCheckedChangeListener(object  : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                itemClickListener.onUpdate(notes)
                notes.isTaskCompleted = isChecked
            }
        })
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.textViewTitle)
        val textViewDescription: TextView = itemView.findViewById(R.id.textViewDescription)
        val checkBoxMarkStatus: CheckBox = itemView.findViewById(R.id.checkboxMarkStatus)
    }
}





