package org.koin.sample.androidx.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.koin.android.ext.android.getKoin
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.sample.android.R
import org.koin.sample.androidx.components.ID
import org.koin.sample.androidx.components.mvvm.SimpleViewModel
import org.koin.sample.androidx.components.scope.Controller
import org.koin.sample.androidx.components.scope.Session

class MVVMFragment : Fragment() {

    val shared: SimpleViewModel by sharedViewModel { parametersOf(ID) }
    val simpleViewModel: SimpleViewModel by viewModel { parametersOf(ID) }
    val controller: Controller? by lazy { currentScope.get<Controller>() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.mvvm_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        assertNotEquals(shared, simpleViewModel)
        assertEquals((activity as MVVMActivity).simpleViewModel, shared)
        assertEquals(activity, controller?.owner)
        assertEquals(activity, currentScope.get<MVVMActivity>())
        assertEquals(controller?.owner, currentScope.get<MVVMActivity>())
        assertEquals(currentScope.get<Session>(), getKoin().getProperty("session"))
    }
}