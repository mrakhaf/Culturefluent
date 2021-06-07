package id.co.culturefluent.ui.ency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.culturefluent.data.InstrumentModel
import id.co.culturefluent.databinding.ItemInstrumentBinding

class InstrumentAdapter(
    private val listener: InstrumentAdapter.EncyListener,
    private val listInstrument: List<InstrumentModel>
) : RecyclerView.Adapter<InstrumentAdapter.ViewHolder>() {


    interface EncyListener {
        fun onClick(instrument: InstrumentModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemInstrumentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(listInstrument[position])

    override fun getItemCount(): Int = listInstrument.size

    class ViewHolder(
        private val itemBinding: ItemInstrumentBinding,
        private val listener: EncyListener
    ) :
        RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

        init {
            itemBinding.root.setOnClickListener(this)
        }

        private lateinit var item: InstrumentModel

        @SuppressLint("SetTextI18n")
        fun bind(item: InstrumentModel) {
            this.item = item
            itemBinding.let {
                it.tvName.text = item.name
            }
        }

        override fun onClick(v: View?) {
            listener.onClick(item)
        }
    }
}