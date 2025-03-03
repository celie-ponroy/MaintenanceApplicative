package com.gildedrose;

public class Backstage extends StockItem{


    public Backstage(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        if(quality > 50){
            this.quality = 50;
        }
    }

    @Override
    public void update() {
        if (quality < 50) {
            quality = quality + 1;
            if (sellIn < 11) {
                quality = quality + 1;
            }

            if (sellIn < 6) {
                quality = quality + 1;
            }
        }
        if (sellIn <= 0) {
            quality = 0;
        }
    }
}
