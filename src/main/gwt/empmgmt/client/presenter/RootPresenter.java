package empmgmt.client.presenter;

import com.mvp4g.client.annotation.Presenter;
import com.mvp4g.client.presenter.BasePresenter;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.RPCTransport;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import empmgmt.client.RootEventBus;
import empmgmt.client.ds.EmployeeDataSource;
import empmgmt.client.view.AddWorkplacePopup;
import empmgmt.client.view.RootView;

import java.util.HashMap;
import java.util.Map;

@Presenter(name = "rootPresenter", view = RootView.class)
public class RootPresenter extends BasePresenter<RootView, RootEventBus> {

	@Override
	public void bind() {
		view.getAddButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				view.getGrid().startEditingNew();
			}
		});

		view.getAddWorkplaceButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				final AddWorkplacePopup popup = new AddWorkplacePopup();
				popup.getNameItem().addKeyPressHandler(new KeyPressHandler() {
					@Override
					public void onKeyPress(KeyPressEvent keyPressEvent) {
						if ("Enter".equals(keyPressEvent.getKeyName()) && keyPressEvent.getItem().getValue() != null) {
							ListGridRecord record = view.getGrid().getSelectedRecord();
							Map<String, Object> params = new HashMap<>();
							params.put(EmployeeDataSource.PARAM_WORKPLACE, keyPressEvent.getItem().getValue());
							RPCRequest req = new RPCRequest();
							req.setActionURL(EmployeeDataSource.ADD_WORKPLACE_URL + "/" + record.getAttribute(EmployeeDataSource.FIELD_ID));
							req.setTransport(RPCTransport.XMLHTTPREQUEST);
							req.setParams(params);
							RPCManager.sendRequest(req, new RPCCallback() {
								@Override
								public void execute(RPCResponse rpcResponse, Object o, RPCRequest rpcRequest) {
									popup.destroy();
									fetchData();
								}
							});
						}
					}
				});
				popup.show();
			}
		});

		view.getRefreshButton().addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent clickEvent) {
				fetchData();
			}
		});


		view.getGrid().addSelectionChangedHandler(new SelectionChangedHandler() {
			@Override
			public void onSelectionChanged(SelectionEvent selectionEvent) {
				view.getAddWorkplaceButton().setDisabled(selectionEvent.getSelectedRecord() == null);
			}
		});

		view.getGrid().setDataSource(new EmployeeDataSource());
		fetchData();
	}

	public void fetchData() {
		view.getAddWorkplaceButton().setDisabled(true);
		view.getGrid().fetchData(new Criteria("timestamp", "" + System.currentTimeMillis()));
	}
}
