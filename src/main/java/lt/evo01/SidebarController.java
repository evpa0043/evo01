package lt.evo01;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;

public class SidebarController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 7410502984150436833L;

    @Wire
    Grid fnList;

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
 
        //initialize view after view construction.
        Rows rows = fnList.getRows();
         
        Row row = constructSidebarRow("ZK", "http://www.zkoss.org/");
        rows.appendChild(row);
        row = constructSidebarRow("ZK Demo", "http://www.zkoss.org/zkdemo");
        rows.appendChild(row);
        row = constructSidebarRow("ZK Developer Reference", "http://books.zkoss.org/wiki/ZK_Developer's_Reference");
        rows.appendChild(row);
    }

    private Row constructSidebarRow(String label, final String locationUri) {

        //construct component and hierarchy
        Row row = new Row();
        Label image = new Label("O");
        Label lab = new Label(label);
         
        row.appendChild(image);
        row.appendChild(lab);
         
        //set style attribute
        //row.setSclass("sidebar-fn");
         
        //create and register event listener
        EventListener<Event> actionListener = new SerializableEventListener<Event>() {
     		private static final long serialVersionUID = 1L;

			public void onEvent(Event event) throws Exception {
                //redirect current url to new location
                Executions.getCurrent().sendRedirect(locationUri);
            }
        };
         
        row.addEventListener(Events.ON_CLICK, actionListener);
 
        return row;
    }
}
