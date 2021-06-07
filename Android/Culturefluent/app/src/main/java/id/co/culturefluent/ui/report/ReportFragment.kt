package id.co.culturefluent.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.culturefluent.R
import id.co.culturefluent.databinding.FragmentReportBinding
import id.co.culturefluent.ui.detail.DetailFragment
import id.co.culturefluent.utils.ImageViewHelper
import id.co.culturefluent.utils.Resource
import id.co.culturefluent.utils.SnackBin
import id.co.culturefluent.utils.autoCleared
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@AndroidEntryPoint
class ReportFragment : Fragment() {
    private var binding: FragmentReportBinding by autoCleared()
    private val viewModel: ReportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val name = arguments?.getString(DetailFragment.INSTRUMENT)?:""
        val image = arguments?.getString(DetailFragment.IMAGEPATH)?:""
        Glide.with(requireContext())
            .load(image)
            .placeholder(R.drawable.bg_gray_curve)
            .into(binding.ivInstrument)
//        binding.tvDesc.text="Report that this is not “$name”,\nThis musical instrument is named..."
        binding.tvDesc.text="Laporkan bahwa ini bukan “$name”, \nAlat musik ini bernama..."
        binding.btnReport.setOnClickListener {
            if (!binding.tieName.text.isNullOrBlank()) {
//                SnackBin.show(binding.root,"Thank you for your report :)")
                report(image, name)
            } else {
//                SnackBin.show(binding.root,"Please fill in the correct name of the musical instrument!")
                SnackBin.show(binding.root, "Isikan nama alat musik yang benar!")
            }
        }
        binding.ivInstrument.setImageBitmap(ImageViewHelper.savedImage)
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }

        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    binding.ivLoading.visibility = View.GONE
                    SnackBin.show(binding.root, "Terimakasih :)")
                    findNavController().navigateUp()
                }
                Resource.Status.ERROR -> {
                    requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    binding.ivLoading.visibility = View.GONE
                    SnackBin.show(binding.root, "Terimakasih :)")
                    findNavController().navigateUp()
                }

                Resource.Status.LOADING -> {
                    loadingState()
                }
            }
        })

    }

    private fun report(image: String, name: String) {
//        val requestFile = RequestBody.create(
//            MediaType.parse("application/octet"),
//            File(image)
//        )
//        val img = MultipartBody.Part.createFormData("img", File(image).name, requestFile)
//
//        val name = RequestBody.create(MediaType.parse("multipart/form-data"), name)

        val part: HashMap<String, RequestBody> = HashMap()
        val requestFile = RequestBody.create(
            MediaType.parse("application/octet"),
            File(image)
        )
        val name = RequestBody.create(MediaType.parse("text/plain"), name)

        part["img"] = requestFile
        part["name"] = name

        viewModel.report(part)
    }

    private fun loadingState(){
        binding.ivLoading.visibility = View.VISIBLE
        requireActivity().window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
}