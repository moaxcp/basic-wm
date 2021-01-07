package basic.wm;

import com.github.moaxcp.x11client.protocol.XEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import javax.inject.Singleton;

@Singleton
public class WindowManager {
  @EventListener
  void event(XEvent event) {

  }
}
