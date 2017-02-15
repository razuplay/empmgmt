package empmgmt.client.view;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import empmgmt.client.lang.EmployeeConstants;

public class RootView extends HLayout {

	private EmployeeListGrid grid;
	private Button addButton;
	private Button addWorkplaceButton;
	private Button refreshButton;

	private EmployeeConstants constants = GWT.create(EmployeeConstants.class);

	public RootView() {
		setWidth(1024);
		setHeight(768);
		addButton = new Button(constants.newEmployee());
		addWorkplaceButton = new Button(constants.addWorkplace());
		refreshButton = new Button(constants.refresh());
		VLayout buttonsLayout = new VLayout();
		buttonsLayout.setMembers(addButton, addWorkplaceButton, refreshButton);

		grid = new EmployeeListGrid();
		setMembers(grid, buttonsLayout);
	}

	public EmployeeListGrid getGrid() {
		return grid;
	}

	public Button getAddButton() {
		return addButton;
	}

	public Button getAddWorkplaceButton() {
		return addWorkplaceButton;
	}

	public Button getRefreshButton() {
		return refreshButton;
	}
}
