package id.co.culturefluent.ui.detail

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import id.co.culturefluent.R
import id.co.culturefluent.data.InstrumentModel
import id.co.culturefluent.databinding.FragmentClassifyobjectBinding
import id.co.culturefluent.databinding.FragmentDetailBinding
import id.co.culturefluent.utils.Data
import id.co.culturefluent.utils.ImageViewHelper
import id.co.culturefluent.utils.SnackBin
import id.co.culturefluent.utils.autoCleared

class DetailFragment : Fragment() {

    companion object{
        const val INSTRUMENT = "INSTRUMENT"
        const val FROMENCY = "FROMENCY"
        const val IMAGEPATH = "IMAGEPATH"
    }
    private var binding : FragmentDetailBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var itemName =""
        val fromEncy : Boolean = arguments?.getBoolean(FROMENCY)?:false
        val item : InstrumentModel = if(fromEncy){
            arguments?.getParcelable(INSTRUMENT)!!
        }else{
            itemName = arguments?.getString(INSTRUMENT).toString()
            Data.getInstrumentByName(itemName)
        }
        binding.apply {
            tvInstrument.text = item.name
            tvDesc.text =item.desc
            tvDescQuiz.text = item.question

            rA.text = item.choices[0]
            rB.text = item.choices[1]
            rC.text = item.choices[2]
            rD.text = item.choices[3]
            btnExamine.setOnClickListener {
                when(item.rightChoice){
                    0->{if(rA.isChecked){examine(true)}else examine(false)}
                    1->{if(rB.isChecked){examine(true)}else examine(false)}
                    2->{if(rC.isChecked){examine(true)}else examine(false)}
                    3->{if(rD.isChecked){examine(true)}else examine(false)}
                }
            }
            btnReport.setOnClickListener {
                findNavController().navigate(R.id.action_detailFragment_to_reportFragment
                    , bundleOf(INSTRUMENT to itemName,
                        IMAGEPATH to arguments?.getString(IMAGEPATH))
                )
            }

            btnBack.setOnClickListener {
                findNavController().navigateUp()
            }

            if (fromEncy){
                Glide.with(requireContext())
                    .load(item.image)
                    .placeholder(R.drawable.bg_gray_curve)
                    .into(binding.ivInstrument)
                quizbar.visibility= View.GONE
                radioQuiz.visibility= View.GONE
                tvDescQuiz.visibility= View.GONE
                iv2.visibility= View.GONE
                tvQuiz.visibility= View.GONE
                btnReport.visibility= View.GONE
                btnExamine.visibility= View.GONE
            }else{
                Glide.with(requireContext())
                    .load(arguments?.getString(IMAGEPATH))
                    .placeholder(R.drawable.bg_gray_curve)
                    .into(binding.ivInstrument)
            }
        }
    }

    private fun examine(answered:Boolean){
        if(answered){
            SnackBin.show(binding.root,"Benar :)")
        }else{
            SnackBin.show(binding.root,"Salah :(")
        }
    }
}