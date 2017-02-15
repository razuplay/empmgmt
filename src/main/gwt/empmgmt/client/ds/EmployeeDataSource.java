package empmgmt.client.ds;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import empmgmt.client.lang.EmployeeConstants;

import java.util.Map;

public class EmployeeDataSource extends RestDataSource {

	public static final String FETCH_URL = "employee/list";
	public static final String ADD_URL = "employee/create";
	public static final String UPDATE_URL = "employee/update";
	public static final String ADD_WORKPLACE_URL = "employee/addWorkplace";

	public static final String PARAM_WORKPLACE = "workplace";

	public static final String FIELD_ID = "id";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_ADDRESS = "address";
	public static final String FIELD_EMAIL = "email";
	public static final String FIELD_PHONE = "phoneNumber";
	public static final String FIELD_WORKPLACES = "workplacesString";

	private EmployeeConstants constants = GWT.create(EmployeeConstants.class);

	public EmployeeDataSource() {
		setDataFormat(DSDataFormat.JSON);
		setFields(idField(), nameField(), addressField(), emailField(), phoneNumberField(), workplacesField());
		setOperationBindings(fetchBinding(), addBinding(), updateBinding());
		setJsonRecordXPath("/");
	}

	private DataSourceField idField() {
		DataSourceIntegerField idField = new DataSourceIntegerField(FIELD_ID);
		idField.setPrimaryKey(true);
		idField.setHidden(true);
		return idField;
	}

	private DataSourceField nameField() {
		DataSourceTextField field = new DataSourceTextField(FIELD_NAME, constants.name());
		field.setRequired(true);
		return field;
	}

	private DataSourceField addressField() {
		DataSourceTextField field = new DataSourceTextField(FIELD_ADDRESS, constants.address());
		field.setRequired(true);
		return field;
	}

	private DataSourceField emailField() {
		DataSourceTextField field = new DataSourceTextField(FIELD_EMAIL, constants.email());
		field.setRequired(false);
		return field;
	}

	private DataSourceField phoneNumberField() {
		DataSourceTextField field = new DataSourceTextField(FIELD_PHONE, constants.phoneNumber());
		field.setRequired(false);
		return field;
	}

	private DataSourceField workplacesField() {
		DataSourceTextField field = new DataSourceTextField(FIELD_WORKPLACES, constants.workplaces());
		field.setCanEdit(false);
		return field;
	}

	private OperationBinding fetchBinding() {
		OperationBinding fetch = new OperationBinding(DSOperationType.FETCH, FETCH_URL);
		fetch.setDataProtocol(DSProtocol.GETPARAMS);
		return fetch;
	}

	private OperationBinding addBinding() {
		OperationBinding save = new OperationBinding(DSOperationType.ADD, ADD_URL);
		save.setDataProtocol(DSProtocol.POSTPARAMS);
		return save;
	}

	private OperationBinding updateBinding() {
		OperationBinding update = new OperationBinding(DSOperationType.UPDATE, UPDATE_URL);
		update.setDataProtocol(DSProtocol.POSTPARAMS);
		return update;
	}

	@Override
	protected Object transformRequest(DSRequest dsRequest) {
		if (dsRequest.getOperationType() == DSOperationType.UPDATE) {
			Map data = dsRequest.getAttributeAsMap("data");
			dsRequest.setActionURL(UPDATE_URL + "/" + data.get(FIELD_ID));
		}
		return super.transformRequest(dsRequest);
	}
}
