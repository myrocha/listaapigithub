package br.com.listrepositori.github.extensions


import android.app.Activity
import android.view.View
import android.widget.ImageView
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

fun ImageView.loadUrl(url: String?) {
    Picasso.get()
        .load(url)
        .placeholder(R.drawable.icon_user)
        .error(R.drawable.icon_user)
        .into(this);
}