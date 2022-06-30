package androidx.appcompat.widget;

import android.database.Cursor;
import android.widget.Filter;

/**
 * Created by Kim.L 2020/03/11
 * Inspired by {@link androidx.cursoradapter.widget.CursorAdapter}
 */
class CursorFilter extends Filter {
    CursorFilterClient mClient;

    CursorFilter(CursorFilterClient client) {
        this.mClient = client;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue) {
        return this.mClient.convertToString((Cursor) resultValue);
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        Cursor cursor = this.mClient.runQueryOnBackgroundThread(constraint);
        FilterResults results = new FilterResults();
        if (cursor != null) {
            results.count = cursor.getCount();
            results.values = cursor;
        } else {
            results.count = 0;
            results.values = null;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        Cursor oldCursor = this.mClient.getCursor();
        if (results.values != null && results.values != oldCursor) {
            this.mClient.changeCursor((Cursor) results.values);
        }

    }

    interface CursorFilterClient {
        CharSequence convertToString(Cursor var1);

        Cursor runQueryOnBackgroundThread(CharSequence var1);

        Cursor getCursor();

        void changeCursor(Cursor var1);
    }
}
