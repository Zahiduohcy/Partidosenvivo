package com.envivo.futbolenvivoyendirectopartidos.ui.teams


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.extensions.hide
import com.envivo.futbolenvivoyendirectopartidos.extensions.show
import com.envivo.futbolenvivoyendirectopartidos.rest.model.Team
import com.envivo.futbolenvivoyendirectopartidos.ui.adapter.TeamAdapter
import com.envivo.futbolenvivoyendirectopartidos.ui.teams.teamdetail.TeamDetailActivity
import com.envivo.futbolenvivoyendirectopartidos.utils.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_teams.*


class TeamsFragment : Fragment(), TeamsContract.View {

    companion object {
        val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"

        fun newInstance(leagueId : Int) = TeamsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_LEAGUE_ID, leagueId)
            }
        }
    }

    private var mLeagueId: Int? = null

    private lateinit var mPresenter: TeamsPresenter
    private lateinit var mAdapter: TeamAdapter
    private var mTeamList: MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mLeagueId = it.getInt(ARG_LEAGUE_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // recycler view
        rvTeamList.layoutManager = GridLayoutManager(context, 3)
        rvTeamList.addItemDecoration(GridItemDecoration(16, 3))
        mAdapter = TeamAdapter(mTeamList) {
            val intent = Intent(context, TeamDetailActivity::class.java)
            intent.putExtra(TeamDetailActivity.ARG_LEAGUE_ID, mLeagueId)
            intent.putExtra(TeamDetailActivity.ARG_TEAM, it)
            startActivity(intent)
//            context?.startActivity<TeamDetailActivity>(
//                    TeamDetailActivity.ARG_LEAGUE_ID to mLeagueId,
//                    TeamDetailActivity.ARG_TEAM to it)
        }
        rvTeamList.adapter = mAdapter

        // presenter
        mPresenter = TeamsPresenter(this)
        mLeagueId?.let {
            mPresenter.getData(it)
        }
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvTeamList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvTeamList.show()
    }

    override fun display(teamList: List<Team>) {
        if (teamList.isEmpty()) {
//            Toast.makeText(context, "Can not pull the team list", Toast.LENGTH_SHORT).show()
        }
        mTeamList.clear()
        mTeamList.addAll(teamList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
