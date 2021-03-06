package id.dtprsty.movieme.ui.movie

import android.content.res.Resources
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.dtprsty.movieme.BuildConfig
import id.dtprsty.movieme.R
import id.dtprsty.movieme.data.remote.movie.Movie
import id.dtprsty.movieme.util.DateHelper
import kotlinx.android.synthetic.main.item_highlight.*
import timber.log.Timber

class MovieHighlightItem(private val movie: Movie?) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Timber.d("Title : ${movie?.title} & Year ${movie?.releaseDate}")
        viewHolder.tvMovieTitle.text = movie?.title
        viewHolder.tvYear.text = DateHelper.dateToYear(movie?.releaseDate)
        Glide.with(viewHolder.itemView.context)
            .load("${BuildConfig.IMAGE_URL}${movie?.backdrop}")
            .centerCrop()
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .apply(
                RequestOptions.placeholderOf(R.drawable.img_placeholder)
            )
            .into(viewHolder.ivBackdrop)

        val displayMetrics =
            Resources.getSystem().displayMetrics
        val width = displayMetrics.widthPixels
        val params: ViewGroup.LayoutParams = viewHolder.itemView.layoutParams
        params.width = width * 70 / 100
        viewHolder.itemView.layoutParams = params
    }

    override fun getLayout(): Int {
        return R.layout.item_highlight
    }
}