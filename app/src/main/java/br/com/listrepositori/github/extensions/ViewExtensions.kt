package br.com.listrepositori.github.extensions


import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.listrepositori.github.R
import com.squareup.picasso.Picasso
import org.jetbrains.anko.runOnUiThread


fun RecyclerView.createRecyclerView(
    activity: Activity,
    recyclerViewAdapter: RecyclerView.Adapter<*>,
    hasFixedSize: Boolean = true,
    itemAnimatorParam: RecyclerView.ItemAnimator? = null

) {
    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    setHasFixedSize(hasFixedSize)
    itemAnimator = itemAnimatorParam
    adapter = recyclerViewAdapter
}

fun View.changeVisibility(isVisible: Boolean) {
    this.context.runOnUiThread {
        visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}

fun ImageView.loadUrl(url: String?, @DrawableRes placeholder: Int? = null) {
    /*
    if (placeholder == null) {
        Glide.with(context)
            .load(url)
            .apply( RequestOptions.circleCropTransform())
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(this)
        return
    }
    Glide.with(context)
        .load(url)
        .into(this)

     */


    Picasso.get()
        .load(url)
        .placeholder(R.drawable.ic_account)
        .error(R.drawable.ic_account)
        .into(this);
}