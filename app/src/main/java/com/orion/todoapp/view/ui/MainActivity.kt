package com.orion.todoapp.view.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.orion.todoapp.R
import com.orion.todoapp.base.DataBindingActivity
import com.orion.todoapp.binding.ToolbarConfiguration
import com.orion.todoapp.databinding.ActivityMainBinding
import com.orion.todoapp.databinding.DialogAddTaskBinding
import com.orion.todoapp.view.adapter.TasksAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.getViewModel
import timber.log.Timber

class MainActivity : DataBindingActivity() {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var viewBinding: ActivityMainBinding

    // Configuration
    private val toolbar = ToolbarConfiguration()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.config = toolbar

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = getViewModel()
            taskAdapter = TasksAdapter()
        }

        toolbar.setConfiguration("My Dashboard") { v: View? ->
            showDialog()
        } // Update the UI
    }

    private fun showDialog() {
        Timber.d("DISPLAY DIALOG BOX")

        val dialogBuilder = AlertDialog.Builder(this)
        val binding: DialogAddTaskBinding = DialogAddTaskBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vm = getViewModel()
        dialogBuilder.setView(binding.root)


        dialogBuilder.setPositiveButton("OK") { dialog, whichButton ->
            dialog.dismiss()
        }

        dialogBuilder.setNegativeButton("Cancel") { dialog, whichButton ->
            dialog.dismiss()
        }

        dialogBuilder.show()
    }
}