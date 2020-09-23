package `in`.gogaga.fragments

import `in`.gogaga.R
import `in`.gogaga.adapter.PostAdapter
import `in`.gogaga.databinding.FragmentRequestsBinding
import `in`.gogaga.extras.DataListener
import `in`.gogaga.mvvm.MyViewModel
import `in`.gogaga.rest.Coroutines
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class RequestsFragment : Fragment(), DataListener {

    private lateinit var binding : FragmentRequestsBinding
    private lateinit var activity1 : FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_requests, container, false)
        activity1 = requireActivity()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val myViewModel = ViewModelProvider(activity1).get(MyViewModel::class.java)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity1)
        val postAdapter = PostAdapter(activity1)
        binding.recyclerView.adapter = postAdapter
        myViewModel.getPosts(this).observe(activity1, Observer {
            it?.let {
                postAdapter.submitList(it)
                postAdapter.notifyDataSetChanged()
            }
        })

    }

    companion object {
        @JvmStatic
        fun newInstance() = RequestsFragment()
    }

    override fun errorLoading() {
        Coroutines.main {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(activity1,"Some error occurred, Try again later",Toast.LENGTH_SHORT).show()
        }
    }

    override fun dataIsFinished() {
        Coroutines.main {
            binding.progressBar.visibility = View.GONE
        }
    }
}