package com.pax.us.pay.ui.core;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

interface IActionHandler {

    void sendNext(@Nullable Bundle bundle);

    void sendAbort();

    void sendPrev();

    void setSecurityArea(@NonNull Bundle bundle);
}
