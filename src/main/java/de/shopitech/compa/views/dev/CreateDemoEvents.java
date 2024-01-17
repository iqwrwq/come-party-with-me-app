package de.shopitech.compa.views.dev;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import de.shopitech.compa.data.entity.AppUser;
import de.shopitech.compa.data.entity.Event;
import de.shopitech.compa.data.repository.AppUserRepository;
import de.shopitech.compa.data.repository.EventRepository;
import jakarta.annotation.security.PermitAll;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Route(value = "create-demo-events")
@PermitAll
public class CreateDemoEvents extends Div implements HasUrlParameter<String> {

    private AppUserRepository appUserRepository;
    private EventRepository eventRepository;
    private Lorem lorem;
    private Random random;

    public CreateDemoEvents(AppUserRepository appUserRepository, EventRepository eventRepository) {
        this.appUserRepository = appUserRepository;
        this.eventRepository = eventRepository;
        this.lorem = LoremIpsum.getInstance();
        this.random = new Random();
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
            long count = eventRepository.count();
            eventRepository.deleteAll();
            add(new H1("☑️ " + count + " deleted Event(s) ☑️"));

            return;
        }

        try {
            Grid<Event> items = new Grid<>(Event.class);
            List<Event> data = new ArrayList<>();
            List<AppUser> allUsers = appUserRepository.findAll();
            List<Event> events = new ArrayList<>();

            List<CompletableFuture<Event>> futureEvents = new ArrayList<>();

            for (int i = 0; i < Integer.parseInt(parameter); i++) {
                CompletableFuture<Event> futureEvent = createEventAsync(allUsers);
                futureEvents.add(futureEvent);
            }

            CompletableFuture<Void> allOf = CompletableFuture.allOf(futureEvents.toArray(new CompletableFuture[0]));

            allOf.get();

            for (CompletableFuture<Event> futureEvent : futureEvents) {
                data.add(futureEvent.get());
            }

            eventRepository.saveAll(data);

            items.setItems(data);
            add(new H1("☑️ " + parameter + " Event(s) ☑️"));
            add(items);
        } catch (Exception e) {
            add(new Span(e.toString()));
        }
    }

    private CompletableFuture<Event> createEventAsync(List<AppUser> allUsers) {
        return CompletableFuture.supplyAsync(() -> {
            Event event = new Event();

            event.setEventId(UUID.randomUUID());
            event.setLocation(lorem.getCity());
            event.setDescription(lorem.getWords(5));
            LocalDate currentDate = LocalDate.now();
            int randomDays = new Random().nextInt(365) + 1;
            LocalDate futureDate = currentDate.plusDays(randomDays);
            int randomUserIndex = random.nextInt(allUsers.size());
            AppUser randomUser = allUsers.get(randomUserIndex);
            event.setOrganizer(randomUser);

            return event;
        });
    }

    private CompletableFuture<byte[]> loadImageAsync(int index) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                byte[] imageBytes = loadEventBanner(index);
                return imageBytes;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private byte[] loadEventBanner(int index) throws IOException {
        Resource resource = new ClassPathResource("eventBanner" + index + ".jpeg");
        return StreamUtils.copyToByteArray(resource.getInputStream());
    }
}


