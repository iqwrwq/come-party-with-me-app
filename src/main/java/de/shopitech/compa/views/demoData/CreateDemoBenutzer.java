package de.shopitech.compa.views.demoData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.data.repository.AppUserRepository;
import de.shopitech.compa.security.AuthenticatedUser;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Route(value = "create-demo-users")
@PermitAll
public class CreateDemoBenutzer extends Div implements HasUrlParameter<String> {

    private final AppUserRepository appUserRepository;
    private final Lorem lorem;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${unsplash.accesskey}")
    public String UNSPLASH_ACCESS_KEY;

    @Value("${unsplash.secret}")
    public String UNSPLASH_SECRET_KEY;

    public CreateDemoBenutzer(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        this.lorem = LoremIpsum.getInstance();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String parameter) {
        Button back = new Button("Back");
        back.addClickListener(buttonClickEvent -> {
            getUI().ifPresent(ui -> {
                ui.navigate(DeveloperDashboard.class);
            });
        });
        add(back);

        if (parameter.equals("deleteall")) {
            appUserRepository.deleteAllByEmailNot(adminUsername);

            long count = appUserRepository.count();
            add(new H1("☑️ " + count + " left User(s) ☑️"));

            return;
        }

        Grid<AppUser> items = new Grid<>(AppUser.class);
        List<AppUser> data = new ArrayList<>();

        try {
            for (int i = 0; i < Integer.parseInt(parameter); i++) {
                AppUser appUser = new AppUser();
                appUser.setAppUserId(UUID.randomUUID());
                appUser.setEmail(lorem.getEmail());
                appUser.setFirstName(lorem.getFirstName());
                appUser.setLastName(lorem.getLastName());
                appUser.setPassword(AuthenticatedUser.hashPassword("812421"));
                appUser.setBannerUrl(getRandomUnsplashImageUrl());

                data.add(appUser);

                appUserRepository.save(appUser);
            }

            items.setItems(data);
            add(new H1("☑️ " + parameter + " Event(s) ☑️"));
            add(items);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomUnsplashImageUrl() {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.unsplash.com/photos/random?client_id=" + UNSPLASH_ACCESS_KEY))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response.body());

            return jsonNode.get("urls").get("regular").asText();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
