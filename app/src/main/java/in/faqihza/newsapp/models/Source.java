package in.faqihza.newsapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Source implements Parcelable {

    @Json(name = "id")
    private String id;
    @Json(name = "name")
    private String name;

    public final static Parcelable.Creator<Source> CREATOR = new Creator<Source>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Source createFromParcel(Parcel in) {
            return new Source(in);
        }

        public Source[] newArray(int size) {
            return (new Source[size]);
        }

    };

    protected Source(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }
}
