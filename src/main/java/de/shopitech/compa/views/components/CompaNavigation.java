package de.shopitech.compa.views.components;

import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.security.AuthenticatedUser;
import de.shopitech.compa.views.create.CreateEventView;
import de.shopitech.compa.views.dev.DeveloperDashboard;
import de.shopitech.compa.views.discover.DiscoverView;
import de.shopitech.compa.views.home.HomeView;
import de.shopitech.compa.views.login.LoginView;
import de.shopitech.compa.views.messages.MessageView;
import de.shopitech.compa.views.notifications.NotificationsView;
import de.shopitech.compa.views.personform.PersonFormView;
import de.shopitech.compa.views.profile.ProfileView;
import de.shopitech.compa.views.register.RegisterView;
import de.shopitech.compa.views.search.SearchView;
import org.vaadin.lineawesome.LineAwesomeIcon;

import java.util.Optional;

public class CompaNavigation extends SideNav {

    private AuthenticatedUser authenticatedUser;
    private AccessAnnotationChecker accessChecker;

    public CompaNavigation(AuthenticatedUser authenticatedUser, AccessAnnotationChecker accessChecker) {
        this.authenticatedUser = authenticatedUser;
        this.accessChecker = accessChecker;

        if (accessChecker.hasAccess(HomeView.class)) {
            SvgIcon icon = LineAwesomeIcon.HOME_SOLID.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Startseite", HomeView.class, icon));
        }

        if (accessChecker.hasAccess(SearchView.class)) {
            SvgIcon icon = LineAwesomeIcon.SEARCH_SOLID.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Suchen", SearchView.class, icon));
        }

        if (accessChecker.hasAccess(DiscoverView.class)) {
            SvgIcon icon = LineAwesomeIcon.CALENDAR_CHECK_SOLID.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Entdecken", DiscoverView.class, icon));
        }

        if (accessChecker.hasAccess(MessageView.class)) {
            SvgIcon icon = LineAwesomeIcon.PAPER_PLANE_SOLID.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Nachrichten", MessageView.class, icon));
        }

        if (accessChecker.hasAccess(NotificationsView.class)) {
            SvgIcon icon = LineAwesomeIcon.HEART.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Benachrichtigungen", NotificationsView.class, icon));
        }

        if (accessChecker.hasAccess(CreateEventView.class)) {
            SvgIcon icon = LineAwesomeIcon.PLUS_SQUARE_SOLID.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("Erstellen", CreateEventView.class, icon));
        }

        if (accessChecker.hasAccess(DeveloperDashboard.class)) {
            SvgIcon icon = LineAwesomeIcon.DEV.create();
            icon.setClassName("compa-nav-menu--item--icon");

            addItem(new SideNavItem("DEVELOPER", DeveloperDashboard.class, icon));
        }


        if (accessChecker.hasAccess(ProfileView.class)) {
            if (authenticatedUser.get().isPresent()) {
                Optional<AppUser> user = authenticatedUser.get();
                Avatar avatarIcon =new Avatar(user.get().getFirstName());
                avatarIcon.setClassName("compa-nav-menu--item--avatar-icon");
                addItem(new SideNavItem("Profil", ProfileView.class, avatarIcon));

                return;
            }
            addItem(new SideNavItem("Profil", LoginView.class, LineAwesomeIcon.USER.create()));
        }

        if (authenticatedUser.get().isEmpty()){
            addItem(new SideNavItem("Login", LoginView.class, LineAwesomeIcon.SIGN_IN_ALT_SOLID.create()));
            addItem(new SideNavItem("Register", RegisterView.class, LineAwesomeIcon.USER_PLUS_SOLID.create()));
        }
    }
}
