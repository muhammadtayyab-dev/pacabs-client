package com.techlogix.pacaps.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.ServcieProviderAndCapActivity
import com.techlogix.pacaps.customViews.CircleImageView
import com.techlogix.pacaps.models.NearestVehiclesModels.GetNearestAvailbleVehiclesResponseModel
import com.techlogix.pacaps.models.cabAndDriverInformationModels.DriverDetailModel
import com.techlogix.pacaps.utility.Utility

class DriverDetailsRecyclerAdapter(var driverList: ArrayList<GetNearestAvailbleVehiclesResponseModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == Utility.HEADER_TYPE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.dashboard_list_header_layout, parent, false)
            return MyDriverHeardeHolder(view)
        } else if (viewType == Utility.SERVICE_PROVIDER_TYPE) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.taxi_list_item, parent, false)
            return MyServicesProviderHolders(view)
        } else if (viewType == Utility.DRIVER_TYPE) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.drivers_list_items, parent, false)
            return MyDriversHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.taxi_list_item, parent, false)
            return MyServicesProviderHolders(view)
        }
    }

    override fun getItemCount(): Int {
        return driverList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyServicesProviderHolders) {
            holder.userNameTv.text =
                driverList.get(holder.adapterPosition).vehicle.get(0).drivername
            holder.distanceTv.text = " - " + driverList.get(holder.adapterPosition).dist
            holder.rateTv.text = driverList.get(holder.adapterPosition).rating.toString()
            holder.rateBar.rating = driverList.get(holder.adapterPosition).rating
            holder.serviceTypeTv.text =
                "Taxi-Service-" + Utility.getAddress(holder.serviceTypeTv.context,
                    driverList.get(holder.adapterPosition).vehicle.get(0).latitude,
                    driverList.get(holder.adapterPosition).vehicle.get(0).longitude)
                    .get(0).subLocality + "-" + Utility.getAddress(holder.serviceTypeTv.context,
                    driverList.get(holder.adapterPosition).vehicle.get(0).latitude,
                    driverList.get(holder.adapterPosition).vehicle.get(0).longitude).get(0).locality
            holder.idnavigateImg.setOnClickListener {
                holder.idnavigateImg.context.startActivity(Intent(holder.idnavigateImg.context,
                    ServcieProviderAndCapActivity::class.java).putExtra("driverInfo",
                    driverList.get(holder.adapterPosition)))
            }
        } else if (holder is MyDriversHolder) {

        }
    }

    override fun getItemViewType(position: Int): Int {
        return driverList.get(position).type
    }


    class MyServicesProviderHolders(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userNameTv = itemView.findViewById(R.id.userNameTv) as TextView
        var rateTv = itemView.findViewById(R.id.rateTv) as TextView
        var distanceTv = itemView.findViewById(R.id.distanceTv) as TextView
        var serviceTypeTv = itemView.findViewById(R.id.serviceTypeTv) as TextView
        var rateBar = itemView.findViewById(R.id.rateBar) as RatingBar
        var idnavigateImg = itemView.findViewById(R.id.idnavigateImg) as ImageView
    }


    class MyDriversHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var driverProfileImg = itemView.findViewById(R.id.driverProfileImg) as CircleImageView
        var driverNameTv = itemView.findViewById(R.id.driverNameTv) as TextView
        var driverRating = itemView.findViewById(R.id.driverRating) as RatingBar
        var numberRidesTv = itemView.findViewById(R.id.numberRidesTv) as TextView
        var idnavigateImg = itemView.findViewById(R.id.idnavigateImg) as ImageView
    }

    class MyDriverHeardeHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}