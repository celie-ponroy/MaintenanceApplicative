package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items){
           handleItem(item);

        }
    }
    private void handleItem(Item item){
        //Aged Brie
        if(item.name.equals("Aged Brie")){
            handleBrie(item);
            //Backstage passes
        } else if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
           handleBackstage(item);
        }else if (item.name.equals("Sulfuras, Hand of Ragnaros")) {
            //Sulfuras
            //on change rien
            handleSulfuras(item);
        } else {
            //Normal
            handleClassicItem(item);
        }

    }
    private void handleBrie(Item item){
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
    private void handleBackstage(Item item){
        if (item.quality < 50) {
            item.quality = item.quality + 1;
            if (item.sellIn < 11) {
                item.quality = item.quality + 1;
            }

            if (item.sellIn < 6) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            }
        }
        if (item.sellIn <= 0) {
            item.quality = 0;
        }
    }
    private void handleSulfuras(Item item){
        //on change rien
    }
    private void handleClassicItem(Item item){
        if (item.quality > 0) {
            item.quality = item.quality - 1;
            if (item.sellIn < 0) {
                    item.quality = item.quality - 1;
            }
        }
        item.sellIn = item.sellIn - 1;
    }
}
