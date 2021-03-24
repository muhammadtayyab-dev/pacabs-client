package com.techlogix.pacaps.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.techlogix.pacaps.R
import com.techlogix.pacaps.models.cabAndDriverInformationModels.CabDetailsInformationModel
import com.techlogix.pacaps.models.NavMenuModel
import com.techlogix.pacaps.models.favoritesModels.MyFavoritesModel
import com.techlogix.pacaps.models.offerAndDisountModels.OffersAndDiscoutModel
import com.techlogix.pacaps.models.rides.MyRidesModel
import com.techlogix.pacaps.utility.GenericCallback
import com.techlogix.pacaps.utility.Utility

class ActivityRecyclerAdapterGeneric<T>(var type: Int,
                                        var arraylist: ArrayList<T>,
                                        var genericCall: GenericCallback<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (type == Utility.NAV_ITEMS) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.drawer_items_layout, parent, false)
            return MyNavItemsHolder(view)
        } else if (type == Utility.MY_RIDES) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.my_rides_item_layout, parent, false)
            return MyRidesHolder(view);
        } else if (type == Utility.OFFERS_DISCOUNT) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.offer_discounts_items_layout, parent, false)
            return MyOffersAndDiscountsHolder(view);
        }  else if (type == Utility.MY_FAVORITES) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.favorites_items_layout, parent, false)
            return MyFavoritesHolder(view);
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dashboard_list_header_layout, parent, false)
            return MyNavItemsHolder(view);
        }
    }

    override fun getItemCount(): Int {
        return arraylist.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var obj = arraylist.get(holder.adapterPosition)
        if (holder is MyNavItemsHolder && obj is NavMenuModel) {
            when (obj.isSelected) {
                true -> holder.rootLayout.setBackgroundColor(ContextCompat.getColor(holder.iconImg.context,
                    R.color.gray_div_dark))

                false -> holder.rootLayout.setBackgroundColor(ContextCompat.getColor(holder.iconImg.context,
                    R.color.transparent))
            }
            holder.iconImg.setImageResource(obj.icon)
            holder.titleTv.text = obj.value
            holder.rootLayout.setOnClickListener {
                genericCall.GenericCallType(holder.adapterPosition)
                setSelection(obj.value)
            }
        } else if (holder is MyRidesHolder && obj is MyRidesModel) {
            holder.timeDateTv.text = obj.data
            holder.rideStatusTv.text = obj.rideStatus
            holder.paymentTv.text = obj.totalPayment
            holder.paymentTypeTv.text = obj.paymentType
            holder.startAddressTv.text = obj.startingAddress
            holder.endAddressTv.text = obj.endingAddress
        } else if (holder is MyOffersAndDiscountsHolder && obj is OffersAndDiscoutModel) {
            holder.offerTv.text = obj.discountedValue
            holder.valideValueTv.text = obj.availabeRides
            holder.valideRsTv.text = obj.discountedPrice
            holder.oldRsTv.text = obj.originalPrice
            holder.oldRsTv.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG)
        }else if (holder is MyFavoritesHolder && obj is MyFavoritesModel) {
            holder.profileImg.setImageResource(obj.icon)
            holder.addresTv.text = obj.address
            holder.addressTypeTv.text = obj.addressType

        }
    }


    class MyNavItemsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImg = itemView.findViewById(R.id.iconImg) as ImageView
        val titleTv = itemView.findViewById(R.id.titleTv) as TextView
        val rootLayout = itemView.findViewById(R.id.rootLayout) as ConstraintLayout
        val div = itemView.findViewById(R.id.div) as View


    }

    class MyRidesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val timeDateTv = itemView.findViewById(R.id.timeDateTv) as TextView
        val paymentTv = itemView.findViewById(R.id.paymentTv) as TextView
        val rideStatusTv = itemView.findViewById(R.id.rideStatusTv) as TextView
        val paymentTypeTv = itemView.findViewById(R.id.paymentTypeTv) as TextView
        val startAddressTv = itemView.findViewById(R.id.startAddressTv) as TextView
        val endAddressTv = itemView.findViewById(R.id.endAddressTv) as TextView

    }

    class MyOffersAndDiscountsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val offerTv = itemView.findViewById(R.id.offerTv) as TextView
        val valideValueTv = itemView.findViewById(R.id.valideValueTv) as TextView
        val valideRsTv = itemView.findViewById(R.id.valideRsTv) as TextView
        val oldRsTv = itemView.findViewById(R.id.oldRsTv) as TextView

    }

    class MyFavoritesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg = itemView.findViewById(R.id.profileImg) as ImageView
        val addresTv = itemView.findViewById(R.id.addresTv) as TextView
        val addressTypeTv = itemView.findViewById(R.id.addressTypeTv) as TextView
        val deleteImg = itemView.findViewById(R.id.deleteImg) as ImageView
    }

    fun setSelection(itemToBeSelected: String) {
        for (item in arraylist) {
            if (item is NavMenuModel) {
                item.isSelected = item.value.equals(itemToBeSelected)
            } else if (item is CabDetailsInformationModel) {
                item.isSelected = item.cabType.equals(itemToBeSelected)
            }
        }
        notifyDataSetChanged()
    }


}