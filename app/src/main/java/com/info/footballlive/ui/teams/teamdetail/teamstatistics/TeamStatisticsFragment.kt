package com.info.footballlive.ui.teams.teamdetail.teamstatistics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.info.footballlive.R
import com.info.footballlive.rest.model.Team
import com.info.footballlive.rest.model.TeamStatistics

class TeamStatisticsFragment : Fragment(), TeamStatisticsContract.View {

    companion object {
        val ARG_LEAGUE_ID = "ARG_LEAGUE_ID"
        val ARG_TEAM = "ARG_TEAM"

        fun newInstance(leagueId: Int, team: Team) = TeamStatisticsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_LEAGUE_ID, leagueId)
                putParcelable(ARG_TEAM, team)
            }
        }
    }

    private var mLeagueId = 0
    private lateinit var mTeam: Team

    private lateinit var mPresenter: TeamStatisticsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mLeagueId = it.getInt(ARG_LEAGUE_ID)
            mTeam = it.getParcelable(ARG_TEAM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_statistics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // presenter
        mPresenter = TeamStatisticsPresenter(this)
        mPresenter.getData(mLeagueId, mTeam.team_id!!)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun display(teamStatistics: TeamStatistics?) {
        if (teamStatistics == null) {
            Toast.makeText(context, "Can not pull the team statistics", Toast.LENGTH_SHORT).show()
            return
        }
        ////
    }
}
