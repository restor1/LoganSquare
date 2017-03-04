package com.bluelinelabs.logansquare.demo.parsetasks;

import com.bluelinelabs.logansquare.demo.model.Response;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class MoshiParser extends Parser {

    private final JsonAdapter<Response> adapter;

    public MoshiParser(Parser.ParseListener parseListener, String jsonString, Moshi moshi) {
        super(parseListener, jsonString);
        adapter = moshi.adapter(Response.class);
    }

    @Override
    protected int parse(String json) {
        try {
            return adapter.fromJson(json).users.size();
        } catch (Exception e) {
            return 0;
        } finally {
            System.gc();
        }
    }

}
