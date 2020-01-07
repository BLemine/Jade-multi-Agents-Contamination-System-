package agents;
/**
 * C'est le policier stationnaire.
 * il est stationnaire dans le noeud du container Main-Container, 
 * si le virus se déplace vers lui (fixCop), ce dernier va tuer 
 * le virus puis verifier si tout est décontaminé en envoyant des 
 * messages ACL aux agents auxiliaires, si oui il va arrêter le procès sinon il fait rien
 * 
 */
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class FixCop extends Agent {

    @Override
    protected void setup() {
        System.out.println("I am the fixCop");
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive() ;
                if (message != null) {
                    System.out.println("receiving message "+message.getContent() + "From " + message.getSender().getName());
                } else {
                    block() ;
                }
            }
        });
    }
}
