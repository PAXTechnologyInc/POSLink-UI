package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by Kim.L on 3/16/2018.
 */
public class StringListPreference extends ListPreferenceFix {

    public StringListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public StringListPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringListPreference(Context context) {
        this(context, null);
    }
}
