package id.co.culturefluent.ui.ency

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.culturefluent.R
import id.co.culturefluent.data.InstrumentModel
import id.co.culturefluent.databinding.FragmentEncyBinding
import id.co.culturefluent.ui.detail.DetailFragment
import id.co.culturefluent.utils.Data
import id.co.culturefluent.utils.autoCleared

class EncyFragment : Fragment(),InstrumentAdapter.EncyListener {
    private var binding : FragmentEncyBinding by autoCleared()
    private lateinit var adapter: InstrumentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEncyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = InstrumentAdapter(this, Data.getInstrumentList())
        binding.rvInstrument.adapter = adapter
        binding.rvInstrument.layoutManager =LinearLayoutManager(requireContext())
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onClick(instrument: InstrumentModel) {
        findNavController().navigate(R.id.action_encyFragment_to_detailFragment,
            bundleOf(DetailFragment.INSTRUMENT to instrument,
                DetailFragment.FROMENCY to true
            )
        )
    }
}