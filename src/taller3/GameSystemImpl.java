/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;
import ucn.ArchivoEntrada;
import ucn.StdOut;
import ucn.StdIn;
import java.util.InputMismatchException;
/**
 *
 * @author fabianxd
 */
public class GameSystemImpl implements GameSystem {
    private HeroList Heroes;
    private WarriorList warriorCards;
    private GuardianList guardianCards;
    private SpellList spellCards;
    private Hero player1;
    private Hero player2;
    public void leerArchivo(ArchivoEntrada cards){
        
    }
    public void gameTurns(){
        while(player1.getHp()>0 && player2.getHp()>0){
            
        }
        
    }

    /**
     *
     * @param player
     * @return
     */
    @Override
    public Deck chooseYourCards(int player){
        Warrior warriorSelected;
        Guardian guardianSelected;
        Spell spellSelected;
        Deck newDeck;
        StdOut.println(" .:: Warriors ::. ");
        for(int i=0;i<warriorCards.getWarriorQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            warriorCards.getWarrior(i).getName()+" -> "+
            warriorCards.getWarrior(i).getRarity()+" -> "+
            warriorCards.getWarrior(i).getRace()+" -> "+
            "Hp: "+warriorCards.getWarrior(i).getHp()+" -> "+
            "Damage: "+ warriorCards.getWarrior(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        int cardSelected;
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<warriorCards.getWarriorQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        warriorSelected=warriorCards.getWarrior(cardSelected);
        
        
        StdOut.println(" .:: Guardians ::. ");
        for(int i=0;i<guardianCards.getGuardianQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            guardianCards.getGuardian(i).getName()+" -> "+
            guardianCards.getGuardian(i).getRace()+" -> "+
            "Hp: "+guardianCards.getGuardian(i).getHp()+" -> "+
            "Damage: "+ guardianCards.getGuardian(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<guardianCards.getGuardianQty()&& cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        guardianSelected=guardianCards.getGuardian(cardSelected);
  
        StdOut.println(" .:: Spells ::. ");
        for(int i=0;i<spellCards.getSpellQty();i++){
            StdOut.print("[" + i + "]");
            StdOut.println(
            spellCards.getSpell(i).getName()+" -> "+
            spellCards.getSpell(i).getRarity()+" -> "+
            "Damage: "+ spellCards.getSpell(i).getDamage());
        }
        StdOut.println("Choose 1 card ");
        while(true){
            try{
                cardSelected=StdIn.readInt();
            }catch(InputMismatchException e){
                StdOut.println("Choose an integer");
                return null;
            }
            if(cardSelected<spellCards.getSpellQty() && cardSelected>=0){
                break;
            }
            else{
                StdOut.println("Choose between the range of the cards");
            }
        }
        spellSelected=spellCards.getSpell(cardSelected);
        newDeck= new Deck(warriorSelected,guardianSelected,spellSelected);
        return newDeck;
    }
    
    @Override
    public void useWarrior(int player){
        if(player==1){
            StdOut.println(".:: Warrior of "+ player1.getAlias()+" ::.");
            StdOut.println(player1.getDeck().getWarrior().getName()+" -> "+
            player1.getDeck().getWarrior().getRace() + " -> Hp: "    +
            player1.getDeck().getWarrior().getHp() +   " -> Damage: "+
            player1.getDeck().getWarrior().getDamage());
            
            StdOut.println(".:: "+player1.getDeck().getWarrior().getName()+
            " attacks ::.");
            if(player2.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player2.getAlias());
                player2.loseHp(player1.getDeck().getWarrior().getDamage());
            }
            else{
                
                StdOut.print(player2.getDeck().getWarrior().getName()+" -> "+
                player2.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player2.getDeck().getWarrior().getHp());
            
                player2.getDeck().getWarrior().loseHp(player1.getDeck().getWarrior()
                .getDamage());
            
            }
        }
        else{
            StdOut.println(".:: Warrior of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getWarrior().getName()+" -> "+
            player2.getDeck().getWarrior().getRace() + " -> Hp: "    +
            player2.getDeck().getWarrior().getHp() +   " -> Damage: "+
            player2.getDeck().getWarrior().getDamage());
            
            StdOut.println(".:: "+player2.getDeck().getWarrior().getName()+
            " attacks ::.");
            if(player1.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player1.getAlias());
                player1.loseHp(player2.getDeck().getWarrior().getDamage());
            }
            else{
                
                StdOut.print(player1.getDeck().getWarrior().getName()+" -> "+
                player1.getDeck().getWarrior().getRace()+" -> "+ "Hp:"+
                player1.getDeck().getWarrior().getHp());
            
                player1.getDeck().getWarrior().loseHp(player2.getDeck().getWarrior()
                .getDamage());
            }
            
        }
        
        
    }
    @Override
    public void useSpell(int player){
        if(player==1){
            StdOut.println(".:: Spell of "+ player1.getAlias()+" ::.");
            StdOut.println(player1.getDeck().getSpell().getName()+" -> "+
            player1.getDeck().getSpell().getRarity() + 
            " -> Damage: "+ player1.getDeck().getSpell().getDamage());
            
            StdOut.println(".:: Attack with  "
            +player1.getDeck().getSpell().getName()+" .::");
            if(player2.getDeck().getWarrior().getHp()==0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player2.getAlias());
                player2.loseHp(player1.getDeck().getSpell().getDamage());
            }
            else{
                
                StdOut.print(player2.getDeck().getWarrior().getName()+" -> "+
                player2.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player2.getDeck().getWarrior().getHp());
            
                player2.getDeck().getWarrior().loseHp(player2.getDeck().getSpell()
                .getDamage());
                StdOut.println(" -> Damage: "+
                player2.getDeck().getWarrior().getDamage());
            }
        }
        else{
            StdOut.println(".:: Spell of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getSpell().getName()+" -> "+
            player2.getDeck().getSpell().getRarity() + " -> Damage: "+
            player2.getDeck().getSpell().getDamage());
            
            StdOut.println(".:: Attack with "+player2.getDeck()
            .getSpell().getName()+ " ::.");
            if(player1.getDeck().getWarrior().getHp()<=0){
                StdOut.println("Warrior is dead, the damage is dealt to the "
                        + "Hero "+player1.getAlias());
                player1.loseHp(player2.getDeck().getSpell().getDamage());
            }
            else{
                StdOut.print(player1.getDeck().getWarrior().getName()+" -> "+
                player1.getDeck().getWarrior().getRace()+" -> "+ "Hp: "+
                player1.getDeck().getWarrior().getHp()+" -> ");
            
                player1.getDeck().getWarrior().loseHp(player2.getDeck().getWarrior()
                .getDamage());
                StdOut.println(" -> Damage: "
                +player1.getDeck().getWarrior().getDamage());
            }
            
        }
        
        
    }

    @Override
    public void showDuelOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void useGuardian(int player) {
        
        
    }

    @Override
    public boolean findCardGame(String id) {
        for(int i=0;i<warriorCards.getWarriorQty();i++){
            if(id.equals(warriorCards.getWarrior(i).getId())){
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getName());
                StdOut.println("Race: "+ warriorCards.getWarrior(i).getRace());
                StdOut.println("Times used: "+ warriorCards.getWarrior(i)
                .getTimesUsed());
                StdOut.println("Times dead: "+ warriorCards.getWarrior(i)
                .getTimesDead());
                return true;
                
            }
        }
        for(int i=0;i<guardianCards.getGuardianQty();i++){
            if(id.equals(guardianCards.getGuardian(i).getId())){
               StdOut.println("Name: "+ guardianCards.getGuardian(i).getName());
               StdOut.println("Race: "+ guardianCards.getGuardian(i).getRace());
               StdOut.println("Times used: "+ guardianCards.getGuardian(i)
               .getTimesUsed());
                return true;
            }
        }
        for(int i=0;i<spellCards.getSpellQty();i++){
            if(id.equals(spellCards.getSpell(i).getId())){
                StdOut.println("Name: "+ spellCards.getSpell(i).getName());
               StdOut.println("Rarity: "+ spellCards.getSpell(i).getRarity());
               StdOut.println("Times used: "+ guardianCards.getGuardian(i)
               .getTimesUsed());
               return true;
            }
        }
        return false;
        //xddxd
    }

    @Override
    public void heroesThatParticipated() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void detailsOfLastCombat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
