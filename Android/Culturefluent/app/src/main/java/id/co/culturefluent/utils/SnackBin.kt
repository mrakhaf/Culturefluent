package id.co.culturefluent.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar

object SnackBin {
    fun show(view: View, title: String) {
        Snackbar.make(view, title, Snackbar.LENGTH_SHORT).show()
    }
}