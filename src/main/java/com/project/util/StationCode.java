package com.project.util;

public class StationCode {

    // 서울 천안아산 대전 동대구 대구 울산 경주 부산  Flag
    //  1     0    1   1    0   0   0  1    1
    //   1     0    1   0    0   0   0   0   0


    public static final int FLAG = 1;
    public static final int BUSAN = 2;
    public static final int ULSAN = 4;
    public static final int GYUNGJU = 8;
    public static final int DAEGU = 16;
    public static final int DONGDAEGU = 32;
    public static final int DAJEON = 64;
    public static final int CHEONAN = 128;
    public static final int SEOUL = 256;



    static int getStationShift(int stationCode) {
        switch (stationCode) {
            case BUSAN:
                return 1;
            case ULSAN:
                return 2;
            case GYUNGJU:
                return 3;
            case DAEGU:
                return 4;
            case DONGDAEGU:
                return 5;
            case DAJEON:
                return 6;
            case CHEONAN:
                return 7;
            case SEOUL:
                return 8;
        }
        return -1;
    }


    public static String intToStation(int index) {
        switch (index) {
            case 256:
                return "서울역";
            case 128:
                return "천안아산역";
            case 64:
                return "대전역";
            case 32:
                return "동대구역";
            case 16:
                return "대구역";
            case 8:
                return "울산역";
            case 4:
                return "경주역";
            case 2:
                return "부산역";

            default:
                return "";
        }

    }

    public static int staionToCode(String station) {
        switch (station) {
            case "서울역":
                return 256;
            case "천안아산역":
                return 128;
            case "대전역":
                return 64;
            case "동대구역":
                return 32;
            case "대구역":
                return 16;
            case "울산역":
                return 8;
            case "경주역":
                return 4;
            case "부산역":
                return 2;

            default:
                return -1;
        }
    }


}
