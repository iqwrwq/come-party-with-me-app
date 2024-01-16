package de.shopitech.compa.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.theme.lumo.LumoUtility;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.security.AuthenticatedUser;
import de.shopitech.compa.views.demoData.DeveloperDashboard;
import de.shopitech.compa.views.eventchat.EventchatView;
import de.shopitech.compa.views.events.EventsView;
import de.shopitech.compa.views.home.HomeView;
import de.shopitech.compa.views.imagegallery.ImageGalleryView;
import de.shopitech.compa.views.personform.PersonFormView;
import de.shopitech.compa.views.register.RegisterView;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class MainLayout extends AppLayout {

    private H2 viewTitle;

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public MainLayout(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        addClassName("compa-main-layout");
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        Image logo = new Image("images/Logo.png", "Come Party With Me");
        logo.addClassName("compa-logo");

        Scroller scroller = new Scroller(createNavigation());
        scroller.addClassName("compa-nav");

        addToDrawer(logo, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        if (accessChecker.hasAccess(DeveloperDashboard.class)) {
            nav.addItem(new SideNavItem("DEV", DeveloperDashboard.class, LineAwesomeIcon.DEV.create()));
        }

        if (accessChecker.hasAccess(HomeView.class)) {
            nav.addItem(new SideNavItem("Home", HomeView.class, LineAwesomeIcon.CROWN_SOLID.create()));
        }
        if (accessChecker.hasAccess(EventsView.class)) {
            nav.addItem(new SideNavItem("Events", EventsView.class, LineAwesomeIcon.PEACE_SOLID.create()));

        }
        if (accessChecker.hasAccess(EventchatView.class)) {
            nav.addItem(new SideNavItem("Eventchat", EventchatView.class, LineAwesomeIcon.COMMENTS.create()));

        }
        if (accessChecker.hasAccess(RegisterView.class)) {
            nav.addItem(new SideNavItem("Register", RegisterView.class, LineAwesomeIcon.USER.create()));

        }
        if (accessChecker.hasAccess(ImageGalleryView.class)) {
            nav.addItem(
                    new SideNavItem("Image Gallery", ImageGalleryView.class, LineAwesomeIcon.TH_LIST_SOLID.create()));

        }
        if (accessChecker.hasAccess(PersonFormView.class)) {
            nav.addItem(new SideNavItem("Person Form", PersonFormView.class, LineAwesomeIcon.USER.create()));

        }

        return nav;
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

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
