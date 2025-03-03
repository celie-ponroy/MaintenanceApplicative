package com.gildedrose;

public class Conjured extends StockItem {

    public Conjured(String name, int sellIn,int quality) {
        super(name, sellIn, quality);
        if(quality > 50){
            this.quality = 50;
        }
    }

    @Override
    public void update() {
        super.update();
        super.update();
    }
}
