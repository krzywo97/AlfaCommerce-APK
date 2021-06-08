package pl.makrohard.alfacommerce.presentation.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.makrohard.alfacommerce.R
import pl.makrohard.alfacommerce.databinding.BottomNavigationFragmentBinding
import pl.makrohard.alfacommerce.util.setupWithNavController

class BottomNavigationFragment : Fragment() {

    private lateinit var viewBinding: BottomNavigationFragmentBinding
    private val bottomNavigationViewModel: BottomNavigationViewModel by viewModel()

    companion object {
        fun newInstance() = BottomNavigationFragment()
    }

    private lateinit var viewModel: BottomNavigationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = BottomNavigationFragmentBinding.inflate(layoutInflater, container, false)
        viewBinding.bottomNavigation.setupWithNavController(
            listOf(R.navigation.bottom_nav_explore, R.navigation.bottom_nav_search),
            childFragmentManager,
            viewBinding.subContainer.id, Intent()
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
