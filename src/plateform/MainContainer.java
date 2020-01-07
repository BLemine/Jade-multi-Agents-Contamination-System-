package plateform;
/*
 * Authors : BAILLAHI & DAKIR
 * */
/**
 * Cette classe concerne le container Main, elle doit etre executée en premier, ça va créer 
 * le container Main-Container qui comporte les deux agents fixCop et mobileCop
 * 
 */
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

public class MainContainer {
    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance() ;

            Properties properties = new ExtendedProperties();
            properties.setProperty(Profile.GUI, "true") ;
            Profile profile = new ProfileImpl(properties);
            AgentContainer mainContainer = runtime.createMainContainer(profile) ;

            // attachons l'agent fixCop au container Main-Container
            AgentController FixCop = mainContainer.createNewAgent("fixCop", "agents.FixCop" , new Object[]{}) ;
            // attachons l'agent mobileCop au container Main-Container
            AgentController MobileCop = mainContainer.createNewAgent("mobileCop", "agents.MobileCop" , new Object[]{}) ;

            FixCop.start();
            MobileCop.start();

        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
