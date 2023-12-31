package com.envivo.futbolenvivoyendirectopartidos.ui.standing


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.extensions.hide
import com.envivo.futbolenvivoyendirectopartidos.extensions.show
import com.envivo.futbolenvivoyendirectopartidos.rest.model.StandingModel
import com.envivo.futbolenvivoyendirectopartidos.ui.adapter.StandingAdapter
import kotlinx.android.synthetic.main.fragment_standing.*

class StandingFragment : Fragment(), StandingContract.View {

    companion object {
        val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"
        val ARG_SEASON = "ARG_SEASON"
        fun newInstance(leagueId: Int, season: Int) = StandingFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_LEAGUE_ID, leagueId)
                putInt(ARG_SEASON, season)
            }
        }
    }

    private var mLeagueId: Int? = null
    private var mSeason: Int? = null
    private lateinit var mPresenter: StandingPresenter
    private lateinit var mAdapter: StandingAdapter
    private var mStandingList: MutableList<StandingModel.Standings> = mutableListOf()
    private var mLeague: MutableLiveData<StandingModel.Standings> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mLeagueId = it.getInt(ARG_LEAGUE_ID)
            mSeason = it.getInt(ARG_SEASON)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // recycler view
        rvStandingList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvStandingList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        mAdapter = StandingAdapter(mStandingList) {}
        rvStandingList.adapter = mAdapter

        // presenter
        mPresenter = StandingPresenter(this)
        mLeagueId?.let {
            mSeason?.let { it1 -> mPresenter.getData(it, it1) }
        }
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvStandingList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvStandingList.show()
    }

    override fun display(standingList: StandingModel.Response?) {
        if (standingList !=null) {
//            Toast.makeText(context, "Can not pull the standing list", Toast.LENGTH_SHORT).show()
        }
        val league = standingList?.league;

        standingList?.league?.standings?.get(0)?.forEach{
                val _league = StandingModel.League(league?.id, league?.name, league?.country, league?.logo, league?.flag, league?.season )
                it.league = _league

        }
        mStandingList.clear()
        standingList?.league?.standings?.get(0)?.let { mStandingList.addAll(it) }
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
