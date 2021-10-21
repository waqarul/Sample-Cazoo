package com.wtmcodex.cazoo.features

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.cazoo.core.data.model.AlertModel
import com.wtmcodex.cazoo.core.di.Injectable
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactoryProvider: ViewModelProvider.Factory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = getBindingView(inflater)
        getOrCreateViewModel()
        setupUI()
        setListeners()
        setupBindings()
        loadData()
        return view
    }

    protected fun showAlert(alertModel: AlertModel) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(alertModel.title)
            .setMessage(alertModel.message)
            .setCancelable(false)
            .setPositiveButton(alertModel.positiveButtonTitle) { dialog, which ->
                if (alertModel.listener != null) {
                    alertModel.listener.onPositiveButtonClickAction()
                }
            }
            .setNegativeButton(alertModel.negativeButtonTitle) { dialog, which ->
                if (alertModel.listener != null) {
                    alertModel.listener.onNegativeButtonClickAction()
                }
            }
        val dialog = builder.create()
        dialog.show()
        dialog.getButton(DialogInterface.BUTTON_POSITIVE).isAllCaps = false
        dialog.getButton(DialogInterface.BUTTON_NEGATIVE).isAllCaps = false
    }

    // this method is used to get binding view
    protected abstract fun getBindingView(inflater: LayoutInflater?): View

    // this method is used to initialize View Model
    protected abstract fun getOrCreateViewModel(): Unit

    // this method is used to setup UI views
    protected open fun setupUI() {}

    // this method will be invoked from @onViewCreated to setup bindings
    protected open fun setListeners() {}

    // this method will be invoked from @onViewCreated to setup bindings
    protected open fun setupBindings() {}

    // this method will be invoked from @onViewCreated to load data remotely/locally
    protected open fun loadData() {}
}