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
import de.shopitech.compa.data.entity.GiftList;
import de.shopitech.compa.data.repository.AppUserRepository;
import de.shopitech.compa.data.repository.EventRepository;
import de.shopitech.compa.data.repository.GiftListRepository;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Route(value = "create-demo-geschenkelisten")
@PermitAll
public class CreateDemoGeschenkeListen extends Div implements HasUrlParameter<String> {

    private AppUserRepository appUserRepository;
    private EventRepository eventRepository;
    private GiftListRepository giftListRepository;
    private Lorem lorem;
    private Random random;

    public CreateDemoGeschenkeListen(AppUserRepository appUserRepository, EventRepository eventRepository, GiftListRepository giftListRepository) {
        this.appUserRepository = appUserRepository;
        this.eventRepository = eventRepository;
        this.giftListRepository = giftListRepository;
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
            long count = giftListRepository.count();
            giftListRepository.deleteAll();
            add(new H1("☑️ " + count + " deleted Geschenkliste(n) ☑️"));

            return;
        }

        Grid<GiftList> items = new Grid<>(GiftList.class);
        List<GiftList> data = new ArrayList<>();
        List<Event> allEvents = eventRepository.findAll();
        List<AppUser> allUsers = appUserRepository.findAll();

        try {
            for (int i = 0; i < Integer.parseInt(parameter); i++) {
                GiftList geschenkliste = new GiftList();
                geschenkliste.setGiftListId(UUID.randomUUID());
                geschenkliste.setTitle(lorem.getName());
                int randomEventIndex = random.nextInt(allEvents.size());
                Event randomEvent = allEvents.get(randomEventIndex);
                geschenkliste.setEvent(randomEvent);
                int randomUserIndex = random.nextInt(allUsers.size());
                AppUser randomUser = allUsers.get(randomUserIndex);
                geschenkliste.setCreator(randomUser);

                data.add(geschenkliste);

                giftListRepository.save(geschenkliste);
            }

            items.setItems(data);
            add(new H1("☑️ " + parameter + " Geschenkliste(n) ☑️"));
            add(items);
        } catch (Exception e) {
            add(new Span(e.toString()));
        }
    }
}
