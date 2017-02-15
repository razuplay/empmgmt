package empmgmt.client;

import com.mvp4g.client.annotation.Events;
import com.mvp4g.client.event.EventBus;
import empmgmt.client.presenter.RootPresenter;

@Events(startPresenter = RootPresenter.class)
public interface RootEventBus extends EventBus {

}
