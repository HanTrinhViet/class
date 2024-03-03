package net.braniumacademy.themovieapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName(value = "page")
    @Expose
    private Integer page;

    @SerializedName(value = "total_pages")
    @Expose
    private Integer totalPages;

    @SerializedName(value = "total_results")
    @Expose
    private Integer totalResults;

    @SerializedName(value = "results")
    @Expose
    private List<Movie> results = null;

    public Result() {
    }

    public Result(Integer page, Integer totalPages, Integer totalResults, List<Movie> results) {
        this.page = page;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
        this.results = results;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
