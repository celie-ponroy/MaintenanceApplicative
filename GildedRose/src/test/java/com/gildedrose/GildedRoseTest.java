package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQualityNormal() {
       Item[] items = new Item[] { new Item("patates", 2, 2) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(1, app.items[0].quality);
    }
    @Test
    void updateQualityNormalSellinNegative() {
        Item[] items = new Item[] { new Item("patates", 1, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void updateQualityAgedBrie() {
       Item[] items = new Item[] { new Item("Aged Brie", 2, 0) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(1, app.items[0].quality);
    }
    @Test
    void updateQualityBrieOver50() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void updateQualitySulfuras() {
       Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
       GildedRose app = new GildedRose(items);
       app.updateQuality();
       assertEquals(80, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket15() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }
    @Test
    void updateQualityConcertTicket0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }
    @Test
    void updateQualitySellInless0(){
        Item[] items = new Item[] { new Item("patates", -1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }



}
