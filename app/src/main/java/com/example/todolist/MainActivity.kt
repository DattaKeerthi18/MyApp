package com.example.todolist

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    lateinit var item: EditText
    lateinit var add: Button

    //lateinit var listView: ListView
    lateinit var recyclerView: RecyclerView

    var itemList = ArrayList<String>()

    var fileHelper = FileHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler)
        itemList = fileHelper.readData(this)

         var adapter = MyAdapter(itemList,
            { position ->

            var alert = AlertDialog.Builder(this)
            alert.setTitle("Delete")
            alert.setMessage("Do you want to delete this item from the list?")
            alert.setCancelable(false)
            alert.setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->

                dialogInterface.cancel()
            })
            alert.setPositiveButton("yes", DialogInterface.OnClickListener { dialogInterface, i ->

                itemList.removeAt(position)
                fileHelper.writeData(itemList, applicationContext)
                //adapter?.removeItem(position)
            })
            alert.create().show()
        })
        recyclerView.adapter = adapter


        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        item = findViewById(R.id.editText)
        add = findViewById(R.id.button)
        //listView = findViewById(R.id.list)
        recyclerView = findViewById(R.id.recycler)


        // var arrrayAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,itemList)

        // recyclerView.adapter = arrrayAdapter

        add.setOnClickListener {

            var itemName: String = item.text.toString()
            itemList.add(itemName)
            item.setText("")
            fileHelper.writeData(itemList, applicationContext)
            //  arrrayAdapter.notifyDataSetChanged()
            adapter!!.addItem(itemName)


        }

    }
}