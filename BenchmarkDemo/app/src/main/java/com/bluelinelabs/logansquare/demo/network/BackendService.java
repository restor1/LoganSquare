package com.bluelinelabs.logansquare.demo.network;

import com.bluelinelabs.logansquare.demo.model.Response;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BackendService {
  @GET("/") Call<Response> users();
}
