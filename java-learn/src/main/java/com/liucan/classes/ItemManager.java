package com.liucan.classes;

import lombok.AllArgsConstructor;

/**
 * 局部类,成员类，静态成员类
 *
 * @author liucan
 * @version 18-12-9
 */
public class ItemManager {
    private Item[] itemArray;
    private int index = 0;

    public ItemManager(int size) {
        itemArray = new Item[size];
    }

    public void add(Item item) {
        itemArray[index++] = item;
    }

    public ItemList getItemList() {
        return new ItemList();
    }

    public static class ItemList1 {
        private Integer key;
        private Integer value;
    }

    //成员类
    private class ItemList {
        private Integer key;
        private Integer value;

        public void test() {
        }
    }

    public Iterator iterator() {
        //局部类
        class IteratorImple implements Iterator {

            @Override
            public boolean hasMoreElements() {
                return itemArray.length > index;
            }

            @Override
            public Object nextElement() {
                return itemArray[index++];
            }
        }
        return new IteratorImple();
    }
}

@AllArgsConstructor(staticName = "of")
class Item {
    private String name;
    private String value;
}

interface Iterator {
    boolean hasMoreElements();

    Object nextElement();
}
