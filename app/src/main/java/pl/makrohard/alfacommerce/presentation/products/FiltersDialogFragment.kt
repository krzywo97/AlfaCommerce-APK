package pl.makrohard.alfacommerce.presentation.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pl.makrohard.alfacommerce.databinding.FragmentFiltersDialogBinding

class FiltersDialogFragment : BottomSheetDialogFragment() {
    private lateinit var viewBinding: FragmentFiltersDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentFiltersDialogBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewBinding.apply.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(): FiltersDialogFragment =
            FiltersDialogFragment()
    }
}