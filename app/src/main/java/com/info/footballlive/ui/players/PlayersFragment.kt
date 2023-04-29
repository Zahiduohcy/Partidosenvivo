package com.info.footballlive.ui.players


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager

import com.info.footballlive.R
import com.info.footballlive.extensions.hide
import com.info.footballlive.extensions.show
import com.info.footballlive.rest.model.Player
import com.info.footballlive.ui.adapter.PlayerAdapter
import com.info.footballlive.ui.players.playerdetail.PlayerDetailActivity
import com.info.footballlive.utils.GridItemDecoration
import kotlinx.android.synthetic.main.fragment_players.*
import org.jetbrains.anko.startActivity

class PlayersFragment : Fragment(), PlayersContract.View {

    companion object {
        val ARG_TEAM_ID = "ARG_TEAM_ID"

        fun newInstance(teamId: Int) = PlayersFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TEAM_ID, teamId)
            }
        }
    }

    private var mTeamId: Int? = null

    private lateinit var mPresenter: PlayersPresenter
    private lateinit var mAdapter: PlayerAdapter
    private var mPlayerList: MutableList<Player> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mTeamId = it.getInt(ARG_TEAM_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // recycler view
        rvPlayerList.layoutManager = GridLayoutManager(context, 3)
        rvPlayerList.addItemDecoration(GridItemDecoration(16, 3))
        mAdapter = PlayerAdapter(mPlayerList) {
            context?.startActivity<PlayerDetailActivity>(PlayerDetailActivity.ARG_PLAYER_ID to it.id)
        }
        rvPlayerList.adapter = mAdapter

        // presenter
        mPresenter = PlayersPresenter(this)
        mTeamId?.let {
            mPresenter.getData(it)
        }
    }

    override fun showLoading() {
        mainProgressBar.show()
        rvPlayerList.hide()
    }

    override fun hideLoading() {
        mainProgressBar.hide()
        rvPlayerList.show()
    }

    override fun display(playerList: ArrayList<Player>) {
        if (playerList.isEmpty()) {
//            Toast.makeText(context, "Can not pull the player list", Toast.LENGTH_SHORT).show()
        }
        mPlayerList.clear()
        mPlayerList.addAll(playerList)
        mAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroyPresenter()
    }
}
