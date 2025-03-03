package com.gildedrose;

public class FactoryStockItem {
    public static StockItem getItem(String name, int sellIn, int quality) {
        switch (name){
            case "Aged Brie":
                return new AgedBrie(name, sellIn, quality);
            case "Conjured":
                return new Conjured(name, sellIn, quality);
            case "Sulfuras, Hand of Ragnaros":
            case "Sulfuras":
                return new Sulfuras(name, sellIn);
            case "Backstage passes to a TAFKAL80ETC concert":
            case "Backstage":
                return new Backstage(name, sellIn, quality);
            default:
                return new StockItem(name, sellIn, quality);
        }

    }
}
