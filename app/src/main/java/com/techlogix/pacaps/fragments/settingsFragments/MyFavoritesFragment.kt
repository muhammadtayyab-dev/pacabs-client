package com.techlogix.pacaps.fragments.settingsFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.techlogix.pacaps.R
import com.techlogix.pacaps.activities.SettingsActivity
import com.techlogix.pacaps.adapters.ActivityRecyclerAdapterGeneric
import com.techlogix.pacaps.models.favoritesModels.MyFavoritesModel
import com.techlogix.pacaps.utility.GenericCallback
import com.techlogix.pacaps.utility.Utility
import kotlinx.android.synthetic.main.fragment_my_favorites.*

class MyFavoritesFragment : Fragment(),GenericCallback<Any> {


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
    }

    private fun initViews(view: View) {
        (requireActivity() as SettingsActivity).setText("Settings-Favorites")
        val myFavArray= arrayListOf<MyFavoritesModel>()
        myFavArray.add(MyFavoritesModel(R.drawable.home_bg,requireContext().getString(R.string.dummu_loc),"Home"))
        myFavArray.add(MyFavoritesModel(R.drawable.office_bg,requireContext().getString(R.string.dummu_loc),"Office"))
        MyFavoritesRecycler.adapter=ActivityRecyclerAdapterGeneric(Utility.MY_FAVORITES,myFavArray,this)
    }

    override fun GenericCallType(T: Any) {
        TODO("Not yet implemented")
    }


}