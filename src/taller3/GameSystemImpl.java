/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;
import java.io.IOException;
import ucn.ArchivoEntrada;
import ucn.StdOut;
import ucn.StdIn;
import java.util.InputMismatchException;
import ucn.StdRandom;
import ucn.Registro;
/**
 *
 * @author fabianxd
 */
public class GameSystemImpl implements GameSystem {
    private HeroList heroes;
    private WarriorList warriorCards;
    private GuardianList guardianCards;
    private SpellList spellCards;
    private Hero player1;
    private Hero player2;
    private int turn;

    public GameSystemImpl() {
        heroes = new HeroList(100000);
        warriorCards = new WarriorList(50000);
        guardianCards = new GuardianList(50000);
        spellCards = new SpellList(50000);
        this.turn = 0;
    }
    
     public int validateGameOptions(String inputNumber){
        try{
            int option = Integer.parseInt(inputNumber);
                if(option<=3 && option>0){
                    return option;  
                }
                else{
                    StdOut.println("Choose a number between the options: 1 "
                    + "to use warrior, 2 for use spell and 3 to use guardian");
                    return -1;
                }
            }
            catch(NumberFormatException e){
                StdOut.println("this isn't a number, please input a number, remember 1 "
                    + "to use warrior, 2 for use spell and 3 to use guardian");
                return -1;
            }
        }
     /**
     * method that open the file cards.txt and create all the cards
     */
    public void addCardsToTheSystem(){
        try{
            ArchivoEntrada arch = new ArchivoEntrada("Cards.txt");
            while(!arch.isEndFile()){
                Registro registro = arch.getRegistro();
                String field1 = registro.getString();
                String field2 = registro.getString();
                String field3 = registro.getString();
                String field4 = registro.getString();
                String field5 = registro.getString();
                String field6 = registro.getString();

                if(field5 != null && field6 != null){
                    String name = field1;
                    String id = field2;
                    String rarity = field3;
                    String race = field4;
                    double hp = Double.parseDouble(field5);
                    double damage = Double.parseDouble(field6);
                    Warrior warrior = new Warrior(name,id,rarity,race,hp,damage);
                    warriorCards.addWarrior(warrior);
                }
                if(field5 == null && field6 == null){
                    String name = field1;
                    String id = field2;
                    String rarity = field3;
                    double damage = Double.parseDouble(field4);
                    Spell spell = new Spell(name,id,rarity,damage);
                    spellCards.addSpell(spell);
                }
                if(field6 == null && field5 != null){
                   String name = field1;
                   String id = field2;
                   String race = field3;
                   double hp = Double.parseDouble(field4);;
                   double damage = Double.parseDouble(field5);
                   Guardian guardian = new Guardian(name,id,race,hp,damage);
                   guardianCards.addGuardian(guardian);
                }
            }
        }
        catch(IOException e){
            StdOut.println("The file cards.txt doesn't exist");
        }
    }
    
    @Override
    public void play(){
        StdOut.println("Player 1 enter your alias");
        String name1 = StdIn.readString();
        Deck deck1 =chooseYourCards(1);
        //no se si es asi: Deck deck1 = chooseYourCards(Card[] cards);
        if(deck1==null){
            StdOut.println("Try again");
            return;
        }
        StdOut.println("Player 2 enter your alias");
        String name2 = StdIn.readString();
        //no se si es asi: Deck deck1 = chooseYourCards(Card[] cards)
        Deck deck2= chooseYourCards(2);
        if(deck2==null){
            StdOut.println("Try again");
            return;
        }
        player1 = new Hero(name1,deck1);
        heroes.addHero(player1);
        player2 = new Hero(name2,deck2);
        heroes.addHero(player2);
        
        int dice1 = StdRandom.uniform(6)+1;
        int dice2 = StdRandom.uniform(6)+1;
        while(dice1 == dice2){
            dice1 = StdRandom.uniform(6)+1;
            dice2 = StdRandom.uniform(6)+1;
        }
        if (dice1 > dice2){
            turn=1;
            StdOut.println("Player 1 starts");
        }
        else{
            turn=2;
            StdOut.println("Player 2 starts");
        }
        gameTurns();
    }
    
