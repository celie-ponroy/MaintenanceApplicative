package com.gildedrose;

public class StockItem extends Item {
    public StockItem(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
    }

    public void update(){
        if (quality > 0) {
            quality = quality - 1;
            if (sellIn < 0) {
                quality = 0;
            }
        }
        sellIn = sellIn - 1;
    }

}
