package com.bluelinelabs.logansquare.demo.network.parsetasks;

import com.github.aurae.retrofit2.LoganSquareConverterFactory;
import retrofit2.Retrofit;

public class LoganSquareNetworkParser extends NetworkParser {
  public LoganSquareNetworkParser(ParseListener parseListener, String jsonString) {
    super(parseListener, jsonString);
  }

  @Override Retrofit.Builder setupRetrofit(Retrofit.Builder builder) {
    return builder.addConverterFactory(LoganSquareConverterFactory.create());
  }
}
