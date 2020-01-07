package support;
/**
 * C'est la classe responsable de la définition de la 
 * logique du déplacement des différents agents.
 */


public class PathRouter {

    /**
     * Cette méthode reçoit un agent pour le faire déplacer en déterminant les 
     * noeuds d'accessiblité possible pour lui selon juste la logique 
     * du déplacement de l'arbre
     */
    public static void moveAgent(Agent agent) throws ControllerException {
        ContainerID dest =new ContainerID();

        List ContAttr=new List();
        /*
         * L'arbre utilisé se trouve dans le même répertoire racine, un arbre de cinq noeuds
         * et encore dans le rapport.
         * */
        if(agent.getContainerController().getContainerName().equals("Container-1")) {
            String[] str= {"Main-Container","Container-3","Container-4"};
            dest.setName(str[(int)(2*Math.random())]);

        }
        else if(agent.getContainerController().getContainerName().equals("Main-Container")){
            String[] str= {"Container-1","Container-2"};
            dest.setName(str[(int)Math.random()]);
        }
        else if(agent.getContainerController().getContainerName().equals("Container-2")){
            dest.setName("Main-Container");
        }
        else if(agent.getContainerController().getContainerName().equals("Container-3")){
            dest.setName("Container-1");
        }
        else if(agent.getContainerController().getContainerName().equals("Container-4")){
            dest.setName("Container-1");
        }
        agent.doMove(dest);
    }



}
