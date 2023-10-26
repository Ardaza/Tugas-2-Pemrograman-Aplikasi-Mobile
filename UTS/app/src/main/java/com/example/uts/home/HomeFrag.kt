package com.example.uts.home

import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uts.R
import kotlin.collections.ArrayList
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFrag : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var skillArrayList: ArrayList<User>
    private lateinit var imageId: TypedArray
    private lateinit var heading: Array<String>
    private lateinit var email: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        dataInitialized()

        adapter = MyAdapter(skillArrayList)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.search_bar)
        adapter.onItemClick = {
            navigateToDetail(it.heading)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                Handler(Looper.getMainLooper()).postDelayed({
                    filterList(p0)
                }, 1000)
                return false
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        param1 = null
    }

    private fun navigateToDetail(extraName: String) {
        findNavController().navigate(HomeFragDirections.actionNavHomeToNavUserDetail(extraName))
    }

    private fun filterList(query: String?) {
        if (query != null) {
            val filteredlist = ArrayList<User>()
            for (i in skillArrayList) {
                if (i.heading.lowercase(Locale.ROOT).contains(query)) {
                    filteredlist.add(i)
                }
            }

            if (filteredlist.isEmpty()) {
                Toast.makeText(context, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredlist)
            }
        }
    }

    private fun getUserData() {
        for (i in 0..<imageId.length()) {
            val skill = User(imageId.getResourceId(i, 0), heading[i], email[i])
            skillArrayList.add(skill)
        }
    }

    private fun dataInitialized() {
        skillArrayList = arrayListOf<User>()
        imageId = resources.obtainTypedArray(R.array.integer_skill_array)
        heading = resources.getStringArray(R.array.string_email_array)
        email = resources.getStringArray(R.array.string_email_array)
        getUserData()
        imageId.recycle()
    }
}