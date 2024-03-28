package net.braniumtech;

import com.google.common.base.Splitter;


public class AES_UTIL {
    public static final int MATRIX_SIZE = 4;
    public static final String[][] S_BOX = new String[][]{
            {"63", "7c", "77", "7b", "f2", "6b", "6f", "c5", "30", "01", "67", "2b", "fe", "d7", "ab", "76"},
            {"ca", "82", "c9", "7d", "fa", "59", "47", "f0", "ad", "d4", "a2", "af", "9c", "a4", "72", "c0"},
            {"b7", "fd", "93", "26", "36", "3f", "f7", "cc", "34", "a5", "e5", "f1", "71", "d8", "31", "15"},
            {"04", "c7", "23", "c3", "18", "96", "05", "9a", "07", "12", "80", "e2", "eb", "27", "b2", "75"},
            {"09", "83", "2c", "1a", "1b", "6e", "5a", "a0", "52", "3b", "d6", "b3", "29", "e3", "2f", "84"},
            {"53", "d1", "00", "ed", "20", "fc", "b1", "5b", "6a", "cb", "be", "39", "4a", "4c", "58", "cf"},
            {"d0", "ef", "aa", "fb", "43", "4d", "33", "85", "45", "f9", "02", "7f", "50", "3c", "9f", "a8"},
            {"51", "a3", "40", "8f", "92", "9d", "38", "f5", "bc", "b6", "da", "21", "10", "ff", "f3", "d2"},
            {"cd", "0c", "13", "ec", "5f", "97", "44", "17", "c4", "a7", "7e", "3d", "64", "5d", "19", "73"},
            {"60", "81", "4f", "dc", "22", "2a", "90", "88", "46", "ee", "b8", "14", "de", "5e", "0b", "db"},
            {"e0", "32", "3a", "0a", "49", "06", "24", "5c", "c2", "d3", "ac", "62", "91", "95", "e4", "79"},
            {"e7", "c8", "37", "6d", "8d", "d5", "4e", "a9", "6c", "56", "f4", "ea", "65", "7a", "ae", "08"},
            {"ba", "78", "25", "2e", "1c", "a6", "b4", "c6", "e8", "dd", "74", "1f", "4b", "bd", "8b", "8a"},
            {"70", "3e", "b5", "66", "48", "03", "f6", "0e", "61", "35", "57", "b9", "86", "c1", "1d", "9e"},
            {"e1", "f8", "98", "11", "69", "d9", "8e", "94", "9b", "1e", "87", "e9", "ce", "55", "28", "df"},
            {"8c", "a1", "89", "0d", "bf", "e6", "42", "68", "41", "99", "2d", "0f", "b0", "54", "bb", "16"},
    };
    public static final String[] RC = {"01", "02", "04", "08", "10", "20", "40", "80", "1B", "36"};
    public static String M = "B104AADD3AC293DF787EFD2CF8065925";
    public static String[] K = {
            "2B7E151628AED2A6ABF7158809CF4F3C",
            "1899285EE1F5FB0C716388AF9F6E9B04",
            "E871590711E41F1381E26AC56F019A9E",
            "E796CFC81EFAE5F68E6C06C36061FB65",
            "A234FB335BA144B2CBA7A1622544BFDA",
            "E5D12A191CBDF94B8C2B8AE862269943",
            "DF0E243D269B6229B69D17FF587EE7A4",
            "D6D8FCC12FB4D6FFBF2235CA512FC86C",
            "06DE22E3FF4B9D626F4D78B281AE660A",
            "7AA4866583C85537135E2694FD53353F",
            "399D1B7EC0085D6A500E28BCBEEDD8E7"
    };

    public static String[] splitTo4Byte(String value) {
        Iterable<String> parts = Splitter.fixedLength(2).split(value);
        String[] words = new String[4];
        var iter = parts.iterator();
        int i = 0;
        words[i++] = iter.next();
        while (iter.hasNext()) {
            words[i++] = iter.next();
        }
        return words;
    }

    public static String fromIntToHexString(int value) {
        String hex = Integer.toHexString(value).toUpperCase();
        if (hex.length() == 1) hex = "0" + hex;
        return hex;
    }

    public static int fromHexStringToInt(String value) {
        return Integer.parseInt(value, 16);
    }

    public static String findNewSBoxHex(String hex) {
        String s_box_x = hex.substring(0, 1);
        String s_box_y = hex.substring(1);
        int index_x = getIndex(s_box_x);
        int index_y = getIndex(s_box_y);
        return S_BOX[index_x][index_y].toUpperCase();
    }

    private static int getIndex(String pos) {
        int index = -1;
        switch (pos) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> index = Integer.valueOf(pos);
            case "A" -> index = 10;
            case "B" -> index = 11;
            case "C" -> index = 12;
            case "D" -> index = 13;
            case "E" -> index = 14;
            case "F" -> index = 15;
        }
        return index;
    }

    public static String getHexXorHex(String hex1, String hex2) {
        int num1 = fromHexStringToInt(hex1);
        int num2 = fromHexStringToInt(hex2);
        int xorResult = num1 ^ num2;
        return fromIntToHexString(xorResult);
    }
}
