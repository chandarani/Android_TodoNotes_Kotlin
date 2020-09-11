package com.chanda.todonotesapp.mynotes.clickListeners

import com.chanda.todonotesapp.data.local.db.Notes


interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}