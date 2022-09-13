package com.example.mygitapplication.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mygitapplication.R
import com.example.mygitapplication.infra.model.GitData

class PullRequestAdapter(
    val context: Context,
    var list: ArrayList<GitData.GitPullRequest>
): RecyclerView.Adapter<PullRequestAdapter.PullReqAdapter>() {
    companion object{
        private const val TAG = "PullRequestAdapter"
    }

    inner class PullReqAdapter(view: View): RecyclerView.ViewHolder(view) {
        var ivProfile: AppCompatImageView = view.findViewById(R.id.ivUserImages)
        var tvTitle: AppCompatTextView = view.findViewById(R.id.tvTitle)
        var tvAuthor: AppCompatTextView = view.findViewById(R.id.tvAuthor)
        var tvCreatedAt: AppCompatTextView = view.findViewById(R.id.tvCreatedAt)
        var tvClosedAt: AppCompatTextView = view.findViewById(R.id.tvClosedAt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullReqAdapter {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return PullReqAdapter(inflater)
    }

    override fun onBindViewHolder(holder: PullReqAdapter, position: Int) {
        if(context != null && position < list.size){
            val item = list[position]
            val image = item.user?.userImage
            val title = item.title
            val author = item.user?.userName
            val createdAt = item.createdAt
            val closedAt = item.closedAt

            holder.tvTitle.text = title
            holder.tvAuthor.text = author
            holder.tvCreatedAt.text = createdAt
            holder.tvClosedAt.text = closedAt

            Glide.with(context)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(holder.ivProfile)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}