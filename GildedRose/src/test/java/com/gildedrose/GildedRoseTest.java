package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualityNormal() {
       StockItem[] items = new StockItem[] { FactoryStockItem.getItem("patates",2,2) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(1, app.items[0].quality);
    }
    @Test
    void updateQualityNormalSellinNegative() {
        StockItem[] items = new StockItem[] {FactoryStockItem.getItem("patates", -1, 12) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void updateQualityAgedBrie() {
       StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Aged Brie", 2, 0) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(1, app.items[0].quality);
    }
    @Test
    void updateQualityBrieOver50() {
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void updateQualitySulfuras() {
       StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Sulfuras, Hand of Ragnaros", 0,0) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(80, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket15() {
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Backstage passes to a TAFKAL80ETC concert", 15, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket10() {
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket5() {
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket0() {
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void updateQualityConjured(){
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Conjured", 2, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }
    @Test
    void updateQualityConjuredSellinNegative(){
        StockItem[] items = new StockItem[] { FactoryStockItem.getItem("Conjured", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }



}
