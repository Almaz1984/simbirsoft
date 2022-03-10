package com.almaz.task1.ui.profile

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.almaz.task1.R
import com.almaz.task1.adapters.friends.FriendsAdapter
import com.almaz.task1.data.repository.Repository
import com.almaz.task1.ui.profile.dialogs.ChangeImageFragment
import com.almaz.task1.ui.profile.dialogs.ProfileImageDialogClickListener
import com.almaz.task1.ui.profile.dialogs.DontAskAgainFragment
import com.almaz.task1.ui.profile.dialogs.RationaleFragment

class ProfileFragment : Fragment(), ProfileImageDialogClickListener {

    private val camera =
        registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
            when (bitmap) {
                null -> showToast(R.string.canceled)
                else -> view?.findViewById<ImageView>(R.id.avatar_image)?.setImageBitmap(bitmap)
            }
        }

    private val cameraPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            when {
                granted -> camera.launch()
                !shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) -> {
                    showSettingsDialog()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_view_friends).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = FriendsAdapter(Repository.getFriends())
        }

        setFragmentResultListener(RATIONALE_KEY) { _, bundle ->
            val isWantToAllowAfterRationale = bundle.getBoolean(RESULT_KEY)
            if (isWantToAllowAfterRationale) {
                cameraPermission.launch(Manifest.permission.CAMERA)
            }
        }
        setFragmentResultListener(SETTINGS_KEY) { _, bundle ->
            val isWantToOpenSettings = bundle.getBoolean(RESULT_KEY)
            if (isWantToOpenSettings) {
                openSettings()
            }
        }

        view.findViewById<ImageView>(R.id.avatar_image).setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        ChangeImageFragment().show(childFragmentManager, CHANGE_PHOTO_FRAGMENT_TAG)
    }

    private fun showToast(textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }

    private fun showRationaleDialog() {
        RationaleFragment().show(parentFragmentManager, RationaleFragment.TAG)
    }

    private fun showSettingsDialog() {
        DontAskAgainFragment().show(parentFragmentManager, DontAskAgainFragment.TAG)
    }

    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            .setData(Uri.fromParts("package", requireContext().packageName, null))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun takePhoto() {
        when (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
            true -> showRationaleDialog()
            false -> cameraPermission.launch(Manifest.permission.CAMERA)
        }
    }

    override fun deletePhoto() {
        view?.findViewById<ImageView>(R.id.avatar_image)?.setImageResource(R.drawable.ic_no_image)
    }

    companion object {
        const val CHANGE_PHOTO_FRAGMENT_TAG = "change_photo_fragment_tag"
        const val RATIONALE_KEY = "rationale_tag"
        const val SETTINGS_KEY = "settings_tag"
        const val RESULT_KEY = "camera_result_key"
    }
}
