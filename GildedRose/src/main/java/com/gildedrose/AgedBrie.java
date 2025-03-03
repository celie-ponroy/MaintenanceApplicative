package com.gildedrose;

public class AgedBrie extends StockItem{
    public AgedBrie(String name, int sellIn, int quality) {
        super(name, sellIn, quality);
        if(quality > 50){
            this.quality = 50;
        }
    }

    @Override
    public void update() {
        if (this.quality < 50) {
            this.quality = this.quality + 1;
        }
    }
}
