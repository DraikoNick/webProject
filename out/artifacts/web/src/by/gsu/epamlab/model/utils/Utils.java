package by.gsu.epamlab.model.utils;

import by.gsu.epamlab.model.fabrics.SectionFactory;

import java.util.*;

public class Utils {
    public static String[] getEnumNames(Class<? extends Enum<?>> en){
        return Arrays.stream(en.getEnumConstants())
                .map(Enum::name)
                .toArray(String[]::new);
    }

    public static Map<String, String> getMapFromEnum(Class<? extends Enum<?>> en){
        String[] names = getEnumNames(en);
        Map <String, String> mapOfNames = new LinkedHashMap<>();
        for(String key:names){
            mapOfNames.put(
                    key,
                    SectionFactory.SectionKind.valueOf(key.toUpperCase()).getName());
        }
        return mapOfNames;
    }

    public static Map<String, String> getSectionsMap(){
        java.sql.Date today = new java.sql.Date(new Date().getTime());
        java.sql.Date tomorrow = TimeUtils.datePlusDays(today, TimeUtils.ONE_DAY_MIL);
        Map<String, String> map = getMapFromEnum(SectionFactory.SectionKind.class);
        String valueToday = map.get(SectionFactory.SectionKind.TODAY.toString()) + " " + TimeUtils.formatDate(today);
        String valueTomorrow = map.get(SectionFactory.SectionKind.TOMORROW.toString()) + " " + TimeUtils.formatDate(tomorrow);
        map.put(SectionFactory.SectionKind.TODAY.toString(), valueToday);
        map.put(SectionFactory.SectionKind.TOMORROW.toString(), valueTomorrow);
        return map;
    }
}
