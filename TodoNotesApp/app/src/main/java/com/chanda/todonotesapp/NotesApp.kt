package com.chanda.todonotesapp

import android.app.Application
import com.androidnetworking.AndroidNetworking
import com.chanda.todonotesapp.data.local.db.NotesDatabase

class NotesApp : Application(){
    override fun onCreate() {
        super.onCreate()
        AndroidNetworking.initialize(applicationContext)
    }

    fun getNotesDb(): NotesDatabase {
        return NotesDatabase.getInstance(this)
    }

}