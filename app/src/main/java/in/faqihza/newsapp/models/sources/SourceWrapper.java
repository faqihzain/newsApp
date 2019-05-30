package in.faqihza.newsapp.models.sources;

import com.squareup.moshi.Json;

import java.util.List;

public class SourceWrapper {

    @Json(name = "status")
    private String status;
    @Json(name = "sources")
    private List<Source> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }

}
