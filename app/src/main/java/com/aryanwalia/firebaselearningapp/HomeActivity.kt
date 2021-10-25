package com.aryanwalia.firebaselearningapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var toolbar : androidx.appcompat.widget.Toolbar
    private lateinit var nav_view : NavigationView
    private lateinit var drawer_layout : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        toolbar = findViewById(R.id.toolbar)
        nav_view = findViewById(R.id.nav_view)
        drawer_layout = findViewById(R.id.drawer_layout)

        setSupportActionBar(toolbar)

        val navController = Navigation.findNavController(this,R.id.fragment)
        NavigationUI.setupWithNavController(nav_view,navController)
        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.fragment),drawer_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu,menu)
        return true
    }


}