package agents;
/**
 * C'est un agent auxiliaire dans chaque container pour gérer l'état de contamination, 
 * on l'a appelé : agentContainer-i (avec i dans [1,5]).
 */
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class SimpleAgent extends Agent {
    // l'état de la contamination
    public Boolean isContaminated = false ;
    // pour récupérer l'état de la contamination du container
    public Boolean getContaminated() {
        return isContaminated;
    }
    // pour mettre à jour l'état de la contamination du container
    public void setContaminated(Boolean contaminated) {
        isContaminated = contaminated;
    }

    @Override
    protected void setup() {
        /**
         * Il reçoit les messages de chaque nouveau visiteur de son noeud, 
         * si le visiteur est le _VIRUS_ il modifie donc l'état de la 
         * contamination par _true_ , sinon si c'est le visiteur est le policier 
         * mobile (mobileCop) alors il met l'état à false, 
         * et il affiche l'état après.
         */
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage message = receive() ;
                if (message != null) {
                    System.out.println("the received message : "+message.getContent() + "From " + message.getSender().getName());
                    if ( message.getSender().getName().contains("VIRUS")){
                        // il met l'état à true pour dire que c'est contaminé
                        setContaminated(true);
                        System.out.println("The contamination status : " + getContaminated().toString());
                    }else{
                        // il met l'état à false pour dire que c'est décontaminé
                        setContaminated(false);
                        System.out.println("The contamination status : " + getContaminated().toString());
                    }
                } else {
                    block() ;
                }
            }
        });
    }
}
