/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;

/**
 *
 * @author fabianxd
 */
public interface GameSystem {
    
    public boolean chooseYourCards(int player);
    public void showDuelOptions();
    public void useWarrior(int player);
    public void useGuardian(int player);
    public void useSpell(int player);
    public boolean findCardGame(String id);
    public void heroesThatParticipated();
    public void detailsOfLastCombat();  
}
