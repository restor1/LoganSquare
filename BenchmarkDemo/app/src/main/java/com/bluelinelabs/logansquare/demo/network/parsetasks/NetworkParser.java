package com.bluelinelabs.logansquare.demo.network.parsetasks;

import android.util.Log;
import com.bluelinelabs.logansquare.demo.network.BackendService;
import com.bluelinelabs.logansquare.demo.parsetasks.ParseResult;
import com.bluelinelabs.logansquare.demo.parsetasks.Parser;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Retrofit;

public abstract class NetworkParser extends Parser {

  private final Retrofit retrofit;
  private final MockWebServer server;
  private final BackendService backendService;

  protected NetworkParser(ParseListener parseListener, String jsonString) {
    super(parseListener, jsonString);
    final Retrofit.Builder builder = new Retrofit.Builder();
    server = new MockWebServer();
    server.setDispatcher(new Dispatcher() {
      @Override public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        if (request.getPath().equals("/")){
          return new MockResponse()
              .addHeader("Content-Type", "application/json; charset=utf-8")
              .addHeader("Cache-Control", "no-cache")
              .setResponseCode(200)
              .setBody(mJsonString);
        }

        return new MockResponse().setResponseCode(404);
      }
    });
    HttpUrl baseURL = server.url("/");
    retrofit = setupRetrofit(setupRetrofit(builder.baseUrl(baseURL))).build();
    backendService = retrofit.create(BackendService.class);
  }

  abstract Retrofit.Builder setupRetrofit(Retrofit.Builder builder);

  @Override protected int parse(String json) {
    try {
      return backendService.users().execute().body().users.size();
    } catch (IOException e) {
      Log.e("NetworkParser", "error while executing task", e);
      return 0;
    }
  }

  @Override
  protected void onPostExecute(ParseResult parseResult) {
    try {
      server.shutdown();
    } catch (IOException e) {
      Log.e("NetworkParser", "error while shutting down mock web server", e);
    } finally {
      super.onPostExecute(parseResult);
    }
  }
}
