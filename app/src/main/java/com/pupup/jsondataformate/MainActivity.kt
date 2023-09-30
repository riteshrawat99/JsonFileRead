package com.pupup.jsondataformate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    lateinit var arr : MutableList<String>
    lateinit var type : MutableList<String>
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         listView  = findViewById(R.id.listView)
        arr = arrayListOf()
        type = arrayListOf()
        read_json()
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this@MainActivity, "Type of : ${type[i]}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun read_json(){
        var json :String? = null
        val inputStream : InputStream = assets.open("First.json")
        json = inputStream.bufferedReader().use { it.readText() }
        val jsonArray =JSONArray(json)

        for (i in 0..<jsonArray.length()){
            var jsonObj = jsonArray.getJSONObject(i)
            arr.add(jsonObj.getString("name"))
            type.add(jsonObj.getString("type"))

        }

        listView.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,arr)
    }
}