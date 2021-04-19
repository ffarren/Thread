package com.example.thread

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {


    private var status : Boolean = false
    private lateinit var text: TextView
    private lateinit var btn_start: Button
    private lateinit var btn_stop: Button
    private var bgthread: Thread? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text = findViewById(R.id.textview)
        btn_start = findViewById(R.id.btn_start)
        btn_stop = findViewById(R.id.btn_stop)

        btn_start.setOnClickListener(this)
        btn_stop.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when (v?.id) {
            (R.id.btn_start) -> {
                status = true
                if (bgthread == null || bgthread?.state == Thread.State.TERMINATED) {
                    val runnable = Runnable {
                        try {
                            while (status == true){
                                val angka = (0..9).random()
                                Thread.sleep(500)
                                text.post {
                                    text.text = angka.toString()
                                }
                            }
                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        }
                    }
                    bgthread = Thread(runnable)
                    bgthread?.start()
                }
            }

            (R.id.btn_stop) -> {
                status = false
            }
        }
    }
}