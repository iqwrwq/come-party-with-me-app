package de.shopitech.compa.views.discover;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.shopitech.compa.views.CompaLayout;

@PageTitle("Entdecken")
@Route(value = "discover", layout = CompaLayout.class)
@AnonymousAllowed
@Uses(Icon.class)
public class DiscoverView extends Composite<VerticalLayout> {

    public DiscoverView() {}
}