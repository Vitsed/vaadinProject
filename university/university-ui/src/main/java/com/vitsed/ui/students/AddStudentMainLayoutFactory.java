package com.vitsed.ui.students;

import javax.swing.ButtonModel;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vitsed.model.entity.Student;
import com.vitsed.utils.Gender;
import com.vitsed.utils.StudentsStringUtils;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

	private class AddStudentMainLayout extends VerticalLayout implements Button.ClickListener {

		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox gender;
		private Button saveButton;
		private Button clearButton;

		private BeanFieldGroup<Student> fieldGroup;
		private Student student;

		public AddStudentMainLayout init() {

			fieldGroup = new BeanFieldGroup<Student>(Student.class);
			student = new Student();

			firstName = new TextField(StudentsStringUtils.FIRST_NAME.getString());
			lastName = new TextField(StudentsStringUtils.LAST_NAME.getString());
			age = new TextField(StudentsStringUtils.AGE.getString());
			gender = new ComboBox(StudentsStringUtils.GENDER.getString());

			saveButton = new Button(StudentsStringUtils.SAVE_BUTTON.getString());
			clearButton = new Button(StudentsStringUtils.CLEAR_BUTTON.getString());

			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			gender.addItem(Gender.MALE.getString());
			gender.addItem(Gender.FEMALE.getString());

			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");

			return this;
		}

		private AddStudentMainLayout bind() {

			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(student);
			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(2, 3);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(firstName, 0, 0);
			gridLayout.addComponent(lastName, 1, 0);

			gridLayout.addComponent(age, 0, 1);
			gridLayout.addComponent(gender, 1, 1);

			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 2);

			return gridLayout;
		}

		public void buttonClick(ClickEvent event) {
			if (event.getSource() == this.saveButton) {
				save();
			} else {
				clearField();
			}
		}

		private void save() {
			try {
				fieldGroup.commit();
			} catch (CommitException e) {

				e.printStackTrace();
			}
			System.out.println(student);

		}

		private void clearField() {
			firstName.setValue(null);
			lastName.setValue(null);
			gender.setValue(null);
			age.setValue(null);
		}

	}

	public Component createComponent() {
		return new AddStudentMainLayout().init().bind().layout();
	}

}
