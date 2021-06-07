package id.co.culturefluent.ui.classifyobject

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.culturefluent.R
import id.co.culturefluent.databinding.FragmentClassifyobjectBinding
import id.co.culturefluent.ui.detail.DetailFragment
import id.co.culturefluent.utils.Data
import id.co.culturefluent.utils.Resource
import id.co.culturefluent.utils.SnackBin
import id.co.culturefluent.utils.autoCleared
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ClassifyobjectFragment : Fragment() {

    private var binding: FragmentClassifyobjectBinding by autoCleared()
    private val viewModel: ClassifyViewModel by viewModels()
    private var instrumentName: String = ""
    private val REQUEST_TAKE_PHOTO = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentClassifyobjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .load(R.drawable.loading)
            .into(binding.ivLoading)
        noImage()
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }
            clBottombar.setOnClickListener {
                findNavController().navigate(
                    R.id.action_classifyobjectFragment_to_detailFragment, bundleOf(
                        DetailFragment.INSTRUMENT to instrumentName,
                        DetailFragment.IMAGEPATH  to viewModel.currentPhotoPath
                    )
                )
            }
            btnCam.setOnClickListener { dispatchTakePictureIntent() }
//            btnGallery.setOnClickListener { chooseImage() }
        }

        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    val i: List<Double> = it.data!![0]
                    applyData(i)
                }
                Resource.Status.ERROR -> {
                    noImage()
                    SnackBin.show(binding.root, "Terjadi kesalahan.")
                }

                Resource.Status.LOADING -> {
                    loadingState()
                }
            }
        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            predictImage()
            Glide.with(requireContext())
                .load(viewModel.currentPhotoPath)
                .into(binding.ivInstrument)
        }
    }

    private fun noImage() {
        binding.svInstrument.visibility = View.GONE
        binding.clBottombar.visibility = View.GONE
        binding.bgNoimage.visibility = View.VISIBLE
    }

    private fun loadingState() {
        binding.apply {
            clBottombar.isEnabled = false
            clBottombar.isFocusable = false
            svInstrument.visibility = View.VISIBLE
            clBottombar.visibility = View.VISIBLE
            bgNoimage.visibility = View.GONE
            tvFirst.text = ""
            tvSecond.text = ""
            tvThird.text = ""
            tvPercent1.text = ""
            tvPercent2.text = ""
            tvPercent3.text = ""
            btnDetail.visibility = View.GONE
            ivLoading.visibility = View.VISIBLE
            tvLoading.visibility = View.VISIBLE
        }
    }


    fun Double.format(digits: Int) = "%.${digits}f".format(this)

    private fun applyData(list: List<Double>) {
        val catnprobs = Data.getListCategory(list)
        val sortedCatnprobs = catnprobs.sortedWith(compareBy { it.prob * -1 })
        instrumentName = sortedCatnprobs[0].name
        binding.apply {
            clBottombar.isEnabled = true
            clBottombar.isFocusable = true
            svInstrument.visibility = View.VISIBLE
            clBottombar.visibility = View.VISIBLE
            bgNoimage.visibility = View.GONE
            tvFirst.text = sortedCatnprobs[0].name
            tvSecond.text = sortedCatnprobs[1].name
            tvThird.text = sortedCatnprobs[2].name
            tvPercent1.text = (sortedCatnprobs[0].prob * 100).format(2) + "%"
            tvPercent2.text = (sortedCatnprobs[1].prob * 100).format(2) + "%"
            tvPercent3.text = (sortedCatnprobs[2].prob * 100).format(2) + "%"
            btnDetail.visibility = View.VISIBLE
            ivLoading.visibility = View.GONE
            tvLoading.visibility = View.GONE
        }
        Glide.with(requireContext())
            .load(viewModel.currentPhotoPath)
            .into(binding.ivInstrument);
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            viewModel.currentPhotoPath = absolutePath
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                // Create the File where the photo should go
                var photoFile: File?
                try {
                    photoFile = createImageFile()
                } catch (ex: IOException) {
                    photoFile = null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "id.co.culturefluent.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
                }
            }
        }
    }

    private fun predictImage() {
        val requestBody = RequestBody.create(
            MediaType.parse("application/octet"),
            File(viewModel.currentPhotoPath)
        )
        viewModel.predict(requestBody!!)

//        val requestBody : RequestBody = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            RequestBody.create(MediaType.parse("application/octet"), Files.readAllBytes(Uri.parse(currentPhotoPath).path))
//        }else{
//            RequestBody.create(MediaType.parse("application/octet"), imageFile)
//        }

//        var requestBody: RequestBody? = null
//        try {
//            val `in`: InputStream = FileInputStream(File(currentPhotoPath))
//            val buf = ByteArray(`in`.available())
//            while (`in`.read(buf) != -1){
//                Log.d("classify activity","read")};
//            requestBody = RequestBody
//                .create(MediaType.parse("application/octet-stream"), buf)
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }

    }






//    private fun chooseImage() {
//        val photoPickerIntent = Intent(
//            Intent.ACTION_PICK,
//            MediaStore.Images.Media.INTERNAL_CONTENT_URI
//        )
////        photoPickerIntent.type = "image/*"
//        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
//    }

    //    private fun dispatchTakePictureIntent() {
//        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
//                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
//            }
//        }
//    }
//          Load Image from intent result pick
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            ImageViewHelper.savedImage = imageBitmap
//            binding.ivInstrument.setImageBitmap(imageBitmap)
//            binding.ivInstrument.setImageBitmap(imageBitmap)

//          Load Image from intent result choose
//    if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK){
//        try {
//            val imageUri: Uri = data?.data!!
//            val imageStream: InputStream = requireContext().contentResolver.openInputStream(
//                imageUri
//            )!!
//            val imageBitmap = BitmapFactory.decodeStream(imageStream)
//            ImageViewHelper.savedImage = imageBitmap
//            binding.ivInstrument.setImageBitmap(imageBitmap)
//            binding.svInstrument.visibility = View.VISIBLE
//            binding.clBottombar.visibility = View.VISIBLE
//            binding.bgNoimage.visibility = View.GONE
//            binding.tvNoimage.visibility = View.GONE
//            loadingState()
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
////                Toast.makeText(this@PostImage, "Something went wrong", Toast.LENGTH_LONG).show()
//        }
//    }
}