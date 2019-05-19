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
class Card {
    private String name;
    private String id;
    private int timesUsed;

    protected Card(String name, String id) {
        this.name = name;
        this.id = id;
        timesUsed=0;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getTimesUsed() {
        return timesUsed;
    }
    public void cardSelected(){
        timesUsed++;
    }
    
    
    
}