    public void gameTurns(){
        while(player1.getHp()>0 && player2.getHp()>0){
            int optionSelected;
            if(turn==1){
                optionSelected=showDuelOptions();
                if(optionSelected==1){
                    useWarrior(1);
                }
                if(optionSelected==2){
                    useSpell(1);
                }
                if(optionSelected==3){
                    useGuardian(1);
                }
            }
            if(turn==2){
                optionSelected=showDuelOptions();
                if(optionSelected==1){
                    useWarrior(2);
                }
                if(optionSelected==2){
                    useSpell(2);
                }
                if(optionSelected==3){
                    useGuardian(2);
                }
            }   
        }
        if(player1.getHp()>0 && player2.getHp()==0){
            StdOut.println(player1.getAlias()+" has won");
        }
        if(player2.getHp()>0 && player1.getHp()==0){
            StdOut.println(player2.getAlias()+" has won");
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
            player1.dealDamage(player1.getDeck().getWarrior().getDamage());
            
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
        if(player==2){
            StdOut.println(".:: Warrior of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getWarrior().getName()+" -> "+
            player2.getDeck().getWarrior().getRace() + " -> Hp: "    +
            player2.getDeck().getWarrior().getHp() +   " -> Damage: "+
            player2.getDeck().getWarrior().getDamage());
            
            StdOut.println(".:: "+player2.getDeck().getWarrior().getName()+
            " attacks ::.");
            player2.dealDamage(player2.getDeck().getWarrior().getDamage());
            
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
            player1.dealDamage(player1.getDeck().getSpell().getDamage());
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
        if(player==2){
            StdOut.println(".:: Spell of "+ player2.getAlias()+" ::.");
            StdOut.println(player2.getDeck().getSpell().getName()+" -> "+
            player2.getDeck().getSpell().getRarity() + " -> Damage: "+
            player2.getDeck().getSpell().getDamage());
            
            StdOut.println(".:: Attack with "+player2.getDeck()
            .getSpell().getName()+ " ::.");
            player2.dealDamage(player2.getDeck().getSpell().getDamage());
            
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
    public int showDuelOptions() {
        StdOut.println("[1] Use Warrior");
        StdOut.println("[2] Use Spell");
        StdOut.println("[3] Use Guardian");
        StdOut.println("Enter the option you will choose");
        String option = StdIn.readString();
        int validatedOption = validateGameOptions(option);
        while(validatedOption == -1){
            option = StdIn.readString();
            validatedOption = validateGameOptions(option);
        }
        return validatedOption ;
    }

    @Override
    public void useGuardian(int player) {
        
        
    }

    @Override
    public void findCardGame(String id) {
        for(int i=0;i<warriorCards.getWarriorQty();i++){
            if(id.equals(warriorCards.getWarrior(i).getId())){
                StdOut.println("Name: "+ warriorCards.getWarrior(i).getName());
                StdOut.println("Race: "+ warriorCards.getWarrior(i).getRace());
                StdOut.println("Times used: "+ warriorCards.getWarrior(i)
                .getTimesUsed());
                StdOut.println("Times dead: "+ warriorCards.getWarrior(i)
                .getTimesDead());
                return;
                
            }
        }
        for(int i=0;i<guardianCards.getGuardianQty();i++){
            if(id.equals(guardianCards.getGuardian(i).getId())){
               StdOut.println("Name: "+ guardianCards.getGuardian(i).getName());
               StdOut.println("Race: "+ guardianCards.getGuardian(i).getRace());
               StdOut.println("Times used: "+ guardianCards.getGuardian(i)
               .getTimesUsed());
                return;
            }
        }
        for(int i=0;i<spellCards.getSpellQty();i++){
            if(id.equals(spellCards.getSpell(i).getId())){
                StdOut.println("Name: "+ spellCards.getSpell(i).getName());
               StdOut.println("Rarity: "+ spellCards.getSpell(i).getRarity());
               StdOut.println("Times used: "+ guardianCards.getGuardian(i)
               .getTimesUsed());
               return;
            }
        }
        StdOut.println("couldnt find the card id");
    }

    @Override
    public void heroesThatParticipated() {
        for(int i=0;i<heroes.getHeroQty();i++){
            if(i == (heroes.getHeroQty())-1){
                break;
            }
            else{
             StdOut.println(heroes.getHero(i).getAlias()+" v/s " + heroes.getHero(i+1).getAlias());   
            }
        }
    }

    @Override
    public void detailsOfLastCombat() {
        Hero firstHero = heroes.getHero((heroes.getHeroQty())-1);
        StdOut.println("First player of the last combat " + firstHero.getAlias());
        StdOut.println("Warrior"+firstHero.getDeck().getWarrior()+"Guardian"+firstHero.getDeck().getGuardian()+"Spell"+firstHero.getDeck().getSpell());
        StdOut.println("Damage dealt "+firstHero.getDamageDealt());
        Hero lastHero = heroes.getHero(heroes.getHeroQty());
        StdOut.println("Second player of the last combat " + lastHero.getAlias());
        StdOut.println("Warrior "+lastHero.getDeck().getWarrior()+"Guardian "+lastHero.getDeck().getGuardian()+"Spell "+lastHero.getDeck().getSpell());
        StdOut.println(lastHero.getDamageDealt());
        /*
        no se si esto se ve feo pero es el mismo print que los 3 print separados
        StdOut.println("First player of the last combat " + firstHero.getAlias()+
        "Warrior"+firstHero.getDeck().getWarrior()+"Guardian"+firstHero.getDeck().getGuardian()
        +"Spell"+firstHero.getDeck().getSpell()+"Damage dealt "+firstHero.getDamageDealt());
        */
        
    }
    public int menu(){
        String String = null;//variable que acepta todo tipo de datos
        boolean isCorrect = false;//boolean que permite que funcione el while
        int numberSelected;// Numero que va a ser retornado
        // Instrucciones
        StdOut.println(" .:: DISC OF WORLD ::. ");
        StdOut.println("[1] Play");
        StdOut.println("[2] Find Card-Game");
        StdOut.println("[3] Heroes that have participated");
        StdOut.println("[4] Details of the last combat");
        StdOut.println("[5] Exit");
        
        while(isCorrect == false){ 
            
            String = StdIn.readString();
            if("1".equals(String) || "2".equals(String) 
            || "3".equals(String) || "4".equals(String) ||
            "5".equals(String)){
                
                isCorrect=true;
            }
            else{
                StdOut.println("Error wrong input");
                
        
            }  
        }
        numberSelected = Integer.parseInt(String);
        return numberSelected;
    }
    
}
