package com.grey.kotlinpractice.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.grey.kotlinpractice.R
import com.grey.kotlinpractice.adapter.SearchAdapter
import com.grey.kotlinpractice.data.Model
import kotlinx.android.synthetic.main.fragment_search.view.*


class SearchFragment : Fragment() {

    private val itemList = ArrayList<Model.Podcast>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    lateinit var searchView: SearchView
    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView = view.findViewById(R.id.searchView)
        searchView.setOnClickListener { searchView.isIconified = false }
        changeTextColor()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                viewModel.loadResults(s)
                return true
            }

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }
        })

        val manager: RecyclerView.LayoutManager = LinearLayoutManager(view.context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = manager
        adapter = SearchAdapter(view.context, itemList)
        recyclerView.adapter = adapter


        val resultObserver = Observer<Model.Results> { result ->
            // Update the UI
            Log.v("changing UI", "changing UI--------")
            adapter.updateList(result.results)
            recyclerView.adapter = adapter
        }

        viewModel.data.observe(viewLifecycleOwner, resultObserver)



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //tvCommon.text = "home frag"
        //commonLayout.setBackgroundColor(resources.getColor(android.R.color.darker_gray))
    }

    private fun changeTextColor() {
        val id =
            searchView.context.resources.getIdentifier("android:id/search_src_text", null, null)
        val textView = searchView.findViewById<View>(id) as TextView
        textView.setTextColor(Color.WHITE)
        textView.setHintTextColor(Color.WHITE);

    }
}


