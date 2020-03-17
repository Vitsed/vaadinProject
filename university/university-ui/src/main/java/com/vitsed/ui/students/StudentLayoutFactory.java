package com.vitsed.ui.students;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vitsed.ui.commons.UniversMainUI;
import com.vitsed.utils.StudentsStringUtils;

@SpringView(name = StudentLayoutFactory.NAME, ui = UniversMainUI.class)
public class StudentLayoutFactory extends VerticalLayout implements View {

	public static final String NAME = "addstudent";

	@Autowired
	private AddStudentMainLayoutFactory mainLayoutFactory;
	
	private TabSheet tabSheet;

	private void addLayout() {
		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");

		Component addStudentMainTab = mainLayoutFactory.createComponent();
		Component showStudentsTab = new Label("Show students tab...");

		tabSheet.addTab(addStudentMainTab, StudentsStringUtils.MAIN_MENU.getString());
		tabSheet.addTab(showStudentsTab, StudentsStringUtils.SHOW_ALL_STUDENTS.getString());

		addComponent(tabSheet);

	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
}
