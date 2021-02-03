package com.orion.todoapp.view.ui.main

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.orion.todoapp.R
import com.orion.todoapp.base.DataBindingActivity
import com.orion.todoapp.binding.ToolbarConfiguration
import com.orion.todoapp.databinding.ActivityMainBinding
import com.orion.todoapp.databinding.DialogAddTaskBinding
import com.orion.todoapp.model.TaskData
import com.orion.todoapp.model.TaskItem
import com.orion.todoapp.view.adapter.TasksAdapter
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.dialog_add_task.*
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.getViewModel


class MainActivity : DataBindingActivity(), TasksAdapter.OnItemClickListener {
    private val binding: ActivityMainBinding by binding(R.layout.activity_main)
    private lateinit var viewBinding: ActivityMainBinding
    private val toolbar = ToolbarConfiguration()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.config = toolbar

        binding.apply {
            lifecycleOwner = this@MainActivity
            vm = getViewModel()
            taskAdapter = TasksAdapter(this@MainActivity)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        toolbar.setConfiguration("My Dashboard") { v: View? ->
            showDialog()
        } // Update the UI
    }

    private fun showDialog() {

        val dialogBuilder = AlertDialog.Builder(this)
        val binding: DialogAddTaskBinding = DialogAddTaskBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.vm = getViewModel()
        dialogBuilder.setView(binding.root)

        val alertDialog: AlertDialog = dialogBuilder.create()

        binding.tvAdd.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please enter required fields!", Toast.LENGTH_LONG).show()
            } else {
                val item = TaskItem(title, description)
                viewModel.postTask(item)
                alertDialog.cancel()
                binding.etTitle.text?.clear()
                binding.etDescription.text?.clear()
            }
        }

        binding.tvCancel.setOnClickListener {
            alertDialog.cancel()
        }

        alertDialog.show()
    }

    override fun onItemClick(item: TaskData) {
        Log.d("ImageClicked", item.title)
    }

}