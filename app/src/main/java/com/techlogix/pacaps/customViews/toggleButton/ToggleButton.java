package com.techlogix.pacaps.customViews.toggleButton;

import android.widget.Checkable;

public interface ToggleButton extends Checkable {

    void setOnCheckedChangeListener(OnCheckedChangeListener listener);
}

