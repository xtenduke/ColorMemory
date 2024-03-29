package com.jakelaurie.colormemory.ui.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jakelaurie.colormemory.R
import com.jakelaurie.colormemory.domain.model.GameCard
import com.jakelaurie.colormemory.ui.viewholder.GameCardItemViewHolder
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

/**
 * Adapter specifically for GameView
 * Similar to RecyclerView.Adapter with [onCreateViewHolder] and [onBindViewHolder] idioms
 */
class GameViewAdapter @Inject constructor() {
    private var hasPendingDataChange = false

    var data: List<GameCard> = mutableListOf()
        set(value) {
            field = value
            hasPendingDataChange = dataSetObserver == null
            dataSetObserver?.invalidateData()
        }

    var dataSetObserver: DatasetObserver? = null
        set(value) {
            field = value
            if(hasPendingDataChange) {
                field?.invalidateData()
                hasPendingDataChange = false
            }
        }

    var clickSubject: PublishSubject<Selection>? = null

    init {
        clickSubject = PublishSubject.create()
    }

    fun getItemCount(): Int = data.size

    fun notifyItemChanged(id: Int, position: Int) {
        dataSetObserver?.itemChanged(id, position)
    }

    fun onCreateViewHolder(parent: ViewGroup): GameCardItemViewHolder {
        val viewHolder: GameCardItemViewHolder?
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_game_card, parent, false)
        viewHolder = GameCardItemViewHolder(view)

        return viewHolder
    }

    fun onBindViewHolder(viewHolder: GameCardItemViewHolder, position: Int) {
        viewHolder.bind(data[position], position) { clickedView: View, _: Int ->
            clickSubject?.onNext(Selection(clickedView.id, data[position].pairId, position))
        }
    }
}

interface DatasetObserver {
    fun itemChanged(id: Int, position: Int)
    fun invalidateData()
}
