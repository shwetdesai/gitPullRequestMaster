package com.example.mygitapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mygitapplication.MyGitApplication
import com.example.mygitapplication.R
import com.example.mygitapplication.databinding.FragmentFirstBinding
import com.example.mygitapplication.di.AppViewModelFactory
import com.example.mygitapplication.viewModel.MainFragmentViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    @Inject
    lateinit var viewModelFactory: AppViewModelFactory
    private lateinit var viewModel: MainFragmentViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MyGitApplication.getInstance()?.appComponents?.inject(this)
        initMvvm()
        binding.buttonFirst.setOnClickListener {
            viewModel.getAllRepositoryFromGit("closed")
//            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initMvvm(){
        if (activity != null && context != null) {
            viewModel =
                ViewModelProvider(this, viewModelFactory)[MainFragmentViewModel::class.java]
        }
    }
}