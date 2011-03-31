package eu.czerpak.vaadin;

import com.vaadin.Application;
import com.vaadin.ui.*;
import eu.czerpak.ejb.SimpleEjb;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ejb.EJB;

@Component(value = "vaadinApp")
@Scope(value = "session")
public class VaadinApp extends Application
{
    protected static final String TITLE = "Test App";

    @EJB
    SimpleEjb simple;

    @Override
    public void init()
    {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();
        Label header = new Label(TITLE);
        header.setStyleName("h1");
        layout.addComponent(header);

        layout.addComponent(new Button("Say", new Button.ClickListener()
        {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent)
            {
                layout.addComponent(new Label(simple.sayHello("Lukes")));
            }
        }));

        TabSheet tabs = new TabSheet();
        tabs.setSizeFull();
        layout.addComponent(tabs);
        layout.setExpandRatio(tabs, 1);

        Window mainWindow = new Window(TITLE, layout);
        setMainWindow(mainWindow);
    }
}
