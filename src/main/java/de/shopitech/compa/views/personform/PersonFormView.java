package de.shopitech.compa.views.personform;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.AvatarGroup;
import com.vaadin.flow.component.avatar.AvatarGroup.AvatarGroupItem;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import de.shopitech.compa.views.CompaLayout;

@PageTitle("Person Form")
@Route(value = "person-form", layout = CompaLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class PersonFormView extends Composite<VerticalLayout> {

    public PersonFormView() {
        FormLayout formLayout2Col = new FormLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        AvatarGroup avatarGroup = new AvatarGroup();
        H1 h1 = new H1();
        TextField textField = new TextField();
        FormLayout formLayout2Col2 = new FormLayout();
        DatePicker datePicker = new DatePicker();
        TimePicker timePicker = new TimePicker();
        TextField textField2 = new TextField();
        CheckboxGroup checkboxGroup = new CheckboxGroup();
        TextArea textArea = new TextArea();
        HorizontalLayout layoutRow = new HorizontalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        NumberField numberField = new NumberField();
        H2 h2 = new H2();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(JustifyContentMode.START);
        getContent().setAlignItems(Alignment.CENTER);
        formLayout2Col.setWidth("100%");
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        avatarGroup.setWidth("min-content");
        setAvatarGroupSampleData(avatarGroup);
        h1.setText("New Event");
        h1.setWidth("max-content");
        textField.setLabel("Event Name");
        textField.setWidth("192px");
        formLayout2Col2.setWidth("100%");
        datePicker.setLabel("Date");
        datePicker.setWidth("min-content");
        timePicker.setLabel("Time");
        timePicker.setWidth("min-content");
        textField2.setLabel("Occupation");
        checkboxGroup.setLabel("Optionals");
        checkboxGroup.setWidth("min-content");
        checkboxGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        textArea.setLabel("Description");
        textArea.setWidth("100%");
        layoutRow.setHeightFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        layoutRow3.setHeightFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        textField3.setLabel("Text field");
        textField3.setWidth("min-content");
        textField4.setLabel("Text field");
        textField4.setWidth("min-content");
        numberField.setLabel("Number field");
        numberField.setWidth("min-content");
        h2.setText("Add Friends");
        h2.setWidth("max-content");
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Save");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancel");
        buttonSecondary.setWidth("min-content");
        getContent().add(formLayout2Col);
        formLayout2Col.add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(avatarGroup);
        layoutColumn3.add(h1);
        layoutColumn2.add(textField);
        layoutColumn2.add(formLayout2Col2);
        formLayout2Col2.add(datePicker);
        formLayout2Col2.add(timePicker);
        formLayout2Col2.add(textField2);
        formLayout2Col2.add(checkboxGroup);
        formLayout2Col2.add(textArea);
        formLayout2Col2.add(layoutRow);
        formLayout2Col2.add(layoutRow3);
        formLayout2Col2.add(textField3);
        formLayout2Col2.add(textField4);
        getContent().add(h2);
        getContent().add(layoutRow2);
        layoutRow2.add(buttonPrimary);
        layoutRow2.add(buttonSecondary);
    }

    private void setAvatarGroupSampleData(AvatarGroup avatarGroup) {
        avatarGroup.add(new AvatarGroupItem("A B"));
        avatarGroup.add(new AvatarGroupItem("C D"));
        avatarGroup.add(new AvatarGroupItem("E F"));
    }
}
