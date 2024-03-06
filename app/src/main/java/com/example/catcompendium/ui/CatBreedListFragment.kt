package com.example.catcompendium.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.catcompendium.R
import com.example.catcompendium.databinding.LayoutCatBreedListBinding
import com.example.catcompendium.viewmodel.CatBreedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatBreedListFragment : Fragment(R.layout.layout_cat_breed_list) {
    private lateinit var catBreedViewModel: CatBreedViewModel
    private lateinit var binding: LayoutCatBreedListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        catBreedViewModel = ViewModelProvider(requireActivity())[CatBreedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutCatBreedListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()

    }

    private fun subscribeUI() {
        lifecycleScope.launch {
            catBreedViewModel.breedList.catch {
                Toast.makeText(context, "Show error", Toast.LENGTH_SHORT).show()
            }.collectLatest {
               binding.rvCatBreedList.setData(it)
            }
        }
    }
}