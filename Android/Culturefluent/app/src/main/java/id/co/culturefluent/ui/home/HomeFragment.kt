package id.co.culturefluent.ui.home

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import id.co.culturefluent.R
import id.co.culturefluent.databinding.FragmentHomeBinding
import id.co.culturefluent.utils.ImageViewHelper
import id.co.culturefluent.utils.autoCleared

class HomeFragment : Fragment() {
    private var binding :FragmentHomeBinding by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        ImageViewHelper.placeImage(BitmapFactory.decodeResource(resources,R.drawable.bg_home),binding.ivBackground)
        binding.btnClassifyobj.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_classifyobjectFragment)
        }
        binding.btnEncyclopedia.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_encyFragment)
        }
        binding.btnInfo.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment)
        }


    }


}