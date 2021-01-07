package basic.wm;

import com.github.moaxcp.x11client.X11Client;
import io.micronaut.context.annotation.Factory;
import javax.inject.Singleton;

@Factory
public class X11ClientFactory {
  @Singleton
  public X11Client client() {
    return X11Client.connect();
  }
}
