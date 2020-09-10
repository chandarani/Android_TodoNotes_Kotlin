package com.chanda.todonotesapp.onboarding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.chanda.todonotesapp.R
import kotlinx.android.synthetic.main.fragment_on_boarding_two.*


class OnBoardingTwoFragment : Fragment() {

    private lateinit var buttonDone: Button
    private lateinit var buttonBack: Button
    private lateinit var onOptionClick: OnOptionClick

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onOptionClick = context as OnOptionClick
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_on_boarding_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setupClickListener()
    }

    private fun bindViews(view: View) {
        buttonBack = view.findViewById(R.id.buttonBack)
        buttonDone = view.findViewById(R.id.buttonDone)
    }

    private fun setupClickListener() {
        buttonBack.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onOptionClick.onOptionBack()
            }

        })

        buttonDone.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                onOptionClick.onOptionDone()
            }
        })
    }

    interface OnOptionClick {
        fun onOptionBack()
        fun onOptionDone()
    }


}

