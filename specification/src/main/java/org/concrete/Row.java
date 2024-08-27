package org.concrete;

import java.util.Comparator;
import java.util.List;

/**
 * PLain Old Java Object that contains strings
 */
public class Row implements Comparable<Row>{
    private List<String> contents;

    public Row() {
    }

    public Row(List<String> contents) {
        this.contents = contents;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for(String s: contents){
            sb.append(s).append(",");
        }
        return sb.toString();
    }

    @Override
    public int compareTo(Row o) {
        int returnRes = 0;
        for(int i = 0; i < this.contents.size();i++){
            if(!o.getContents().get(i).equals(this.contents.get(i))){
                returnRes = 1;
                break;
            }
        }
        return returnRes;
    }
}
