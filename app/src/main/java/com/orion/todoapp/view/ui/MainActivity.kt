package com.orion.todoapp.view.ui

import android.os.Bundle
import com.orion.todoapp.R
import com.orion.todoapp.base.DataBindingActivity
import com.orion.todoapp.databinding.ActivityMainBinding
import com.orion.todoapp.view.adapter.TasksAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class MainActivity : DataBindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = getViewModel()
            taskAdapter = TasksAdapter()
        }
    }
}