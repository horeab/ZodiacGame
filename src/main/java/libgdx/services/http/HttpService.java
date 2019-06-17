package libgdx.services.http;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;

public class HttpService {

    public void makePost() {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.GET);
        request.setUrl("http://libgdx.badlogicgames.com/nightlies/dist/AUTHORS");
        Gdx.app.log("HttpRequestExample", "response: ");
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse (Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpRequestExample", "response: " + httpResponse.getResultAsString());
            }

            @Override
            public void failed (Throwable t) {
                Gdx.app.error("HttpRequestExample", "something went wrong", t);
            }

            @Override
            public void cancelled () {
                Gdx.app.log("HttpRequestExample", "cancelled");
            }
        });
    }
}
