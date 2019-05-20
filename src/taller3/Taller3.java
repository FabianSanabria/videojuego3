/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;
import ucn.ArchivoEntrada;
import ucn.StdIn;
import ucn.StdOut;
/**
 *
 * @author fabianxd
 */
public class Taller3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GameSystemImpl system = new GameSystemImpl();
        system.addCardsToTheSystem();
        int optionSelected;
        while(true){
            optionSelected=system.menu();
            if(optionSelected==1){
                system.play();
                
            }
            if(optionSelected==2){
                String id;
                StdOut.println("Type the id of the card");
                id=StdIn.readString();
                system.findCardGame(id);
            }
            if(optionSelected==3){
                
            }
            if(optionSelected==4){
                
            }
            if(optionSelected==5){
                
            }
            
        }
        
    }
    
}
