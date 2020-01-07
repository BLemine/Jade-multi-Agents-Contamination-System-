package plateform;
/*
 * Auteurs : BAILLAHI & DAKIR
 * */
/**
 * 
 * Cette classe est pour la création des noeuds de notre arbre, elle crée un simple noeud dans
 * chaque execution avec un agent auxillière qu'on utilise pour déterminer l'état de 
 * contamination, cet agent auxillière avec son comportement est défini dans la classe "agents.SimpleAgent"
 */


public class SimpleContainer {

    public static void main(String[] args) {
        try {
            Runtime runtime = Runtime.instance();
            Profile profile = new ProfileImpl(false);
            profile.setParameter(Profile.MAIN_HOST, "localhost");
            AgentContainer agentContainer = runtime.createAgentContainer(profile);

            // On défini le nom de l'agent auxillière qui est responsable de la gestion des états 
            // de contamination.
            String agentName = "agent" + agentContainer.getContainerName();

            // l'agent sera lancé comme dit précédement dans le container "Container-i"
            AgentController simpleAgent = agentContainer.createNewAgent(agentName, "agents.SimpleAgent", new Object[]{"isContaminated", true}) ;
            simpleAgent.start();


        } catch (ControllerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
