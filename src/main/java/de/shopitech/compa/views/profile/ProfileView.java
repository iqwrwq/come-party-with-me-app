package de.shopitech.compa.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.tabs.TabSheetVariant;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.security.AuthenticatedUser;
import de.shopitech.compa.views.CompaLayout;
import de.shopitech.compa.views.components.weather.WeatherWidget;
import jakarta.annotation.security.PermitAll;

@PageTitle("Profile")
@Route(value = "profile", layout = CompaLayout.class)
@PermitAll
@Uses(Icon.class)
public class ProfileView extends VerticalLayout {

    private AuthenticatedUser authenticatedUser;

    public ProfileView(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
        AppUser user = authenticatedUser.get().get();

        addClassName("compa-profile-view");

        VerticalLayout container = new VerticalLayout();
        container.addClassName("compa-profile-view--container");

        HorizontalLayout profileHeader = new HorizontalLayout();
        profileHeader.addClassName("compa-profile-view--profile-header");

        Avatar profileAvatar = new Avatar(user.getFirstName());
        profileAvatar.addClassName("compa-profile-view--avatar-icon");

        VerticalLayout profileHeaderTextContainer = new VerticalLayout();
        profileHeaderTextContainer.addClassName("compa-profile-view--profile-text-container");

        HorizontalLayout profileHeaderTextHeading = new HorizontalLayout();
        profileHeaderTextHeading.addClassName("compa-profile-view--profile-name-container");

        H1 userName = new H1(user.getFirstName() + " " + user.getLastName());
        userName.addClassName("compa-profile-view--profile-name");

        Button editProfileButton = new Button("Profil Bearbeiten");
        editProfileButton.addClassName("compa-profile-view--profile-edit-btn");
        Button settingsButton = new Button("Einstellungen");
        settingsButton.addClassName("compa-profile-view--profile-settings-btn");

        profileHeaderTextHeading.add(userName, editProfileButton, settingsButton);

        HorizontalLayout userStats = new HorizontalLayout();
        userStats.addClassName("compa-profile-view--user-stats");
        userStats.add(new H4(user.getEventsPlanned() + " Events"),
                new H4(user.getFollower() + " Follower"),
                new H4(user.getFollowed() + " Gefolgt"));

        HorizontalLayout userNamecontainer = new HorizontalLayout(new H4("@" + user.getUsername()));
        userNamecontainer.addClassName("compa-profile-view--user-name-container");

        TextArea userBioTextArea = new TextArea();
        userBioTextArea.setReadOnly(true);
        userBioTextArea.setValue("this is some demo description");
        userBioTextArea.addClassName("compa-profile-view--bio-area");
        HorizontalLayout userBio = new HorizontalLayout(userBioTextArea);
        userBio.addClassName("compa-profile-view--user-bio-container");

        profileHeaderTextContainer.add(profileHeaderTextHeading, userStats, userNamecontainer, userBio);

        profileHeader.add(profileAvatar, profileHeaderTextContainer);

        TabSheet tabSheet = new TabSheet();
        tabSheet.addThemeVariants(TabSheetVariant.LUMO_TABS_CENTERED);
        tabSheet.addClassName("compa-profile-view--tabsheet");
        tabSheet.add(new Tab("Events"), new H1("tabsheet1"));
        tabSheet.add(new Tab("Geschenklisten"), new H1("tabsheet2"));
        tabSheet.add(new Tab("Todos"), new H1("tabsheet3"));
        tabSheet.add(new Tab("Freunde"), new H1("tabsheet4"));

        container.add(profileHeader, tabSheet);
        container.setAlignItems(Alignment.CENTER);

        add(container);
    }
}
