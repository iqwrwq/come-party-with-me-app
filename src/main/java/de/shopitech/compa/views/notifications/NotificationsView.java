package de.shopitech.compa.views.notifications;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.shopitech.compa.views.CompaLayout;

@PageTitle("Benachrichtigungen")
@Route(value = "notifications", layout = CompaLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class NotificationsView extends Composite<VerticalLayout> {
}
