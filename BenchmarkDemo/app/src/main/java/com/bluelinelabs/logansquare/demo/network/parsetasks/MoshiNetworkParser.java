package com.bluelinelabs.logansquare.demo.network.parsetasks;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class MoshiNetworkParser extends NetworkParser {
  public MoshiNetworkParser(ParseListener parseListener, String jsonString) {
    super(parseListener, jsonString);
  }

  @Override Retrofit.Builder setupRetrofit(Retrofit.Builder builder) {
    return builder.addConverterFactory(MoshiConverterFactory.create());
  }
}
