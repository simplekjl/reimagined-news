package com.simplekjl.news.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.simplekjl.news.R
import com.simplekjl.news.presentation.news.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }
}
