package com.techlogix.pacaps.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.techlogix.pacaps.R
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabDetailsInformationModel
import com.techlogix.pacaps.models.TimeInKmModel
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabsAndTheirFareResponseModel
import com.techlogix.pacaps.utility.GenericCallBackWithType
import com.techlogix.pacaps.utility.Utility

class ProviderAndCabsFragmentGeneinceRecyclerAdapter<T>(var genericArray: ArrayList<T>,
                                                        var type: Int,
                                                        var callBackWithType: GenericCallBackWithType<T>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var selectedItem: Any? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (type == Utility.PROVIDER_CAB_TIME_IN_KM_RECYCLER_TYPE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.time_in_km_item_layout, parent, false)
            return MyTimeInKmViewHolder(view)
        } else if (type == Utility.PROVIDER_CAB_CAR_TYPE_RECYCLER_TYPE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cabtype_items_layout, parent, false)
            return MyCabsHolderView(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cabtype_items_layout, parent, false)
            return DriverDetailsRecyclerAdapter.MyDriverHeardeHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return genericArray.size
    }

    public fun getArrayLisy(): ArrayList<T> {
        return genericArray;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val Gobject = genericArray.get(holder.adapterPosition)
        if (holder is MyTimeInKmViewHolder && Gobject is TimeInKmModel) {

            when (Gobject.isSelected) {
                true -> holder.rootLayout.setBackgroundResource(R.drawable.white_background_with_border_bg)
                false -> holder.rootLayout.setBackgroundResource(R.drawable.white_background_corner)
            }
            holder.timeInHrTv.text = Gobject.time
            holder.kmTv.text = Gobject.km
            holder.rootLayout.setOnClickListener {
                setSelection(Gobject.time)
                callBackWithType.returnCallback(Gobject, 1)
            }
        } else if (Gobject is CabsAndTheirFareResponseModel && holder is MyCabsHolderView) {
          /*  when (Gobject.isSelected) {
                true -> holder.rootLayout.setBackgroundResource(R.drawable.taxi_seleced_bg)
                false -> holder.rootLayout.background = null
            }
            holder.cabTypeTv.text = Gobject.cabType
            holder.cabDescTv.text = Gobject.cabDesc
            holder.cabArrivalTimeTv.text = Gobject.cabETArivalTime
            holder.cabFaretv.text = Gobject.cabFare
            holder.rootLayout.setOnClickListener {
                setSelection(Gobject.cabType)
                callBackWithType.returnCallback(Gobject, 1)
            }*/

            when (Gobject.isSelected) {
                true -> holder.rootLayout.setBackgroundResource(R.drawable.taxi_seleced_bg)
                false -> holder.rootLayout.background = null
            }

            holder.cabArrivalTimeTv.text = Gobject.basekm.toString() + "KM"
            holder.cabTypeTv.text = Gobject.vehiclecategory.category
            holder.cabDescTv.text = Gobject.triptype
            holder.cabFaretv.text = "₹" + Gobject.basefare
            holder.rootLayout.setOnClickListener {
                selectedItem=Gobject
                setSelection(Gobject.vehiclecategory.category)
                callBackWithType.returnCallback(Gobject, 1)

            }
        }
    }


    class MyTimeInKmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeInHrTv = itemView.findViewById(R.id.timeInHrTv) as TextView
        val kmTv = itemView.findViewById(R.id.kmTv) as TextView
        val rootLayout = itemView.findViewById(R.id.rootLayout) as LinearLayout

    }

    class MyCabsHolderView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cabTypeTv = itemView.findViewById(R.id.cabTypeTv) as TextView
        val cabDescTv = itemView.findViewById(R.id.cabDescTv) as TextView
        val cabFaretv = itemView.findViewById(R.id.cabFaretv) as TextView
        val cabArrivalTimeTv = itemView.findViewById(R.id.cabArrivalTimeTv) as TextView
        val rootLayout = itemView.findViewById(R.id.rootLayout) as ConstraintLayout
    }

    fun setSelection(itemToBeSelected: String) {
        for (item in genericArray) {
            if (item is TimeInKmModel) {
                item.isSelected = item.time.equals(itemToBeSelected)
            } else if (item is CabDetailsInformationModel) {
                item.isSelected = item.cabType.equals(itemToBeSelected)
            } else if (item is CabsAndTheirFareResponseModel) {
                item.isSelected = item.vehiclecategory.category.equals(itemToBeSelected)
            }
        }
        notifyDataSetChanged()
    }

    fun getSelectedItem(): Any? {
        return selectedItem
    }
}