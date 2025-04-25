package com.example.dogapi2recycler.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.dogapi2recycler.R
import com.example.dogapi2recycler.databinding.FragmentLogoutDialogBinding
import com.example.dogapi2recycler.viewmodel.MyViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LogoutDialogFragment : DialogFragment() {
    lateinit var bind: FragmentLogoutDialogBinding
    val myVM: MyViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bind = FragmentLogoutDialogBinding.inflate(inflater)
        return bind.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_add_user_dialog_theme)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        bind.yesButton.setOnClickListener {

            val pref = activity.getSharedPreferences("appSharedPref", Context.MODE_PRIVATE)
            val email = pref.getString("email", "?").toString()
            lifecycleScope.launch(Dispatchers.IO) {
                myVM.getDB(activity).userDAO().updateActiveStatus(email, false)
            }
            pref.edit().clear().apply()
            activity.startActivity(Intent(activity, LaunchActivity::class.java))
            activity.finishAffinity()
        }

        bind.noButton.setOnClickListener {
//            lifecycleScope.launch(Dispatchers.IO) {
//                myVM.getDB(activity).userDAO().deleteUserWithName("GHGH")
//            }
            dismiss()
        }
    }


}