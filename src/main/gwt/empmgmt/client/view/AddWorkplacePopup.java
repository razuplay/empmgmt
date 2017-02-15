package empmgmt.client.view;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextItem;
import empmgmt.client.lang.EmployeeConstants;

public class AddWorkplacePopup extends Window {

	private EmployeeConstants constants = GWT.create(EmployeeConstants.class);

	private TextItem nameItem;

	public AddWorkplacePopup() {
		setTitle(constants.addWorkplace());
		setShowMinimizeButton(false);
		setIsModal(true);
		setShowModalMask(true);
		setAutoSize(true);
		centerInPage();

		DynamicForm form = new DynamicForm();
		form.setWidth100();
		form.setHeight100();
		nameItem = new TextItem(constants.name());
		form.setFields(nameItem);
		form.setPadding(5);

		addItem(form);

		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent closeClickEvent) {
				destroy();
			}
		});
	}

	public TextItem getNameItem() {
		return nameItem;
	}
}
