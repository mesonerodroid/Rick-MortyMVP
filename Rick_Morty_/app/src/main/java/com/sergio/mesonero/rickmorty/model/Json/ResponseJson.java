package com.sergio.mesonero.rickmorty.model.Json;

import java.util.List;

public class ResponseJson {


    private ResponseInfo info;
    private List<Person> results;

    public ResponseInfo getInfo() {
        return info;
    }

    public void setInfo(ResponseInfo info) {
        this.info = info;
    }

    public List<Person> getResults() {
        return results;
    }

    public void setResults(List<Person> results) {
        this.results = results;
    }
}
