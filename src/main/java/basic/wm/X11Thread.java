package basic.wm;

import com.github.moaxcp.x11client.X11Client;
import com.github.moaxcp.x11client.protocol.XEvent;
import io.micronaut.context.event.ApplicationEventPublisher;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class X11Thread {
  private volatile boolean running;
  private final ConcurrentLinkedQueue<Consumer<X11Client>> actions = new ConcurrentLinkedQueue<>();
  @Inject
  private ApplicationEventPublisher eventPublisher;
  @Inject
  private X11Client client;

  public void start() {
    running = true;
    while(running) {
      performActions();
      XEvent event = client.getNextEvent();
      eventPublisher.publishEvent(event);
    }
  }

  private void performActions() {
    Consumer<X11Client> action = actions.poll();
    while(action != null) {
      action.accept(client);
      action = actions.poll();
    }
  }

  public void addAction(Consumer<X11Client> action) {
    actions.add(action);
  }

  public void stop() {
    running = false;
  }
}
