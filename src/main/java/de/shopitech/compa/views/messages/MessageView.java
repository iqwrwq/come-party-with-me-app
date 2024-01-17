package de.shopitech.compa.views.messages;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.shopitech.compa.views.CompaLayout;

@PageTitle("Nachrichten")
@Route(value = "messages", layout = CompaLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class MessageView extends Composite<VerticalLayout> {

    public MessageView() {}
}