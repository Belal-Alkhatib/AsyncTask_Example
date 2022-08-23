package com.example.and2_luc10_asynctaskex

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.and2_luc10_asynctaskex.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            count = 0
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.progress = 0
            MyAsyncTask().execute(100)
        }
    }

    inner class MyAsyncTask: AsyncTask<Int?, Int?, String>(){
        override fun onPreExecute() {
            super.onPreExecute()
            tvOutput.text = "Task Starting"
        }

        override fun doInBackground(vararg params: Int?): String {

            while (count <= params[0]!!){
                Thread.sleep(1000)
                publishProgress(count)
                count+=10
            }

            return "Task Completed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            tvOutput.text = "Running : "
            progressBar.progress = values[0]!!
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressBar.visibility = View.GONE
            tvOutput.text = result
            btnStart.text = "Restart"
        }

    }
}