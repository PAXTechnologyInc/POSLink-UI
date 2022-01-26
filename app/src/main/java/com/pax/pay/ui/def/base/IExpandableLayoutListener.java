package com.pax.pay.ui.def.base;

import androidx.fragment.app.Fragment;

// TODO Kim.L
//  bitmap receipt implementation workaround,
//  currently bitmap will be draw on receipt fragment created on different activities which means screen refresh,
//  a better solution is use a "global" activity with 2 fragments,
//  the left fragment will stay on screen while updating the right fragment
public interface IExpandableLayoutListener {
    Fragment onCreateExpandedPane();
}
