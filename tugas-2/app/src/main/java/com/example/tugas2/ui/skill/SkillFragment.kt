package com.example.tugas2.ui.skill

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas2.R

private const val t10 = "t1"
private const val t20 = "t2"

class SkillFragment : Fragment() {
    private var t1: String? = null
    private var t2: String? = null

    private lateinit var adapter : myAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var skillArrayList : ArrayList<Skill>

    private lateinit var imageId : Array<Int>
    private lateinit var heading : Array<String>
    lateinit var skill : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            t1 = it.getString(t10)
            t1 = it.getString(t20)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SkillFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(t1: String, t2: String) =
            SkillFragment().apply {
                arguments = Bundle().apply {
                    putString(t10, t1)
                    putString(t20, t2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = myAdapter(skillArrayList)
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("skill", it)
            startActivity(intent)
        }
    }

    private fun dataInitialize(){
        skillArrayList = arrayListOf<Skill>()

        imageId = arrayOf(
            R.drawable.ic_c,
            R.drawable.ic_java,
            R.drawable.ic_godot
        )

        heading = arrayOf(
            getString(R.string.text_c_plus_plus),
            getString(R.string.text_java),
            getString(R.string.text_godot)
        )

        for (i in imageId.indices){

            val skill = Skill(imageId[i],heading[i])
            skillArrayList.add(skill)
        }
    }

}