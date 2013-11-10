package lt.evo01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

public class MainFormController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 2552168101641284974L;

	static List<String> countryList = new ArrayList<String>();
	static{
		countryList.add("Afghanistan");
		countryList.add("Aland Islands");
		countryList.add("Albania");
		countryList.add("Yemen");
		countryList.add("Zambia");
		countryList.add("Zimbabwe");
		countryList.add("Serbia");
		countryList.add("Montenegro");
		countryList = Collections.unmodifiableList(countryList);
	}

    //wire components
    @Wire
    Label account;
    @Wire
    Textbox fullName;
    @Wire
    Textbox email;
    @Wire
    Datebox birthday;
    @Wire
    Listbox country;
    @Wire
    Textbox bio;
    @Wire
    Label nameLabel;

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);

        ListModelList<String> countryModel = new ListModelList<String>(countryList);
        country.setModel(countryModel);

        refreshProfileView();
    }

    @Listen("onClick=#saveProfile")
    public void doSaveProfile(){
        //apply component value to bean
    	Clients.showNotification(fullName.getValue());
    	Clients.showNotification(email.getValue());
    	Clients.showNotification(birthday.getValue().toString());
    	Clients.showNotification(bio.getValue());
         
        Set<String> selection = ((ListModelList)country.getModel()).getSelection();
        if(!selection.isEmpty()){
        	Clients.showNotification(selection.iterator().next());
        } else {
        	Clients.showNotification(null);
        }

        Clients.showNotification("Your profile is updated");
    }
    
    @Listen("onClick=#reloadProfile")
    public void doReloadProfile(){
        refreshProfileView();
    }
    
	private void refreshProfileView() {
        //apply bean value to UI components
        account.setValue("s45382");
        fullName.setValue("Evaldas Pareigis");
        email.setValue("dezute@gmail.com");
        birthday.setValue(new Date());
        bio.setValue("No data");
        nameLabel.setValue("s45382");
         
        ((ListModelList)country.getModel()).addToSelection("Zambia");
    }
}
