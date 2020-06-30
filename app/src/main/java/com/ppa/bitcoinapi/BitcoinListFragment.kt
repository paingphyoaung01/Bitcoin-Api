package com.ppa.bitcoinapi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppa.bitcoinapi.adapter.BitcoinListAdapter
import com.ppa.bitcoinapi.model.ArticleResult
import com.ppa.bitcoinapi.viewmodel.ArticleViewModel
import kotlinx.android.synthetic.main.fragment_bitcoin_list.*


class BitcoinListFragment : Fragment() {

    private lateinit var bitcoinListAdapter: BitcoinListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    lateinit var articleViewModel : ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bitcoin_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewManager = LinearLayoutManager(activity)
        bitcoinListAdapter = BitcoinListAdapter()
        recycler_View.adapter = bitcoinListAdapter
        recycler_View.layoutManager = viewManager
        observeViewModel()
    }

    fun observeViewModel() {
        articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel::class.java)

        articleViewModel.getResult().observe(
            this, Observer<ArticleResult> { result ->
                recycler_View.visibility = View.VISIBLE
                bitcoinListAdapter.updateList(result.articles)
            }
        )
        articleViewModel.getError().observe(
            this, Observer<Boolean> {isError ->
                if (isError) {
                    txt_Error.visibility = View.VISIBLE
                    recycler_View.visibility = View.GONE // (View.INVISIBLE = View.GONE)

                } else {
                    txt_Error.visibility = View.INVISIBLE
                }

            }
        )
        articleViewModel.getLoading().observe(
            this, Observer<Boolean> { isLoading ->
                progress_Bar.visibility = (if (isLoading)
                    View.VISIBLE else View.INVISIBLE)
                if (isLoading) {
                    txt_Error.visibility = View.GONE
                    recycler_View.visibility = View.GONE
                }
            }
        )

    }
    override fun onResume() {
        super.onResume()
        articleViewModel.loadResults()
    }
}