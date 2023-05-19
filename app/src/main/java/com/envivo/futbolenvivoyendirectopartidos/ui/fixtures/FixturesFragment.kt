package com.envivo.futbolenvivoyendirectopartidos.ui.fixtures


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.ui.adapter.FixtureAdapter
import com.envivo.futbolenvivoyendirectopartidos.extensions.hide
import com.envivo.futbolenvivoyendirectopartidos.extensions.show
import com.envivo.futbolenvivoyendirectopartidos.rest.model.Fixture
import com.envivo.futbolenvivoyendirectopartidos.ui.fixtures.fixturedetail.FixtureDetailActivity
import kotlinx.android.synthetic.main.fragment_fixtures.*
import java.util.*

class FixturesFragment : Fragment(), FixturesContract.View {

    companion object {
        val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"
        val ARG_SEASON = "ARG_SEASON"

        fun newInstance(leagueId : Int, season: Int) = FixturesFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_LEAGUE_ID, leagueId)
                putInt(ARG_SEASON, season)
            }
        }
    }

    private var mLeagueId: Int? = null
    private var mSeason: Int? = 2023

    private lateinit var mPresenter: FixturesPresenter
    private lateinit var mAdapter: FixtureAdapter
    private var mFixtureList: MutableList<Fixture> = mutableListOf()

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
        return inflater.inflate(R.layout.fragment_fixtures, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // recycler view
        rvFixtureList.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mAdapter = FixtureAdapter(mFixtureList) {
            val intent = Intent(context, FixtureDetailActivity::class.java).apply {
                putExtra(FixtureDetailActivity.ARG_FIXTURE_ID, it.fixture?.id)
            }
            startActivity(intent)

        }
        rvFixtureList.adapter = mAdapter

        // presenter
        mPresenter = FixturesPresenter(this)
        mLeagueId?.let {
            mSeason?.let { it1 -> mPresenter.getData(it, it1) }
        }
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvFixtureList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvFixtureList.show()
    }

    override fun display(fixtureList: List<Fixture>) {
        if (fixtureList.isEmpty()) {
//            Toast.makeText(context, "Can not pull the fixture list", Toast.LENGTH_SHORT).show()
        }
        Collections.reverse(fixtureList)

        mFixtureList.clear()
        mFixtureList.addAll(fixtureList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
