package nz.co.booster.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import nz.co.booster.R
import nz.co.booster.io.model.FundModel

/**
 * Created on 27,February,2021.
 */

class FundAdapter(
    val context: Context,
    var list: List<FundModel>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<FundAdapter.ViewHolder>() {

    private var mSectionPositions: ArrayList<Int>? = null

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        var fundNameTextView: AppCompatTextView = v.findViewById(R.id.fundNameTextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_fund, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fundModel = list[position]

        holder.fundNameTextView.text = fundModel.name
        holder.itemView.setOnClickListener { listener.onItemClick(fundModel) }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener {
        fun onItemClick(fundModel: FundModel)
    }
}