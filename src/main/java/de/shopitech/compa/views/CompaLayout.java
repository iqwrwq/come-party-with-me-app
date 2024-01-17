package de.shopitech.compa.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.security.AuthenticatedUser;
import de.shopitech.compa.views.components.CompaFooter;
import de.shopitech.compa.views.components.CompaNavigation;

import java.util.Optional;

public class CompaLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public CompaLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        addClassName("compa-main-layout");
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);

        Image logo = new Image("images/Logo.png", "Come Party With Me");
        logo.setWidth("75px");
        logo.getStyle().setPadding("2rem 2rem 2rem 2rem");
        Scroller scroller = new Scroller(new CompaNavigation(authenticatedUser, accessChecker));
        addToDrawer(logo, scroller, createFooter());
        addHeaderContent();

    }

    private Footer createFooter() {
        Footer layout = new Footer();

        Optional<AppUser> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            AppUser user = maybeUser.get();

            Avatar avatar = new Avatar(user.getFirstName());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getFirstName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            div.getElement().getStyle().set("gap", "var(--lumo-space-s)");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            layout.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            layout.add(loginLink);
        }

        return layout;
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        Optional<AppUser> maybeUser = authenticatedUser.get();
        HorizontalLayout header = new HorizontalLayout();

        HorizontalLayout userHeader = new HorizontalLayout();
        if (maybeUser.isPresent()) {
            AppUser user = maybeUser.get();

            Avatar avatar = new Avatar(user.getFirstName());
            avatar.setThemeName("xsmall");
            avatar.getElement().setAttribute("tabindex", "-1");

            MenuBar userMenu = new MenuBar();
            userMenu.setThemeName("tertiary-inline contrast");

            MenuItem userName = userMenu.addItem("");
            Div div = new Div();
            div.add(avatar);
            div.add(user.getFirstName());
            div.add(new Icon("lumo", "dropdown"));
            div.getElement().getStyle().set("display", "flex");
            div.getElement().getStyle().set("align-items", "center");
            userName.add(div);
            userName.getSubMenu().addItem("Sign out", e -> {
                authenticatedUser.logout();
            });

            userHeader.add(userMenu);
        } else {
            Anchor loginLink = new Anchor("login", "Sign in");
            userHeader.add(loginLink);
        }

        header.add(toggle, userHeader);
        header.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        addToNavbar(true, toggle);
    }



    @Override
    protected void afterNavigation() {
    }
}
