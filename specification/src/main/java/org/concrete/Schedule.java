package org.concrete;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;

/**
 * THE Schedule Class
 */
public class Schedule {
    /**
     * Special row that contains the header inforamtion
     */
    private Row header;
    /**
     * Boolean that tells us if the schedule has a header
     */
    private boolean containsHeader;
    /**
     * A list that conatins all the rows of a schedule
     */
    private List<Row> allRows;
    /**
     * A list of hashMaps where each hashMap coresponds to collumns of the schedule
     * effectivly allowing us to have a match a schedule field to all the rows that contain it
     * used for filtering and basic checks of availability.
     */
    @JsonIgnore
    @JsonIgnoreProperties
    private List<Map<String, List<Row>>> unifiedMap;

    public Schedule() {
    }

    /**
     * Schedule constructor, checks for header, and initializes the unified hashMap
     * @param containsHeader boolean that tells us if the given content in the form of rows has a special row called a header in its 0 index
     * @param allRows list of all rows given to be inputed into the schedule
     */

    public Schedule(boolean containsHeader, List<Row> allRows) {
        this.containsHeader = containsHeader;
        this.allRows = allRows;
        if(containsHeader){
            header = allRows.remove(0);
        }
        initUnifiedMap();
    }

    /**
     * Initializes the unifiedMap from all the rows of a schedule
     */
    public void initUnifiedMap(){
        unifiedMap = new ArrayList<Map<String, List<Row>>>();
        int bound = allRows.get(0).getContents().size();
        for(int i = 0; i < bound; i++){
            //empty map
            Map<String,List<Row>> oneMap = new HashMap<String,List<Row>>();
            unifiedMap.add(oneMap);
        }
        //time complexity thru the roof
        for(Map<String,List<Row>> oneMap : unifiedMap){
            int index = unifiedMap.indexOf(oneMap);
            for(Row row: allRows){
                String key = row.getContents().get(index);
                if(!(oneMap.containsKey(key))){
                    oneMap.put(key,new ArrayList<>(List.of(row)));
                }else{
                    oneMap.get(key).add(row);
                }
            }
        }
    }

    /**
     * Goes thru the unifiedMap and returns all the rows where the given parameter is mentioned
     * @param paramter a string whoose rows we want to see
     * @return a list of rows that represent all the rows that contain the given parameter as a field
     */
    public List<Row> filter(String paramter){
        List<Row> filtered = new ArrayList<>();
        for (Map<String, List<Row>> map : unifiedMap) {
            for(String key: map.keySet()){
                if(key.toLowerCase().contains(paramter.toLowerCase())){
                    filtered.addAll(map.get(key));
                }
            }
        }
        return filtered;
    }

    /**
     * Modifies a row
     * @param row the row which we will modify
     * @param index the index of the field that we will modify
     * @param modification the content that we will overwrite the field with
     * @return a boolean to tell us if the modification is possible true if yes, false if not
     */
    public boolean modifyRowField(Row row, int index,String modification){
        //row based checks
        if(row == null || row.getContents() == null || modification == null)return false;
        //index based checks
        if(index < 0 || index > row.getContents().size()-1)return false;
        //value based checks
        if((row.getContents().get(index)).equals(modification))return false;

        row.getContents().set(index,modification);
        return true;
    }

    /**
     * Adds a row to alLRows of schedule
     * @param row the row that we want to add
     * @return a boolean to inform us if the operation has been succesfull true if yes, false if not
     */
    public boolean addRow(Row row){
        if(row == null)return false;
        if(row.getContents() == null)return false;
        if(row.getContents().size() > allRows.get(0).getContents().size()
        || row.getContents().size() < allRows.get(0).getContents().size()) return false;
        if(allRows.contains(row))return false;
        allRows.add(row);
        return true;
    }

    /**
     * Deletes a row from allRows of a Schedule
     * @param row the row that we want to delete
     * @return a boolean that informs us if the operation has been successfull, yes if true, false if not
     */
    public boolean deleteRow(Row row){
        if(row == null)return false;
        if(row.getContents() == null)return false;
        if(row.getContents().size() > allRows.get(0).getContents().size()
        || row.getContents().size() < allRows.get(0).getContents().size())return false;
        if(!this.allRows.contains(row)) return false;
        allRows.remove(row);
        return true;
    }

    /**
     * Allows for artifical creation and setting of a header row to a schedule
     * @param content the content we want to fill the header with
     * @return a boolean that informs us if the operation has been successfull, yes if true, false if not
     */
    public boolean createHeader(List<String> content){
        if(content == null || content.isEmpty())return false;
        if(content.size() > allRows.get(0).getContents().size()
                || content.size() < allRows.get(0).getContents().size())return false;
        header = new Row(content);
        containsHeader = true;
        return true;
    }

    /**
     * Adds a field or column to the header therefor increasing the dimension of the schedule
     * after creating a new field it will also go thru allRows and add an additional field for each row
     * in order to be consistent with new header, that new fields will be written in with TBW, short for
     * To Be Written
     * @param columnContent the content that the new field of the header will have
     * @return a boolean to inform us if the operation has been successfull, yes if true, false if not
     */
    public boolean addHeaderColumn(String columnContent){
        //needs checks
        header.getContents().add(columnContent);
        int newSize = header.getContents().size();
        allRows.forEach(row -> row.getContents().add("TBW"));
        return true;
    }

    /**
     * Removes a field from the header therefor also removing it from all the other rows
     * @param index the index of the field that we want to remove
     * @return a boolean to inform us if the operation has been successful, yes if true, false if not
     */
    public boolean removeHeaderColumn(int index){
        if(index < 0 || index > header.getContents().size())return false;
        header.getContents().remove(index);
        allRows.forEach(row -> row.getContents().remove(index));
        return true;
    }

    /**
     * Reforms the unifiedMap based on the contents of the allRows list of the schedule
     */
    public void reformUnifiedMap(){
        //delete all the contents of the map, just in case the number of columns changed
        unifiedMap.clear();
        int bound = allRows.get(0).getContents().size();
        for(int i = 0; i < bound; i++){
            Map<String,List<Row>> oneMap = new HashMap<String,List<Row>>();
            unifiedMap.add(oneMap);
        }
        //maybe try streams later
        for(Map<String,List<Row>> oneMap : unifiedMap){
            int index = unifiedMap.indexOf(oneMap);
            for(Row row: allRows){
                String key = row.getContents().get(index);
                if(!(oneMap.containsKey(key))){
                    oneMap.put(key,new ArrayList<>(List.of(row)));
                }else{
                    oneMap.get(key).add(row);
                }
            }
        }
    }

    @Override
    public String toString() {
        if(allRows == null || allRows.isEmpty())return null;
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(header).append(">\n");
        for (Row row : allRows) {
            sb.append(row.toString());
            if(allRows.indexOf(row) == allRows.size()-1){
                sb.append("|");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Row getHeader() {
        return header;
    }

    public void setHeader(Row header) {
        this.header = header;
    }

    public boolean isContainsHeader() {
        return containsHeader;
    }

    public void setContainsHeader(boolean containsHeader) {
        this.containsHeader = containsHeader;
    }

    public List<Row> getAllRows() {
        return allRows;
    }

    public void setAllRows(List<Row> allRows) {
        this.allRows = allRows;
    }

    public List<Map<String, List<Row>>> getUnifiedMap() {
        return unifiedMap;
    }

    public void setUnifiedMap(List<Map<String, List<Row>>> unifiedMap) {
        this.unifiedMap = unifiedMap;
    }

}
