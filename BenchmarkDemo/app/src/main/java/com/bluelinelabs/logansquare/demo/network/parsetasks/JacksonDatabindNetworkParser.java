package com.bluelinelabs.logansquare.demo.network.parsetasks;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class JacksonDatabindNetworkParser extends NetworkParser {
  public JacksonDatabindNetworkParser(ParseListener parseListener, String jsonString) {
    super(parseListener, jsonString);
  }

  @Override Retrofit.Builder setupRetrofit(Retrofit.Builder builder) {
    return builder.addConverterFactory(JacksonConverterFactory.create());
  }
}
