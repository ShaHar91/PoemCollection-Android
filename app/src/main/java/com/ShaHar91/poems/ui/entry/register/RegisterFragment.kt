package com.shahar91.poems.ui.entry.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shahar91.poems.R
import com.shahar91.poems.ui.base.normal.BaseGoogleFragment
import com.shahar91.poems.ui.entry.EntryActivity
import com.shahar91.poems.ui.entry.EntryListeners
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.reuse_entry_social_footer.*

class RegisterFragment : BaseGoogleFragment<RegisterViewModel, RegisterComponent>() {
    lateinit var listeners: EntryListeners

    override fun createComponent(): RegisterComponent {
        return DaggerRegisterComponent.builder()
            .applicationComponent(appComponent())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory).get(RegisterViewModel::class.java)

        btnLoginFacebook.setOnClickListener { listeners.onFacebookClicked() }
        btnLoginGoogle.setOnClickListener { listeners.onGoogleClicked() }

        tvLoginOrRegister.setOnClickListener {
            requireActivity().onBackPressed()
        }

        (requireActivity() as EntryActivity).setHomeUpIcon(R.drawable.ic_navigation_back)
    }

    companion object {
        @JvmStatic
        fun newInstance(showBackIcon: Boolean) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(SHOW_BACK_ICON, showBackIcon)
                }
            }
    }
}