package cn.edu.nju.cocoelf.code_elf_back_end.entity;

public class StubApi {
    private String package_Name;

    private String class_Name;

    private String name;

    private String type;

    private String description;

    private String chinese;

    private String page;

    private String position;

    public String getPackage_Name() {
        return package_Name;
    }

    public void setPackage_Name(String package_Name) {
        this.package_Name = package_Name;
    }

    public String getClass_Name() {
        return class_Name;
    }

    public void setClass_Name(String class_Name) {
        this.class_Name = class_Name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPrint(){
        String show = "";
        if(package_Name!=null && !"".equals(package_Name))  show+=package_Name+".";
        if(class_Name!=null && !"".equals(class_Name))  show+=class_Name+".";
        show+=name;
        show+=" ["+type+"]";
        return show;
    }
}
