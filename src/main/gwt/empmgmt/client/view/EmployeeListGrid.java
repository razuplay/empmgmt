package empmgmt.client.view;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import empmgmt.client.ds.EmployeeDataSource;

public class EmployeeListGrid extends ListGrid {

	public EmployeeListGrid() {
		setWidth100();
		setHeight100();
		setCanEdit(true);
		setCanRemoveRecords(false);
		setFields(
				new ListGridField(EmployeeDataSource.FIELD_NAME),
				new ListGridField(EmployeeDataSource.FIELD_ADDRESS),
				new ListGridField(EmployeeDataSource.FIELD_EMAIL),
				new ListGridField(EmployeeDataSource.FIELD_PHONE),
				new ListGridField(EmployeeDataSource.FIELD_WORKPLACES)
		);
	}
}
