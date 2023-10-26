package com.example.uts.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uts.databinding.UserDetailBinding

class UserDetailFrag :Fragment(){
    private var _binding : UserDetailBinding? = null

    private val binding get() = _binding!!

    companion object{
        var EXTRA_NAME = "extra_name"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = UserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            val name = arguments?.getString(EXTRA_NAME)
            binding.textName.text = name
        }
    }
}

