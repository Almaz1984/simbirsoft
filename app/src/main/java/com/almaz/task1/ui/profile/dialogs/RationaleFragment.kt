package com.almaz.task1.ui.profile.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.almaz.task1.R
import com.almaz.task1.ui.profile.ProfileFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class RationaleFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rationale, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.rationale_button).setOnClickListener {
            setFragmentResult(
                ProfileFragment.RATIONALE_KEY,
                bundleOf(ProfileFragment.RESULT_KEY to true)
            )
            dismiss()
        }
    }

    companion object {
        const val TAG = "rationale_tag"
    }
}
