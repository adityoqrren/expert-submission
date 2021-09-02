package id.interconnect.gamestar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.dynamicfeatures.fragment.ui.AbstractProgressFragment
import id.interconnect.gamestar.databinding.FragmentProgressBinding

class ProgressFragment : AbstractProgressFragment(R.layout.fragment_progress) {
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCancelled() {
        binding.textBelowProgressbar.text = getString(R.string.installation_cancelled)
    }

    override fun onFailed(errorCode: Int) {
        binding.textBelowProgressbar.text = getString(R.string.installation_failed)
    }

    override fun onProgress(status: Int, bytesDownloaded: Long, bytesTotal: Long) {
        binding.progressbarLoading.progress =
            (bytesDownloaded.toDouble() * 100 / bytesTotal).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

