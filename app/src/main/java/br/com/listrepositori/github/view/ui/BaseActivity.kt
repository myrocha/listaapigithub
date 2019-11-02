package br.com.listrepositori.github.view.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import dagger.android.AndroidInjection

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
    }

    fun setupToolbar(
        toolBar: Toolbar?, icon: Int,
        displayHome: Boolean = true
    ) {

        toolBar?.let { toolbar ->
            setSupportActionBar(toolBar)
            supportActionBar?.let { actionBar ->
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar?.setDisplayHomeAsUpEnabled(true)
                actionBar.setDisplayShowHomeEnabled(displayHome)
            }
            if (icon != -1 && displayHome) {
                toolbar.navigationIcon = ContextCompat.getDrawable(this, icon)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val SPACE_EMPTY = ""
    }
}
