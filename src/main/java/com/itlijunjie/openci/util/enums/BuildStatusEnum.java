package com.itlijunjie.openci.util.enums;

public enum BuildStatusEnum implements EnumInterface {
    Build("构建中", 1),
    Succeed("构建成功", 2),
    Fail("构建失败", 3);

    private String description = "";
    private int value = 0;

    private BuildStatusEnum(String _name, int _index) {
        this.description = _name;
        this.value = _index;
    }

    public static String getName(int index) {
        for (BuildStatusEnum s : BuildStatusEnum.values()) {
            if (s.getValue() == index) {
                return s.description;
            }
        }
        return null;
    }

    public static BuildStatusEnum fromValue(int value) {
        for (BuildStatusEnum s : BuildStatusEnum.values()) {
            if (s.getValue() == value) {
                return s;
            }
        }
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}