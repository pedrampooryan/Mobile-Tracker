package com.example.mobiletracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mobiletracker.databinding.FragmentMainScreenBinding


class MainScreen : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding get() = _binding!!
    //private val viewModel : MainScreenViewModel by viewModels()
    private val viewModel: MainScreenViewModel by lazy {
        ViewModelProvider(this)[MainScreenViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.testText.text = "Hello!!!!"
        viewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
