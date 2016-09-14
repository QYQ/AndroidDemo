package com.kent.learningdemo.item.startmode;


/**
 * Created by kent on 16/9/1.
 */
public class AppVersion implements Comparable<AppVersion>{

    /**
     * 格式化后的版本号的位数 （xx.xx.xx）即有几个 xx
     */
    public static final int FORMATTED_VERSION_PART_COUNT = 3;

    /**
     * 版本号分隔符
     */
    public static final String FORMATTED_VERSION_PART_SEPERATOR = ".";


    private static final int FIRST_PART_OFFSET = 10000;

    private static final int SECOND_PART_OFFSET = 100;


    public String first;
    public String second;
    public String third;

    private AppVersion(){}

    public AppVersion(String first, String second, String third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    /**
     * 根据版本号默认
     * @param version
     * @return
     */
    public static AppVersion create(String version){

        String formatedVersion = AppVersionUtil.format(version);

        if(formatedVersion == null || "".equals(formatedVersion)){
            return createDefault();
        }

        String[] vArr = formatedVersion.split("\\.");

        return new AppVersion(vArr[0], vArr[1], vArr[2]);
    }

    /**
     * 创建默认版本号
     * @return  0.0.0
     */
    public static AppVersion createDefault(){
        return new AppVersion("0", "0", "0");
    }

    @Override
    public String toString() {
        return first + FORMATTED_VERSION_PART_SEPERATOR
                + second + FORMATTED_VERSION_PART_SEPERATOR + third;
    }

    /**
     * 转换成整型
     * @return
     */
    public int toInt(){
        return Integer.parseInt(first) * FIRST_PART_OFFSET
                + Integer.parseInt(second) * SECOND_PART_OFFSET
                + Integer.parseInt(third);
    }


    @Override
    public int compareTo(AppVersion version) {
        int thisInt = toInt();
        int otherInt = version.toInt();
        if(thisInt > otherInt){
            return 1;
        }else if(thisInt < otherInt){
            return -1;
        }else {
            return 0;
        }
    }
}
