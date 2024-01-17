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
import de.shopitech.compa.data.entity.Gift;
import de.shopitech.compa.data.repository.GiftRepository;
import jakarta.annotation.security.PermitAll;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Route(value = "create-demo-geschenke")
@PermitAll
public class CreateDemoGeschenke extends Div implements HasUrlParameter<String> {

    private GiftRepository geschenkRepository;
    private Lorem lorem;
    private Random random;

    public CreateDemoGeschenke(GiftRepository geschenkRepository) {
        this.geschenkRepository = geschenkRepository;
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
            long count = geschenkRepository.count();
            geschenkRepository.deleteAll();
            add(new H1("☑️ " + count + " deleted Geschenk(e) ☑️"));

            return;
        }

        Grid<Gift> items = new Grid<>(Gift.class);
        List<Gift> data = new ArrayList<>();

        try {
            for (int i = 0; i < Integer.parseInt(parameter); i++) {
                Gift geschenk = new Gift();
                geschenk.setGiftId(UUID.randomUUID());
                geschenk.setName(lorem.getName());
                double randomNumber = (random.nextDouble() * 998) + 1;
                randomNumber = Math.round(randomNumber * 100.0) / 100.0;
                geschenk.setPrice(randomNumber);

                data.add(geschenk);

                geschenkRepository.save(geschenk);
            }

            items.setItems(data);
            add(new H1("☑️ " + parameter + " Geschenk(e) ☑️"));
            add(items);
        } catch (Exception e) {
            add(new Span(e.toString()));
        }
    }
}
