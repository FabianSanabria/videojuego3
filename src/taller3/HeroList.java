/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller3;

import ucn.StdOut;

/**
 *
 * @author fabianxd
 */
class HeroList {
    private Hero [] heroes;
    private int heroQty;
    private int max;

    public HeroList(int max) {
        heroQty=0;
        this.max = max;
        heroes= new Hero[max];
    }

    public Hero getHero(int i) {
        Hero hero;
        if(i<heroQty){
            hero= heroes[i];
            return hero;
        }
        else{
         return null;   
        }
        
    }
    public boolean addHero(Hero hero){
        if(heroQty>= max){
            StdOut.println("Cant add more Heroes, list is full");
            return false;
        }
        else{
            heroes[heroQty]=hero;
            heroQty++;
            return true;
        }
    }

    public int getHeroQty() {
        return heroQty;
    }
    
    
}
