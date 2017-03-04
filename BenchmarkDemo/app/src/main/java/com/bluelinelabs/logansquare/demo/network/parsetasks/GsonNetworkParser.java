package com.bluelinelabs.logansquare.demo.network.parsetasks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GsonNetworkParser extends NetworkParser {
  public GsonNetworkParser(ParseListener parseListener, String jsonString) {
    super(parseListener, jsonString);
  }

  @Override Retrofit.Builder setupRetrofit(Retrofit.Builder builder) {
    return builder.addConverterFactory(GsonConverterFactory.create());
  }
}
