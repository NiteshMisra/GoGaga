package `in`.gogaga.activity

import `in`.gogaga.R
import `in`.gogaga.adapter.TabAdapter
import `in`.gogaga.databinding.ActivityMainBinding
import `in`.gogaga.fragments.BlankFragment
import `in`.gogaga.fragments.RequestsFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.addFragment(RequestsFragment.newInstance(),"Requests")
        tabAdapter.addFragment(BlankFragment.newInstance(),"Offerings")
        tabAdapter.addFragment(BlankFragment.newInstance(),"Recommends")

        binding.viewPager.adapter = tabAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)


    }
}