package in.faqihza.newsapp.models;

import com.squareup.moshi.Json;

import java.util.List;

public class NewsWrapper {
        @Json(name = "status")
        private String status;
        @Json(name = "totalResults")
        private Integer totalResults;
        @Json(name = "articles")
        private List<Article> articles = null;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public List<Article> getArticles() {
            return articles;
        }

        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
}
