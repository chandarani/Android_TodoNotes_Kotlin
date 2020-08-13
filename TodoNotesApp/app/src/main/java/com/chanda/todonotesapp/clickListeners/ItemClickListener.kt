package com.chanda.todonotesapp.clickListeners

import com.chanda.todonotesapp.db.Notes


interface ItemClickListener {
    fun onClick(notes: Notes)
    fun onUpdate(notes: Notes)
}